package com.horical.kmvparchitect.ui.main.about

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AboutFragmentProvider {

    @ContributesAndroidInjector(modules = [AboutFragmentModule::class])
    abstract fun provideAboutFragmentFactory(): AboutFragment
}