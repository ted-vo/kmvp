package com.horical.kmvparchitect.ui.main.about

import dagger.Binds
import dagger.Module

@Module
abstract class AboutFragmentModule {

    @Binds
    abstract fun bindAboutPresenter(presenter: AboutPresenter): IAboutPresenter

    @Binds
    abstract fun bindAboutInteractor(interactor: AboutInteractor): IAboutInteractor
}