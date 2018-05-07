package com.horical.kmvparchitect.ui.base

interface IBaseInteractor {

    fun isUserLoggedIn(): Boolean

    fun performUserLogout()
}