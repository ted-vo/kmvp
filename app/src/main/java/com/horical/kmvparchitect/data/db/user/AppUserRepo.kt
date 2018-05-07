package com.horical.kmvparchitect.data.db.user

import com.horical.kmvparchitect.data.db.model.User
import io.reactivex.Observable
import javax.inject.Inject

class AppUserRepo @Inject constructor(private var userDao: UserDao) : UserRepo {

    override fun getAllUsers(): Observable<List<User>> = Observable.fromCallable {
        userDao.loadAll()
    }

    override fun insertUser(user: User): Observable<Boolean> = Observable.fromCallable {
        userDao.insert(user)
        true
    }

    /**
    override fun getAllUsers(): Observable<List<User>> {
    return Observable.fromCallable { userDao.loadAll() }
    }
     */

    /**
    override fun insertUser(user: User): Observable<Boolean> {
    return Observable.fromCallable {
    userDao.insert(user)
    true
    }
    }
     */
}