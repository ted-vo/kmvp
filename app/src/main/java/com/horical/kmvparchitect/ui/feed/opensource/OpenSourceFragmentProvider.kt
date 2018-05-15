package com.horical.kmvparchitect.ui.feed.opensource

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class OpenSourceFragmentProvider {

    @ContributesAndroidInjector(modules = [OpenSourceFragmentModule::class])
    abstract fun provideOpenSourceFragmentFactory(): OpenSourceFragment
}