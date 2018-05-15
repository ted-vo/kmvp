package com.horical.kmvparchitect.ui.feed.opensource

import com.horical.kmvparchitect.data.network.ApiHelper
import com.horical.kmvparchitect.data.prefs.PreferencesHelper
import com.horical.kmvparchitect.ui.base.BaseInteractor
import javax.inject.Inject

class OpenSourceInteractor
@Inject
constructor(mApiHelper: ApiHelper,
            mPreferencesHelper: PreferencesHelper)
    : BaseInteractor(mApiHelper, mPreferencesHelper), IOpenSourceContract.Interactor {

}