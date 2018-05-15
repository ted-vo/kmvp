package com.horical.kmvparchitect.data.network

import com.horical.kmvparchitect.data.network.request.LoginRequest
import com.horical.kmvparchitect.data.network.response.BlogResponse
import com.horical.kmvparchitect.data.network.response.LoginResponse
import com.horical.kmvparchitect.data.network.response.LogoutResponse
import com.horical.kmvparchitect.data.network.response.OpenSourceResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class ApiManager @Inject constructor(retrofit: Retrofit) : ApiHelper {

    private val mApiHelper = retrofit.create(ApiHelper::class.java)

    override fun doFacebookLoginApiCall(request: LoginRequest.FacebookLoginRequest): Observable<LoginResponse> {
        return mApiHelper.doFacebookLoginApiCall(request)
    }

    override fun doGoogleLoginApiCall(request: LoginRequest.GoogleLoginRequest): Observable<LoginResponse> {
        return mApiHelper.doGoogleLoginApiCall(request)
    }

    override fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Observable<LoginResponse> {
        return mApiHelper.doServerLoginApiCall(request)
    }

    override fun doLogoutApiCall(): Observable<LogoutResponse> {
        return mApiHelper.doLogoutApiCall()
    }

    override fun getBlogApiCall(): Observable<BlogResponse> {
        return mApiHelper.getBlogApiCall()
    }

    override fun getOpenSourceApiCall(): Observable<OpenSourceResponse> {
        return mApiHelper.getOpenSourceApiCall()
    }
}