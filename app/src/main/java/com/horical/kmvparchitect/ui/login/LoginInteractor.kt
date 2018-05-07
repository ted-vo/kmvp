package com.horical.kmvparchitect.ui.login

import android.content.Context
import com.horical.kmvparchitect.data.db.user.UserRepo
import com.horical.kmvparchitect.data.network.ApiService
import com.horical.kmvparchitect.data.network.request.LoginRequest
import com.horical.kmvparchitect.data.network.response.LoginResponse
import com.horical.kmvparchitect.data.prefs.PreferencesHelper
import com.horical.kmvparchitect.ui.base.BaseInteractor
import com.horical.kmvparchitect.utils.AppConstants
import io.reactivex.Observable
import javax.inject.Inject

class LoginInteractor
@Inject
constructor(private val mContext: Context,
            private val mUserRepository: UserRepo,
            mApiService: ApiService,
            mPreferencesHelper: PreferencesHelper) : BaseInteractor(mApiService, mPreferencesHelper), ILoginInteractor {

    override fun doServerLoginApiCall(email: String, password: String): Observable<LoginResponse> {
        return mApiService.doServerLoginApiCall(LoginRequest.ServerLoginRequest(email, password))
    }

    override fun doFBLoginApiCall(): Observable<LoginResponse> {
        return mApiService.doFacebookLoginApiCall(
                LoginRequest.FacebookLoginRequest("test", "test"))
    }

    override fun doGoogleLoginApiCall(): Observable<LoginResponse> {
        return mApiService.doGoogleLoginApiCall(
                LoginRequest.GoogleLoginRequest("test", "test"))
    }

    override fun updateUserInSharedPreferences(response: LoginResponse, loggedInMode: AppConstants.LoggedInMode) {
        return mPreferences.let {
            it.setAccessToken(response.accessToken)
            it.setCurrentUserId(response.userId)
            it.setCurrentUserName(response.userName)
            it.setCurrentUserEmail(response.userEmail)
            it.setCurrentUserLoggedInMode(loggedInMode)
        }
    }
}