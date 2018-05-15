package com.horical.kmvparchitect.ui.feed.blogs

import android.content.Context
import com.horical.kmvparchitect.data.network.ApiHelper
import com.horical.kmvparchitect.data.network.response.BlogResponse
import com.horical.kmvparchitect.data.prefs.PreferencesHelper
import com.horical.kmvparchitect.ui.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class BlogInteractor
@Inject
constructor(private val context: Context,
            mApiHelper: ApiHelper,
            mPreferencesHelper: PreferencesHelper) : BaseInteractor(mApiHelper, mPreferencesHelper), IBlogContract.Interactor {

    override fun getBlog(): Observable<BlogResponse> = mApiHelper.getBlogApiCall()
}