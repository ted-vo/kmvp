package com.horical.kmvparchitect.ui.splash

import dagger.Binds
import dagger.Module

@Module
abstract class SplashActivityModule {

    @Binds
    abstract fun bindSplashPresenter(splashPresenter: SplashPresenter): ISplashPresenter

    @Binds
    abstract fun bindSplashInteractor(splashInteractor: SplashInteractor): ISplashInteractor
}