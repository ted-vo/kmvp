package com.horical.kmvparchitect

import android.app.Activity
import android.app.Application
import com.horical.kmvparchitect.di.AppComponent
import com.horical.kmvparchitect.di.DaggerAppComponent
import com.horical.kmvparchitect.utils.AppLogger
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

class MainApplication : Application(), HasActivityInjector {

    @Inject
    internal lateinit var mActivityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    lateinit var mAppComponent: AppComponent

    override fun activityInjector() = mActivityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        mAppComponent.inject(this)

        AppLogger.init()

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

    fun setComponent(component: AppComponent) {
        mAppComponent = component
    }
}