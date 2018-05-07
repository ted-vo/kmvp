package com.horical.kmvparchitect.data.network.exception

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException

class RetrofitException(
        private val mMessage: String?,
        private val mUrl: String?,
        private val mResponse: Response<*>?,
        private val mKind: Kind,
        private val mException: Throwable?,
        private val mRetrofit: Retrofit?) : RuntimeException(mMessage, mException) {

    private var mErrorData: ServerError? = null

    companion object {
        fun httpError(url: String, response: Response<*>, retrofit: Retrofit): RetrofitException {
            val message = response.code().toString() + " " + response.message()
            return RetrofitException(message, url, response, Kind.HTTP, null, retrofit)
        }

        fun httpErrorWithObject(url: String, response: Response<*>, retrofit: Retrofit): RetrofitException {
            val message = response.code().toString() + " " + response.message()
            val error = RetrofitException(message, url, response, Kind.HTTP_422_WITH_DATA, null, retrofit)
            error.deserializeServerError()
            return error
        }

        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(exception.message, null, null, Kind.NETWORK, exception, null)
        }

        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(exception.message, null, null, Kind.UNEXPECTED, exception, null)
        }
    }

    /** The request URL which produced the error. */
    fun getUrl() = mUrl

    /** Response object containing status code, headers, body, etc. */
    fun getResponse() = mResponse

    /** The event kind which triggered this error. */
    fun getKind() = mKind

    /** The Retrofit this request was executed on */
    fun getRetrofit() = mRetrofit

    /** The data returned from the server in the response body*/
    fun getErrorData(): ServerError? = mErrorData

    private fun deserializeServerError() {
        if (mResponse?.errorBody() != null) {
            try {
                mErrorData = getErrorBodyAs(ServerError::class.java)
            } catch (e: IOException) {
                Timber.tag("deserializeServerError").e(e)
            }
        }
    }

    /**
     * HTTP response body converted to specified `type`. `null` if there is no
     * response.
     * @throws IOException if unable to convert the body to the specified `type`.
     */
    @Throws(IOException::class)
    private fun <T> getErrorBodyAs(type: Class<T>): T? {
        if (mResponse?.errorBody() == null || mRetrofit == null) return null
        val converter: Converter<ResponseBody, T> =
                mRetrofit.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
        return converter.convert(mResponse.errorBody()
                ?: throw IllegalArgumentException("Null Response"))
    }

    enum class Kind {
        /** An [IOException] occurred while communicating to the server.  */
        NETWORK,
        /** A non-200 HTTP status code was received from the server.  */
        HTTP,
        HTTP_422_WITH_DATA,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }
}