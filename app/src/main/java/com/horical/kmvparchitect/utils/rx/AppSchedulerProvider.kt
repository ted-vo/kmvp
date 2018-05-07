package com.horical.kmvparchitect.utils.rx

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : ScheduleProvider {

    override fun <T> ioToMainObservableScheduler(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(io())
                    .observeOn(ui())
        }
    }

    override fun <T> ioToMainSingleScheduler(): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream.subscribeOn(io())
                    .observeOn(ui())
        }
    }

    override fun ioMainCompletableSchedulter(): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream.subscribeOn(io())
                    .observeOn(ui())
        }
    }

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}