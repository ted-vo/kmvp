package com.horical.kmvparchitect.data.db.option

import com.horical.kmvparchitect.data.db.model.Option
import io.reactivex.Observable

interface OptionRepo {

    fun getOptionsForQuestion(questionId: Long): Observable<List<Option>>

    fun saveOption(option: Option): Observable<Boolean>

    fun saveOptionList(options: List<Option>): Observable<Boolean>

    fun isOptionEmpty(): Observable<Boolean>
}