package com.horical.kmvparchitect.ui.main.rating

import dagger.Binds
import dagger.Module

@Module
abstract class RateUsDialogModule {

    @Binds
    abstract fun bindRateUsPresenter(rateUsPresenter: RateUsPresenter): IRateUsPresenter

    @Binds
    abstract fun bindRateUsIteractor(rateUsInteractor: RateUsInteractor): IRateUsInteractor
}
