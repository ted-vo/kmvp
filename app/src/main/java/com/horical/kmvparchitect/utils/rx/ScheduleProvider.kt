package com.horical.kmvparchitect.utils.rx

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer

interface ScheduleProvider {

    fun <T> ioToMainObservableScheduler(): ObservableTransformer<T, T>

    fun <T> ioToMainSingleScheduler(): SingleTransformer<T, T>

    fun ioMainCompletableSchedulter(): CompletableTransformer

    fun io(): Scheduler

    fun ui(): Scheduler
}