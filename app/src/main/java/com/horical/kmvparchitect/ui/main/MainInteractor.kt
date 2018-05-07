package com.horical.kmvparchitect.ui.main

import android.content.Context
import com.horical.kmvparchitect.data.db.model.Option
import com.horical.kmvparchitect.data.db.model.Question
import com.horical.kmvparchitect.data.db.option.OptionRepo
import com.horical.kmvparchitect.data.db.question.QuestionRepo
import com.horical.kmvparchitect.data.network.ApiService
import com.horical.kmvparchitect.data.network.response.LogoutResponse
import com.horical.kmvparchitect.data.prefs.PreferencesHelper
import com.horical.kmvparchitect.ui.base.BaseInteractor
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class MainInteractor
@Inject
constructor(private val context: Context,
            private val questionRepo: QuestionRepo,
            private val optionRepo: OptionRepo,
            mApiService: ApiService,
            mPreferencesHelper: PreferencesHelper) : BaseInteractor(mApiService, mPreferencesHelper), IMainInteractor {

    override fun logout(): Observable<LogoutResponse> = mApiService.doLogoutApiCall()

    override fun getUserInfo(): Pair<String?, String?> {
        return Pair(mPreferences.getCurrentUserEmail(), mPreferences.getCurrentUserName())
    }

    override fun getAllQuestions(): Observable<List<QuestionCardData>> {
        return questionRepo.getAllQuestion()
                .flatMapIterable { it }
                .flatMap {
                    Observable.zip(optionRepo.getOptionsForQuestion(it.id),
                            Observable.just(it),
                            BiFunction<List<Option>, Question, QuestionCardData> { options, question ->
                                QuestionCardData(options, question)
                            })
                }
                .toList()
                .toObservable()
    }
}