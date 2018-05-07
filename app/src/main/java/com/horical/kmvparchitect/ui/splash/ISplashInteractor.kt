package com.horical.kmvparchitect.ui.splash

import com.horical.kmvparchitect.data.db.model.Question
import com.horical.kmvparchitect.ui.base.IBaseInteractor
import io.reactivex.Observable

interface ISplashInteractor : IBaseInteractor {

    fun seedQuestions(): Observable<Boolean>

    fun seedOptions(): Observable<Boolean>

    fun getQuestions(): Observable<List<Question>>
}
