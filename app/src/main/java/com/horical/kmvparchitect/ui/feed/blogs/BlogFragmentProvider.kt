package com.horical.kmvparchitect.ui.feed.blogs

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BlogFragmentProvider {

    @ContributesAndroidInjector(modules = [BlogFragmentModule::class])
    abstract fun provideBlogFragmentFactory(): BlogFragment
}