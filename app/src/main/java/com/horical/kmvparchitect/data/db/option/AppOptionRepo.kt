package com.horical.kmvparchitect.data.db.option

import com.horical.kmvparchitect.data.db.model.Option
import io.reactivex.Observable
import javax.inject.Inject

class AppOptionRepo @Inject constructor(private val optionDao: OptionDao) : OptionRepo {

    override fun getOptionsForQuestion(questionId: Long): Observable<List<Option>> = Observable.fromCallable {
        optionDao.loaAllByQuestionId(questionId)
    }

    override fun saveOption(option: Option): Observable<Boolean> = Observable.fromCallable {
        optionDao.insert(option)
        true
    }

    override fun saveOptionList(options: List<Option>): Observable<Boolean> = Observable.fromCallable {
        optionDao.insertAll(options)
        true
    }

    override fun isOptionEmpty(): Observable<Boolean> = Observable.fromCallable {
        optionDao.loadAll().isEmpty()
    }
}