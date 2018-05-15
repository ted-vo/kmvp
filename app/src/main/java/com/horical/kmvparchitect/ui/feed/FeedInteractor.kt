package com.horical.kmvparchitect.ui.feed

import com.horical.kmvparchitect.data.network.ApiHelper
import com.horical.kmvparchitect.data.prefs.PreferencesHelper
import com.horical.kmvparchitect.ui.base.BaseInteractor
import javax.inject.Inject

class FeedInteractor
@Inject
constructor(mApiHelper: ApiHelper,
            mPreferencesHelper: PreferencesHelper) : BaseInteractor(mApiHelper, mPreferencesHelper), IFeedContract.Interactor {

}