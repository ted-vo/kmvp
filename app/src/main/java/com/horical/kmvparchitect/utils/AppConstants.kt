package com.horical.kmvparchitect.utils

object AppConstants {

    internal val NULL_INDEX = -1L
    internal val APP_DB_NAME = "kmvparchitect.db"
    internal val PREF_NAME = "kmvparchitect_pref"
    internal val TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss"
    internal val SEED_DATABASE_QUESTIONS = "seed/questions.json"
    internal val SEED_DATABASE_OPTIONS = "seed/options.json"
    internal val EMPTY_EMAIL_ERROR = 1001
    internal val INVALID_EMAIL_ERROR = 1002
    internal val EMPTY_PASSWORD_ERROR = 1003
    internal val LOGIN_FAILURE = 1004

    internal val ACTION_NO = -1
    internal val ACTION_ADD_ALL = 0
    internal val ACTION_REMOVE = 1

    enum class LoggedInMode constructor(val type: Int) {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3)
    }
}
