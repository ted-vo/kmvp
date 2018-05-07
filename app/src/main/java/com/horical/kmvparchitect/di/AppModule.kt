package com.horical.kmvparchitect.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.horical.kmvparchitect.BuildConfig
import com.horical.kmvparchitect.data.db.AppDatabase
import com.horical.kmvparchitect.data.db.option.AppOptionRepo
import com.horical.kmvparchitect.data.db.option.OptionRepo
import com.horical.kmvparchitect.data.db.question.AppQuestionRepo
import com.horical.kmvparchitect.data.db.question.QuestionRepo
import com.horical.kmvparchitect.data.db.user.AppUserRepo
import com.horical.kmvparchitect.data.db.user.UserRepo
import com.horical.kmvparchitect.data.network.ApiManager
import com.horical.kmvparchitect.data.network.ApiService
import com.horical.kmvparchitect.data.network.HeadersInterceptor
import com.horical.kmvparchitect.data.network.exception.RxErrorHandlingCallAdapterFactory
import com.horical.kmvparchitect.data.prefs.AppPreferencesHelper
import com.horical.kmvparchitect.data.prefs.PreferencesHelper
import com.horical.kmvparchitect.di.anotation.ApiKeyInfo
import com.horical.kmvparchitect.di.anotation.DatabaseInfo
import com.horical.kmvparchitect.di.anotation.PreferenceInfo
import com.horical.kmvparchitect.utils.AppConstants
import com.horical.kmvparchitect.utils.rx.AppSchedulerProvider
import com.horical.kmvparchitect.utils.rx.ScheduleProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseName(): String = AppConstants.APP_DB_NAME

    @Provides
    @Singleton
    internal fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    internal fun provideQuestionRepo(appDatabase: AppDatabase): QuestionRepo {
        return AppQuestionRepo(appDatabase.questionDao())
    }

    @Provides
    @Singleton
    internal fun provideOptionRepo(appDatabase: AppDatabase): OptionRepo {
        return AppOptionRepo(appDatabase.optionDao())
    }

    @Provides
    @Singleton
    internal fun provideUserRepo(appDatabase: AppDatabase): UserRepo {
        return AppUserRepo(appDatabase.usersDao())
    }

    @Provides
    @PreferenceInfo
    internal fun providePreferenceFileName(): String = AppConstants.PREF_NAME

    @Provides
    @Singleton
    internal fun providePreferenceHelper(appPreferenceHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferenceHelper
    }

    @Provides
    @ApiKeyInfo
    internal fun provideApiKey(): String = BuildConfig.API_KEY

    @Provides
    @Singleton
    internal fun provideGson() = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Provides
    @Singleton
    internal fun provideOkHttpClient(application: Application, headersInterceptor: HeadersInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        val cache = Cache(cacheDir, 10 * 1024 * 1024)
        return OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(headersInterceptor)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideApiService(apiManager: ApiManager): ApiService = apiManager

    @Provides
    internal fun provideScheduleProvider(): ScheduleProvider = AppSchedulerProvider()

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}