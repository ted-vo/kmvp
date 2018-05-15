package com.horical.kmvparchitect.ui.feed

import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v7.app.ActionBar
import android.view.MenuItem
import com.horical.kmvparchitect.R
import com.horical.kmvparchitect.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject

class FeedActivity : BaseActivity<FeedPresenter>(), IFeedContract.View, HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, FeedActivity::class.java)
        }
    }

    override fun attachView() = mPresenter.onAttach(this)

    override fun getLayoutId(): Int = R.layout.activity_feed

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val upIntent: Intent? = NavUtils.getParentActivityIntent(this)
                upIntent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                if (upIntent != null) {
                    if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                        TaskStackBuilder.create(this)
                                .addNextIntentWithParentStack(upIntent)
                                .startActivities()
                    } else {
                        NavUtils.navigateUpTo(this, upIntent)
                    }
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setUp() {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
            (supportActionBar as ActionBar).setDisplayShowHomeEnabled(true)
        }

        val pagerAdapter = FeedPagerAdapter(supportFragmentManager)
        pagerAdapter.count = 2

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.blog)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.open_source)))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                feedViewPager.currentItem = tab.position
            }
        })

        feedViewPager.adapter = pagerAdapter
        feedViewPager.offscreenPageLimit = tabLayout.tabCount
        feedViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    }
}