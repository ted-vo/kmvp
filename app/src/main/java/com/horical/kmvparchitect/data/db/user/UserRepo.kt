package com.horical.kmvparchitect.data.db.user

import com.horical.kmvparchitect.data.db.model.User
import io.reactivex.Observable

interface UserRepo {

    fun getAllUsers(): Observable<List<User>>

    fun insertUser(user: User): Observable<Boolean>
}