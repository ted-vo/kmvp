package com.horical.kmvparchitect.ui.main

interface IMainPresenter {

    fun logout()

    fun loadQuestionCards()

    fun removeQuestionCard()

    fun onNavMenuCreated(): Pair<String?, String?>
}