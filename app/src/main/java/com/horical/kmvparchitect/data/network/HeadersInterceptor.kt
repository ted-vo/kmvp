package com.horical.kmvparchitect.data.network

import com.horical.kmvparchitect.BuildConfig
import com.horical.kmvparchitect.data.prefs.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeadersInterceptor @Inject constructor(private val mPreferencesHelper: PreferencesHelper) : Interceptor {

    companion object {
        private const val API_KEY: String = "api_key"
        private const val USER_ID: String = "user_id"
        private const val ACCESS_TOKEN: String = "access_token"
    }

    override fun intercept(chain: Interceptor.Chain?): Response? {
        if (chain != null) {
            val originRequest: Request = chain.request()
            val builder: Request.Builder = originRequest.newBuilder()
            builder.addHeader(API_KEY, BuildConfig.API_KEY)
            if (mPreferencesHelper.getCurrentUserId() != null) {
                builder.addHeader(USER_ID, mPreferencesHelper.getCurrentUserId().toString())
            }
            if (mPreferencesHelper.getAccessToken() != null) {
                builder.addHeader(ACCESS_TOKEN, mPreferencesHelper.getAccessToken().toString())
            }
            return chain.proceed(builder.build())
        }
        return null
    }
}