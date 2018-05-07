package com.horical.kmvparchitect.ui.splash

import android.content.Context
import android.content.Intent
import com.horical.kmvparchitect.R
import com.horical.kmvparchitect.ui.base.BaseActivity
import com.horical.kmvparchitect.ui.login.LoginActivity
import com.horical.kmvparchitect.ui.main.MainActivity

class SplashActivity : BaseActivity<SplashPresenter>(), ISplashView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SplashActivity::class.java)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun attachView() = mPresenter.onAttach(this)

    override fun setUp() {
        mPresenter.startSeeding()
    }

    override fun handleError(errorCode: Int?, throwable: Throwable?) {
        handleThrowable(throwable)
    }

    override fun openLoginActivity() {
        startActivity(LoginActivity.newIntern(this))
        finish()
    }

    override fun openMainActivity() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }
}