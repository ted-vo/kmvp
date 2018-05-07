package com.horical.kmvparchitect.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.horical.kmvparchitect.BuildConfig
import com.horical.kmvparchitect.R
import com.horical.kmvparchitect.ui.base.BaseActivity
import com.horical.kmvparchitect.ui.login.LoginActivity
import com.horical.kmvparchitect.ui.main.about.AboutFragment
import com.horical.kmvparchitect.utils.ScreenUtils
import com.horical.kmvparchitect.utils.ViewAnimationsUtils
import com.mindorks.placeholderview.SwipeDecor
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), IMainView, HasSupportFragmentInjector {

    @Inject
    lateinit var mFragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mFragmentDispatchingAndroidInjector

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun attachView() = mPresenter.onAttach(this)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val drawable = item.icon
        if (drawable is Animatable) {
            drawable.start()
        }
        when (item.itemId) {
            R.id.action_cut -> return true
            R.id.action_copy -> return true
            R.id.action_share -> return true
            R.id.action_delete -> return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setUp() {
        setupNavigationView()
        setupCardContainerView()
    }

    override fun handleError(errorCode: Int?, throwable: Throwable?) {
        handleThrowable(throwable)
    }

    override fun onFragmentDetached(tag: String) {
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commit()
            unlockDrawer()
        }
    }

    override fun onBackPressed() {
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(AboutFragment.TAG)
        if (fragment == null) {
            super.onBackPressed()
        } else {
            onFragmentDetached(AboutFragment.TAG)
        }
    }

    override fun openLoginActivity() {
        startActivity(LoginActivity.newIntern(this))
        finish()
    }

    override fun getQuestionsSuccess() {
        cardsContainer.removeAllViews()
        for (mQuestionCard in mPresenter.mQuestionCards) {
            if (mQuestionCard.options.size == 3) {
                cardsContainer.addView(QuestionCard(cardsContainer.context, mQuestionCard))
            }
        }
        ViewAnimationsUtils.scaleAnimateView(cardsContainer)
    }

    private fun setupNavigationView() {
        setSupportActionBar(toolbar)
        val drawerToggle = object : ActionBarDrawerToggle(
                this,
                drawerView,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer) {

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                hideKeyboard()
            }
        }
        drawerView.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        val navHeader = layoutInflater.inflate(R.layout.nav_header_main, navigationView, false)
        navigationView.addHeaderView(navHeader)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navItemAbout -> {
                    showAboutFragment()
                    true
                }
                R.id.navItemRateUs -> {

                    true
                }
                R.id.navItemFeed -> {

                    true
                }
                R.id.navItemLogout -> {
                    mPresenter.logout()
                    true
                }
                else -> {
                    false
                }
            }
        }

        tvAppVersion.text = String.format(getString(R.string.version) + " " + BuildConfig.VERSION_NAME)
        val userInfo = mPresenter.onNavMenuCreated()
        navHeader.tvEmail.text = userInfo.first
        navHeader.tvName.text = userInfo.second
    }

    private fun setupCardContainerView() {
        val screenWidth = ScreenUtils.getScreenWidth(this)
        val screenHeight = ScreenUtils.getScreenHeight(this)

        cardsContainer.builder
                .setDisplayViewCount(3)
                .setHeightSwipeDistFactor(10f)
                .setWidthSwipeDistFactor(5f)
                .setSwipeDecor(SwipeDecor()
                        .setViewWidth((0.9 * screenWidth).toInt())
                        .setViewHeight((0.75 * screenHeight).toInt())
                        .setPaddingTop(20)
                        .setSwipeRotationAngle(10)
                        .setRelativeScale(0.01f))

        cardsContainer.addItemRemoveListener {
            if (it == 0) {
                Handler(mainLooper).postDelayed({
                    mPresenter.loadQuestionCards()
                }, 800)
            } else {
                mPresenter.removeQuestionCard()
            }
        }

        mPresenter.loadQuestionCards()
    }

    private fun unlockDrawer() {
        drawerView.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    private fun lockDrawer() {
        drawerView.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun showAboutFragment() {
        lockDrawer()
        supportFragmentManager.beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.clRootView, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit()
    }

    private fun showRateUsFragment() {

    }
}
