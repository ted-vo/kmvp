package com.horical.kmvparchitect.ui.feed

import com.horical.kmvparchitect.ui.base.BasePresenter
import com.horical.kmvparchitect.utils.rx.ScheduleProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FeedPresenter
@Inject
constructor(mInteractor: IFeedContract.Interactor,
            mScheduleProvider: ScheduleProvider,
            mCompositeDispoable: CompositeDisposable)
    : BasePresenter<IFeedContract.View, IFeedContract.Interactor>(mInteractor, mScheduleProvider, mCompositeDispoable), IFeedContract.Presenter {


}