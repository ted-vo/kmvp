package com.horical.kmvparchitect.ui.main.rating

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RateUsDialogProvider {

    @ContributesAndroidInjector(modules = [RateUsDialogModule::class])
    abstract fun provideRateUsDialogFactory(): RateUsDialog
}