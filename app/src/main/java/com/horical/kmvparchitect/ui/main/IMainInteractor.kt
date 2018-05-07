package com.horical.kmvparchitect.ui.main

import com.horical.kmvparchitect.data.network.response.LogoutResponse
import com.horical.kmvparchitect.ui.base.IBaseInteractor
import io.reactivex.Observable

interface IMainInteractor : IBaseInteractor {

    fun logout(): Observable<LogoutResponse>

    fun getUserInfo(): Pair<String?, String?>

    fun getAllQuestions(): Observable<List<QuestionCardData>>
}