package com.horical.kmvparchitect.ui.login

import dagger.Binds
import dagger.Module

@Module
abstract class LoginActivityModule {

    @Binds
    abstract fun bindLoginPresenter(loginPresenter: LoginPresenter): ILoginPresenter

    @Binds
    abstract fun bindLoginInteractor(interactor: LoginInteractor): ILoginInteractor
}