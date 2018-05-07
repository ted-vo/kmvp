package com.horical.kmvparchitect.ui.main.about

import android.content.Context
import com.horical.kmvparchitect.data.network.ApiService
import com.horical.kmvparchitect.data.prefs.PreferencesHelper
import com.horical.kmvparchitect.ui.base.BaseInteractor
import javax.inject.Inject

class AboutInteractor
@Inject
constructor(private val context: Context,
            mApiService: ApiService,
            mPreferencesHelper: PreferencesHelper) : BaseInteractor(mApiService, mPreferencesHelper), IAboutInteractor {
}