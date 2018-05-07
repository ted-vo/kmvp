package com.horical.kmvparchitect.ui.main.about

import com.horical.kmvparchitect.ui.base.BasePresenter
import com.horical.kmvparchitect.utils.rx.ScheduleProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AboutPresenter
@Inject
constructor(mInteractor: IAboutInteractor,
            mScheduleProvider: ScheduleProvider,
            mCompositeDisposable: CompositeDisposable)
    : BasePresenter<IAboutView, IAboutInteractor>(mInteractor, mScheduleProvider, mCompositeDisposable), IAboutPresenter {


}