package com.horical.kmvparchitect.data.network.exception

import io.reactivex.*
import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

class RxErrorHandlingCallAdapterFactory private constructor() : CallAdapter.Factory() {

    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    companion object {
        fun create(): CallAdapter.Factory = RxErrorHandlingCallAdapterFactory()
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *> {
        val wrapped = original.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
        return RxCallAdapterWrapper(retrofit, wrapped)
    }

    private class RxCallAdapterWrapper<R>(private val retrofit: Retrofit, private val wrapped: CallAdapter<R, *>) : CallAdapter<R, Any> {

        override fun responseType(): Type {
            return wrapped.responseType()
        }

        override fun adapt(call: Call<R>): Any {
            val adaptedCall = wrapped.adapt(call)

            if (adaptedCall is Completable) {
                return adaptedCall.onErrorResumeNext {
                    Completable.error(asRetrofitException(it))
                }
            }

            if (adaptedCall is Single<*>) {
                return adaptedCall.onErrorResumeNext {
                    Single.error(asRetrofitException(it))
                }
            }

            if (adaptedCall is Observable<*>) {
                return adaptedCall.onErrorResumeNext(Function {
                    Observable.error(asRetrofitException(it))
                })
            }

            if (adaptedCall is Flowable<*>) {
                return adaptedCall.onErrorResumeNext(Function {
                    Flowable.error(asRetrofitException(it))
                })
            }

            if (adaptedCall is Maybe<*>) {
                return adaptedCall.onErrorResumeNext(Function {
                    Maybe.error(asRetrofitException(it))
                })
            }

            throw RuntimeException("Observable Type not supported")
        }

        private fun asRetrofitException(throwable: Throwable): RetrofitException {
            // We had non-200 http error
            if (throwable is HttpException) {
                val response = throwable.response()

                if (throwable.code() == 422) {
                    // on out api 422's get metadata in the response. Adjust logic here based on your needs
                    return RetrofitException.httpErrorWithObject(response.raw().request().url().toString(), response, retrofit)
                } else {
                    return RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit)
                }
            }

            // A network error happened
            if (throwable is IOException) {
                return RetrofitException.networkError(throwable)
            }

            // We don't know what happened. We need to simply convert to an unknown error
            return RetrofitException.unexpectedError(throwable)
        }
    }

}