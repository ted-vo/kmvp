package com.horical.kmvparchitect.ui.login

import android.content.Context
import com.horical.kmvparchitect.data.db.user.UserRepo
import com.horical.kmvparchitect.data.network.ApiHelper
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
            mApiHelper: ApiHelper,
            mPreferencesHelper: PreferencesHelper)
    : BaseInteractor(mApiHelper, mPreferencesHelper), ILoginInteractor {

    override fun doServerLoginApiCall(email: String, password: String): Observable<LoginResponse> {
        return mApiHelper.doServerLoginApiCall(LoginRequest.ServerLoginRequest(email, password))
    }

    override fun doFBLoginApiCall(): Observable<LoginResponse> {
        return mApiHelper.doFacebookLoginApiCall(
                LoginRequest.FacebookLoginRequest("test", "test"))
    }

    override fun doGoogleLoginApiCall(): Observable<LoginResponse> {
        return mApiHelper.doGoogleLoginApiCall(
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