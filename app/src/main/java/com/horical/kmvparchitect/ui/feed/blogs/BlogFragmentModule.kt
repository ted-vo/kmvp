package com.horical.kmvparchitect.ui.feed.blogs

import dagger.Binds
import dagger.Module

@Module
abstract class BlogFragmentModule {

    @Binds
    abstract fun bindBlogPresenter(blogPresenter: BlogPresenter): IBlogContract.Presenter

    @Binds
    abstract fun bindBlogInteractor(blogInteractor: BlogInteractor): IBlogContract.Interactor
}