package com.horical.kmvparchitect.ui.feed.opensource

import dagger.Binds
import dagger.Module

@Module
abstract class OpenSourceFragmentModule {

    @Binds
    abstract fun bindOpenSourcePresenter(openSourcePresenter: OpenSourcePresenter): IOpenSourceContract.Presenter

    @Binds
    abstract fun bindOpenSourceInteractor(openSourceInteractor: OpenSourceInteractor): IOpenSourceContract.Interactor
}