package com.horical.kmvparchitect.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.horical.kmvparchitect.R
import com.horical.kmvparchitect.data.network.exception.RetrofitException
import com.horical.kmvparchitect.utils.CommonUtils
import com.horical.kmvparchitect.utils.NetworkUtils
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<P : BasePresenter<*, *>> : AppCompatActivity(), BaseFragment.Callback, IBaseView {

    @Inject
    protected lateinit var mPresenter: P
    private lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        AndroidInjection.inject(this)
        attachView()
        setUp()
    }

    override fun onDestroy() {
        mPresenter.onDetach()
        super.onDestroy()
    }

    abstract fun getLayoutId(): Int

    abstract fun attachView()

    abstract fun setUp()

    override fun handleError(errorCode: Int?, throwable: Throwable?) {}

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun showLoading() {
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    override fun hideLoading() {
        mProgressDialog.cancel()
    }

    override fun showMessage(message: String?) {
        if (message == null) {
            Toast.makeText(this, getString(R.string.default_error_some), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showMessage(resId: Int) {
        showMessage(getString(resId))
    }

    override fun openActivityOnTokenExpire() {

    }

    override fun isNetworkConnected(): Boolean = NetworkUtils.isNetworkConnected(this)

    override fun hideKeyboard() {
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    fun handleThrowable(throwable: Throwable?): String {
        if (throwable is RetrofitException) {
            return when (throwable.getKind()) {
                RetrofitException.Kind.HTTP_422_WITH_DATA ->
                    throwable.getErrorData()!!.message
                RetrofitException.Kind.HTTP ->
                    getString(R.string.default_error_message_http)
                RetrofitException.Kind.NETWORK ->
                    getString(R.string.default_error_message_network)
                RetrofitException.Kind.UNEXPECTED ->
                    getString(R.string.default_error_message_unexpected)
            }
        }
        return getString(R.string.default_error_message)
    }
}