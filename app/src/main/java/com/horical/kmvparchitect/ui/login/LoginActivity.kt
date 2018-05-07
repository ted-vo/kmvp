package com.horical.kmvparchitect.ui.login

import android.content.Context
import android.content.Intent
import butterknife.OnClick
import com.horical.kmvparchitect.R
import com.horical.kmvparchitect.R.id.*
import com.horical.kmvparchitect.ui.base.BaseActivity
import com.horical.kmvparchitect.ui.main.MainActivity
import com.horical.kmvparchitect.utils.AppConstants
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter>(), ILoginView {

    companion object {

        fun newIntern(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }

    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun attachView() = mPresenter.onAttach(this)

    override fun setUp() {
        btnServerLogin.setOnClickListener {
            mPresenter.onServerLoginClick(edtEmail.text.toString(), edtPassword.text.toString())
        }

        ibFbLogin.setOnClickListener { mPresenter.onFBLoginClick() }

        ibGoogleLogin.setOnClickListener { mPresenter.onGoogleLoginClick() }
    }

    override fun handleError(errorCode: Int?, throwable: Throwable?) {
        when (errorCode) {
            AppConstants.EMPTY_EMAIL_ERROR -> showMessage(getString(R.string.empty_email_error_message))
            AppConstants.INVALID_EMAIL_ERROR -> showMessage(getString(R.string.invalid_email_error_message))
            AppConstants.EMPTY_PASSWORD_ERROR -> showMessage(getString(R.string.empty_password_error_message))
            AppConstants.LOGIN_FAILURE -> showMessage(getString(R.string.login_failure))
            else -> {
                showMessage(handleThrowable(throwable))
            }
        }
    }

    override fun openMainActivity() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }
}
