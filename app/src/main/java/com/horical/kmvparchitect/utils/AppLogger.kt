package com.horical.kmvparchitect.utils

import com.horical.kmvparchitect.BuildConfig
import timber.log.Timber

class AppLogger {

    companion object {
        fun init() {
            if (BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
        }

        fun d(s: String, vararg any: Any) = Timber.d(s, any)

        fun d(throwable: Throwable, s: String, vararg any: Any) = Timber.d(throwable, s, any)

        fun i(s: String, vararg any: Array<out Any>) = Timber.i(s, any)

        fun i(throwable: Throwable, s: String, vararg any: Any) = Timber.i(throwable, s, any)

        fun w(s: String, vararg any: Any) = Timber.w(s, any)

        fun w(throwable: Throwable, s: String, vararg any: Any) = Timber.w(throwable, s, any)

        fun e(s: String, vararg any: Any) = Timber.e(s, any)

        fun e(throwable: Throwable, s: String, vararg any: Any) = Timber.e(throwable, s, any)
    }
}
