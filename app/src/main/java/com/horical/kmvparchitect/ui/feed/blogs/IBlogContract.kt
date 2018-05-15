package com.horical.kmvparchitect.ui.feed.blogs

import com.horical.kmvparchitect.data.db.model.Blog
import com.horical.kmvparchitect.data.network.response.BlogResponse
import com.horical.kmvparchitect.ui.base.IBaseInteractor
import com.horical.kmvparchitect.ui.base.IBaseView
import io.reactivex.Observable

interface IBlogContract {

    interface View : IBaseView {

        fun getBlogSuccess(blogList: MutableList<Blog>)
    }

    interface Presenter {
        fun loadBlogData()
    }

    interface Interactor : IBaseInteractor {

        fun getBlog(): Observable<BlogResponse>
    }
}