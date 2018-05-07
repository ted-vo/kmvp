package com.horical.kmvparchitect.ui.splash

import com.horical.kmvparchitect.ui.base.BasePresenter
import com.horical.kmvparchitect.utils.rx.ScheduleProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter
@Inject
constructor(mInteractor: ISplashInteractor,
            mScheduleProvider: ScheduleProvider,
            mCompositeDisposable: CompositeDisposable)
    : BasePresenter<ISplashView, ISplashInteractor>(mInteractor, mScheduleProvider, mCompositeDisposable), ISplashPresenter {

    override fun startSeeding() {
        mInteractor.let {
            mCompositeDisposable.add(it.seedQuestions()
                    .flatMap {
                        mInteractor.seedOptions()
                    }
                    .compose(mScheduleProvider.ioToMainObservableScheduler())
                    .subscribe({
                        decideActivityToOpen()
                    })
            )
        }
    }

    private fun decideActivityToOpen() = getView().let {
        if (mInteractor.isUserLoggedIn()) {
            it.openMainActivity()
        } else {
            it.openLoginActivity()
        }
    }
}