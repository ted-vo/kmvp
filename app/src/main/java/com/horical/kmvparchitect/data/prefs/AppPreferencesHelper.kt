package com.horical.kmvparchitect.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.horical.kmvparchitect.di.anotation.PreferenceInfo
import com.horical.kmvparchitect.utils.AppConstants
import com.horical.kmvparchitect.utils.extension.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferencesHelper @Inject constructor(
        context: Context,
        @PreferenceInfo private val prefFileName: String) : PreferencesHelper {

    private val mPreferences: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    companion object {
        private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        private val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
        private val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
        private val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
        private val PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL"
        private val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"
    }

    override fun getAccessToken(): String? {
        return mPreferences.getString(PREF_KEY_ACCESS_TOKEN, null)
    }

    override fun setAccessToken(accessToken: String?) {
        mPreferences.edit { putString(PREF_KEY_ACCESS_TOKEN, accessToken) }
    }

    override fun getCurrentUserEmail(): String? {
        return mPreferences.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
    }

    override fun setCurrentUserEmail(email: String?) {
        mPreferences.edit { putString(PREF_KEY_CURRENT_USER_EMAIL, email) }
    }

    override fun getCurrentUserId(): Long? {
        val userId = mPreferences.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX)
        return when (userId) {
            AppConstants.NULL_INDEX -> null
            else -> userId
        }
    }

    override fun setCurrentUserId(userId: Long?) {
        val id = userId ?: AppConstants.NULL_INDEX
        mPreferences.edit { putLong(PREF_KEY_CURRENT_USER_ID, id) }
    }

    override fun getCurrentUserLoggedInMode(): Int {
        return mPreferences.getInt(
                PREF_KEY_USER_LOGGED_IN_MODE,
                AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type)
    }

    override fun setCurrentUserLoggedInMode(mode: AppConstants.LoggedInMode) {
        mPreferences.edit { putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.type) }
    }

    override fun getCurrentUserName(): String? {
        return mPreferences.getString(PREF_KEY_CURRENT_USER_NAME, null)
    }

    override fun setCurrentUserName(name: String?) {
        mPreferences.edit { putString(PREF_KEY_CURRENT_USER_NAME, name) }
    }

    override fun getCurrentUserProfilePicUrl(): String? {
        return mPreferences.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null)
    }

    override fun setCurrentUserProfilePicUrl(url: String?) {
        mPreferences.edit { putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, url) }
    }
}