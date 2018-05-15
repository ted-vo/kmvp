package com.horical.kmvparchitect.ui.feed.blogs

import com.horical.kmvparchitect.ui.base.BasePresenter
import com.horical.kmvparchitect.utils.rx.ScheduleProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BlogPresenter
@Inject
constructor(mInteractor: BlogInteractor,
            mScheduleProvider: ScheduleProvider,
            mCompositeDispoable: CompositeDisposable)
    : BasePresenter<IBlogContract.View, BlogInteractor>(mInteractor, mScheduleProvider, mCompositeDispoable), IBlogContract.Presenter {

    override fun loadBlogData() {
        getView().showLoading()
        mCompositeDisposable.add(mInteractor
                .getBlog()
                .compose(mScheduleProvider.ioToMainObservableScheduler())
                .subscribe({
                    getView().hideLoading()
                    getView().getBlogSuccess(it.data!!.toMutableList())
                }, {
                    getView().hideLoading()
                    getView().handleError(null, it)
                }))

    }
}