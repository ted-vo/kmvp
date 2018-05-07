package com.horical.kmvparchitect.ui.base

import android.support.annotation.StringRes

interface IBaseView {

    fun showLoading();

    fun hideLoading();

    fun handleError(errorCode: Int?, throwable: Throwable?)

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)

    fun openActivityOnTokenExpire();

    fun isNetworkConnected(): Boolean

    fun hideKeyboard()
}