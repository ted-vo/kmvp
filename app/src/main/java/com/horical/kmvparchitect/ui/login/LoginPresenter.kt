package com.horical.kmvparchitect.ui.login

import com.horical.kmvparchitect.ui.base.BasePresenter
import com.horical.kmvparchitect.utils.AppConstants
import com.horical.kmvparchitect.utils.extension.isEmail
import com.horical.kmvparchitect.utils.rx.ScheduleProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginPresenter
@Inject
constructor(mInteractor: ILoginInteractor,
            mScheduleProvider: ScheduleProvider,
            mCompositeDisposable: CompositeDisposable)
    : BasePresenter<ILoginView, ILoginInteractor>(mInteractor, mScheduleProvider, mCompositeDisposable), ILoginPresenter {

    override fun onServerLoginClick(email: String, password: String) {
        when {
            email.isEmpty() -> getView().handleError(AppConstants.EMPTY_EMAIL_ERROR, null)
            !email.isEmail() -> getView().handleError(AppConstants.INVALID_EMAIL_ERROR, null)
            password.isEmpty() -> getView().handleError(AppConstants.EMPTY_PASSWORD_ERROR, null)
            else -> {
                getView().showLoading()
                mInteractor.let {
                    mCompositeDisposable.add(
                            it.doServerLoginApiCall(email, password)
                                    .compose(mScheduleProvider.ioToMainObservableScheduler())
                                    .subscribe({
                                        mInteractor.updateUserInSharedPreferences(it, AppConstants.LoggedInMode.LOGGED_IN_MODE_SERVER)
                                        getView().openMainActivity()
                                        getView().hideLoading()
                                    }, {
                                        getView().handleError(null, it)
                                        getView().hideLoading()
                                    }))
                }
            }
        }
    }

    override fun onFBLoginClick() {
        getView().showLoading()
        mInteractor.let {
            it.doFBLoginApiCall()
                    .compose(mScheduleProvider.ioToMainObservableScheduler())
                    .subscribe({
                        mInteractor.updateUserInSharedPreferences(it, AppConstants.LoggedInMode.LOGGED_IN_MODE_SERVER)
                        getView().openMainActivity()
                        getView().hideLoading()
                    }, {
                        getView().handleError(null, it)
                        getView().hideLoading()
                    })
        }
    }

    override fun onGoogleLoginClick() {
        getView().showLoading()
        mInteractor.let {
            it.doGoogleLoginApiCall()
                    .compose(mScheduleProvider.ioToMainObservableScheduler())
                    .subscribe({
                        mInteractor.updateUserInSharedPreferences(it, AppConstants.LoggedInMode.LOGGED_IN_MODE_SERVER)
                        getView().openMainActivity()
                        getView().hideLoading()
                    }, {
                        getView().handleError(null, it)
                        getView().hideLoading()
                    })
        }
    }
}