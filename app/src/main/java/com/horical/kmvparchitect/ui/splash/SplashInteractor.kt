package com.horical.kmvparchitect.ui.splash

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.internal.`$Gson$Types`
import com.horical.kmvparchitect.data.db.model.Option
import com.horical.kmvparchitect.data.db.model.Question
import com.horical.kmvparchitect.data.db.option.OptionRepo
import com.horical.kmvparchitect.data.db.question.QuestionRepo
import com.horical.kmvparchitect.data.network.ApiService
import com.horical.kmvparchitect.data.prefs.PreferencesHelper
import com.horical.kmvparchitect.ui.base.BaseInteractor
import com.horical.kmvparchitect.utils.AppConstants
import com.horical.kmvparchitect.utils.CommonUtils
import io.reactivex.Observable
import javax.inject.Inject

class SplashInteractor
@Inject
constructor(private val context: Context,
            private val questionRepo: QuestionRepo,
            private val optionRepo: OptionRepo,
            mApiService: ApiService,
            mPreferences: PreferencesHelper) : BaseInteractor(mApiService, mPreferences), ISplashInteractor {

    override fun seedQuestions(): Observable<Boolean> {
        val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()
        return questionRepo.isQuestionEmpty().concatMap { isEmpty ->
            if (isEmpty) {
                val type = `$Gson$Types`.newParameterizedTypeWithOwner(
                        null,
                        List::class.java,
                        Question::class.java)
                val questions = gson.fromJson<List<Question>>(
                        CommonUtils.loadJSONFromAsset(
                                context,
                                AppConstants.SEED_DATABASE_QUESTIONS),
                        type)
                questionRepo.saveQuestions(questions)
            } else {
                Observable.just(false)
            }
        }
    }

    override fun seedOptions(): Observable<Boolean> {
        val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()
        return optionRepo.isOptionEmpty().concatMap { isEmpty ->
            if (isEmpty) {
                val type = `$Gson$Types`.newParameterizedTypeWithOwner(
                        null,
                        List::class.java,
                        Option::class.java)
                val optionList = gson.fromJson<List<Option>>(
                        CommonUtils.loadJSONFromAsset(
                                context,
                                AppConstants.SEED_DATABASE_OPTIONS),
                        type)
                optionRepo.saveOptionList(optionList)
            } else {
                Observable.just(false)
            }
        }
    }

    override fun getQuestions(): Observable<List<Question>> = questionRepo.getAllQuestion()
}