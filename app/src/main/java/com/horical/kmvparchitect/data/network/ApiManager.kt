package com.horical.kmvparchitect.data.network

import com.horical.kmvparchitect.data.network.request.LoginRequest
import com.horical.kmvparchitect.data.network.response.BlogResponse
import com.horical.kmvparchitect.data.network.response.LoginResponse
import com.horical.kmvparchitect.data.network.response.LogoutResponse
import com.horical.kmvparchitect.data.network.response.OpenSourceResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class ApiManager @Inject constructor(retrofit: Retrofit) : ApiService {

    private val mApiService = retrofit.create(ApiService::class.java)

    override fun doFacebookLoginApiCall(request: LoginRequest.FacebookLoginRequest): Observable<LoginResponse> {
        return mApiService.doFacebookLoginApiCall(request)
    }

    override fun doGoogleLoginApiCall(request: LoginRequest.GoogleLoginRequest): Observable<LoginResponse> {
        return mApiService.doGoogleLoginApiCall(request)
    }

    override fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Observable<LoginResponse> {
        return mApiService.doServerLoginApiCall(request)
    }

    override fun doLogoutApiCall(): Observable<LogoutResponse> {
        return mApiService.doLogoutApiCall()
    }

    override fun getBlogApiCall(): Observable<BlogResponse> {
        return mApiService.getBlogApiCall()
    }

    override fun getOpenSourceApiCall(): Observable<OpenSourceResponse> {
        return mApiService.getOpenSourceApiCall()
    }
}