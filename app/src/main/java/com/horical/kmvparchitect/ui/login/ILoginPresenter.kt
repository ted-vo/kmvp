package com.horical.kmvparchitect.ui.login

interface ILoginPresenter {

    fun onServerLoginClick(email: String, password: String)

    fun onFBLoginClick()

    fun onGoogleLoginClick()
}