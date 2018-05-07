package com.horical.kmvparchitect.data.db.question

import com.horical.kmvparchitect.data.db.model.Question
import io.reactivex.Observable
import javax.inject.Inject

class AppQuestionRepo @Inject constructor(private val questionDao: QuestionDao) : QuestionRepo {

    override fun getAllQuestion(): Observable<List<Question>> = Observable.fromCallable({
        questionDao.loadAll()
    })

    override fun saveQuestion(question: Question): Observable<Boolean> = Observable.fromCallable({
        questionDao.insert(question)
        true
    })

    override fun saveQuestions(questions: List<Question>): Observable<Boolean> = Observable.fromCallable({
        questionDao.insertAll(questions)
        true
    })

    override fun isQuestionEmpty(): Observable<Boolean> = Observable.fromCallable({
        questionDao.loadAll().isEmpty()
    })
}