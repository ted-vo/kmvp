package com.horical.kmvparchitect.ui.base

import com.horical.kmvparchitect.utils.rx.ScheduleProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : IBaseView, out I : IBaseInteractor>
constructor(protected val mInteractor: I,
            protected val mScheduleProvider: ScheduleProvider,
            protected val mCompositeDisposable: CompositeDisposable) {

    companion object {

        val TAG: String = BasePresenter::class.java.simpleName
    }

    protected lateinit var mBaseView: V

    fun onAttach(view: V) {
        mBaseView = view
    }

    fun onDetach() {
        mCompositeDisposable.dispose()
    }

    protected fun getView(): V = mBaseView
}
