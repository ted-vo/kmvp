package com.horical.kmvparchitect.data.network

import com.horical.kmvparchitect.data.network.request.LoginRequest
import com.horical.kmvparchitect.data.network.response.BlogResponse
import com.horical.kmvparchitect.data.network.response.LoginResponse
import com.horical.kmvparchitect.data.network.response.LogoutResponse
import com.horical.kmvparchitect.data.network.response.OpenSourceResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiHelper {

    @POST("588d15d3100000ae072d2944")
    fun doFacebookLoginApiCall(@Body request: LoginRequest.FacebookLoginRequest): Observable<LoginResponse>

    @POST("588d14f4100000a9072d2943")
    fun doGoogleLoginApiCall(@Body request: LoginRequest.GoogleLoginRequest): Observable<LoginResponse>

    @POST("588d15f5100000a8072d2945")
    fun doServerLoginApiCall(@Body request: LoginRequest.ServerLoginRequest): Observable<LoginResponse>

    @POST("588d161c100000a9072d2946")
    fun doLogoutApiCall(): Observable<LogoutResponse>

    @GET("5926ce9d11000096006ccb30")
    fun getBlogApiCall(): Observable<BlogResponse>

    @GET("5926c34212000035026871cd")
    fun getOpenSourceApiCall(): Observable<OpenSourceResponse>
}