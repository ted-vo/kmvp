package com.horical.kmvparchitect.ui.feed.opensource

import com.horical.kmvparchitect.ui.base.BasePresenter
import com.horical.kmvparchitect.utils.rx.ScheduleProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class OpenSourcePresenter
@Inject
constructor(mInteractor: IOpenSourceContract.Interactor,
            mScheduleProvider: ScheduleProvider,
            mCompositeDispoable: CompositeDisposable)
    : BasePresenter<IOpenSourceContract.View, IOpenSourceContract.Interactor>(mInteractor, mScheduleProvider, mCompositeDispoable), IOpenSourceContract.Presenter {

}