package com.horical.kmvparchitect.ui.login

import com.horical.kmvparchitect.data.network.response.LoginResponse
import com.horical.kmvparchitect.ui.base.IBaseInteractor
import com.horical.kmvparchitect.utils.AppConstants
import io.reactivex.Observable

interface ILoginInteractor : IBaseInteractor {

    fun doServerLoginApiCall(email: String, password: String): Observable<LoginResponse>

    fun doFBLoginApiCall(): Observable<LoginResponse>

    fun doGoogleLoginApiCall(): Observable<LoginResponse>

    fun updateUserInSharedPreferences(response: LoginResponse, loggedInMode: AppConstants.LoggedInMode)
}