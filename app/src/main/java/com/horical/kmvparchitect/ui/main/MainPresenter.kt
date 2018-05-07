package com.horical.kmvparchitect.ui.main

import com.horical.kmvparchitect.ui.base.BasePresenter
import com.horical.kmvparchitect.utils.rx.ScheduleProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter
@Inject
constructor(mInteractor: IMainInteractor,
            mScheduleProvider: ScheduleProvider,
            mCompositeDispoable: CompositeDisposable)
    : BasePresenter<IMainView, IMainInteractor>(mInteractor, mScheduleProvider, mCompositeDispoable), IMainPresenter {

    val mQuestionCards: MutableCollection<QuestionCardData> = arrayListOf()

    override fun logout() {
        getView().showLoading()
        mCompositeDisposable.add(mInteractor
                .logout()
                .compose(mScheduleProvider.ioToMainObservableScheduler())
                .subscribe({
                    mInteractor.performUserLogout()
                    getView().hideLoading()
                    getView().openLoginActivity()
                }, {
                    getView().hideLoading()
                    getView().handleError(null, it)
                })
        )
    }

    override fun loadQuestionCards() {
        getView().showLoading()
        mCompositeDisposable.add(mInteractor
                .getAllQuestions()
                .compose(mScheduleProvider.ioToMainObservableScheduler())
                .subscribe({
                    if (it != null) {
                        mQuestionCards.clear()
                        mQuestionCards.addAll(it)
                    }
                    getView().hideLoading()
                    getView().getQuestionsSuccess();
                }, {
                    getView().hideLoading()
                    getView().handleError(null, it)
                }))
    }

    override fun removeQuestionCard() {
        mQuestionCards.remove(mQuestionCards.first())
    }

    override fun onNavMenuCreated(): Pair<String?, String?> = mInteractor.getUserInfo()
}