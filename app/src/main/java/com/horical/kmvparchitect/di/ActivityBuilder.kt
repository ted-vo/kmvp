package com.horical.kmvparchitect.di

import com.horical.kmvparchitect.ui.feed.FeedActivity
import com.horical.kmvparchitect.ui.feed.FeedActivityModule
import com.horical.kmvparchitect.ui.feed.blogs.BlogFragmentProvider
import com.horical.kmvparchitect.ui.feed.opensource.OpenSourceFragmentProvider
import com.horical.kmvparchitect.ui.login.LoginActivity
import com.horical.kmvparchitect.ui.login.LoginActivityModule
import com.horical.kmvparchitect.ui.main.MainActivity
import com.horical.kmvparchitect.ui.main.MainActivityModule
import com.horical.kmvparchitect.ui.main.about.AboutFragmentProvider
import com.horical.kmvparchitect.ui.main.rating.RateUsDialogProvider
import com.horical.kmvparchitect.ui.splash.SplashActivity
import com.horical.kmvparchitect.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class, AboutFragmentProvider::class, RateUsDialogProvider::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [FeedActivityModule::class, BlogFragmentProvider::class, OpenSourceFragmentProvider::class])
    abstract fun bindFeedActivity(): FeedActivity
}