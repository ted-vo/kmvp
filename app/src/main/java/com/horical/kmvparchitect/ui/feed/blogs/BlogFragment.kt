package com.horical.kmvparchitect.ui.feed.blogs

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.horical.kmvparchitect.R
import com.horical.kmvparchitect.data.db.model.Blog
import com.horical.kmvparchitect.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment : BaseFragment<BlogPresenter>(), IBlogContract.View {

    companion object {

        internal val TAG: String = BlogFragment::class.java.simpleName
        fun newInstance(): BlogFragment {
            val args = Bundle()
            val fragment = BlogFragment()
            fragment.arguments = args
            return fragment
        }

    }

    lateinit var mBlogAdapter: BlogAdapter

    override fun getLayoutId(): Int = R.layout.fragment_blog

    override fun attachView() = mPresenter.onAttach(this)

    override fun setUp() {
        mBlogAdapter = BlogAdapter(arrayListOf())
        blogRecyclerView.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false)
        blogRecyclerView.itemAnimator = DefaultItemAnimator()
        blogRecyclerView.adapter = mBlogAdapter

        blogSwipeRefreshLayout.setOnRefreshListener {
            mPresenter.loadBlogData()
        }

        mPresenter.loadBlogData()
    }

    override fun getBlogSuccess(blogList: MutableList<Blog>) {
        blogSwipeRefreshLayout.isRefreshing = false
        mBlogAdapter.clearItems()
        mBlogAdapter.addItems(blogList)
    }
}
