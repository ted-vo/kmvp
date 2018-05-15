package com.horical.kmvparchitect.ui.feed

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.horical.kmvparchitect.ui.feed.blogs.BlogFragment
import com.horical.kmvparchitect.ui.feed.opensource.OpenSourceFragment

class FeedPagerAdapter constructor(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private var mTabCount: Int = 0

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> BlogFragment.newInstance()
            1 -> OpenSourceFragment.newInstance()
            else -> null
        }
    }

    override fun getCount(): Int = mTabCount

    fun setCount(count: Int) {
        mTabCount = count
    }
}