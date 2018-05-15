package com.horical.kmvparchitect.ui.base

import com.horical.kmvparchitect.data.network.ApiHelper
import com.horical.kmvparchitect.data.prefs.PreferencesHelper
import com.horical.kmvparchitect.utils.AppConstants

open class BaseInteractor
constructor(protected val mApiHelper: ApiHelper,
            protected val mPreferences: PreferencesHelper) : IBaseInteractor {

    override fun isUserLoggedIn(): Boolean {
        return mPreferences.getCurrentUserLoggedInMode() != AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type
    }

    override fun performUserLogout() {
        mPreferences.let {
            it.setCurrentUserId(null)
            it.setAccessToken(null)
            it.setCurrentUserEmail(null)
            it.setCurrentUserLoggedInMode(AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT)
        }
    }
}