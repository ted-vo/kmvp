package com.horical.kmvparchitect.ui.feed

import dagger.Binds
import dagger.Module

@Module
abstract class FeedActivityModule {

    @Binds
    abstract fun bindFeedPresenter(feedPresenter: FeedPresenter): IFeedContract.Presenter

    @Binds
    abstract fun bindFeedInteractor(interactor: FeedInteractor): IFeedContract.Interactor
}