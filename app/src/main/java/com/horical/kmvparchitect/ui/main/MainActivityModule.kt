package com.horical.kmvparchitect.ui.main

import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindMainPresener(presenter: MainPresenter): IMainPresenter

    @Binds
    abstract fun bindMainInteractor(interactor: MainInteractor): IMainInteractor
}