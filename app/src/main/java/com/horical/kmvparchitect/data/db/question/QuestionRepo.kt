package com.horical.kmvparchitect.data.db.question

import com.horical.kmvparchitect.data.db.model.Question
import io.reactivex.Observable

interface QuestionRepo {

    fun getAllQuestion(): Observable<List<Question>>

    fun saveQuestion(question: Question): Observable<Boolean>

    fun saveQuestions(questions: List<Question>): Observable<Boolean>

    fun isQuestionEmpty(): Observable<Boolean>
}