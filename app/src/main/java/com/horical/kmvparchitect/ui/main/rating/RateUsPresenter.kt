package com.horical.kmvparchitect.ui.main.rating

import com.horical.kmvparchitect.ui.base.BasePresenter
import com.horical.kmvparchitect.utils.rx.ScheduleProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RateUsPresenter
@Inject
constructor(mInteractor: IRateUsInteractor,
            mScheduleProvider: ScheduleProvider,
            mCompositeDisposable: CompositeDisposable)
    : BasePresenter<IRateUsView, IRateUsInteractor>(mInteractor, mScheduleProvider, mCompositeDisposable), IRateUsPresenter {

}