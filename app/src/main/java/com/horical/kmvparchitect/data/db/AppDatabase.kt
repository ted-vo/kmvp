package com.horical.kmvparchitect.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.horical.kmvparchitect.data.db.option.OptionDao
import com.horical.kmvparchitect.data.db.question.QuestionDao
import com.horical.kmvparchitect.data.db.user.UserDao
import com.horical.kmvparchitect.data.db.model.Option
import com.horical.kmvparchitect.data.db.model.Question
import com.horical.kmvparchitect.data.db.model.User

@Database(entities = [User::class, Question::class, Option::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao(): UserDao

    abstract fun questionDao(): QuestionDao

    abstract fun optionDao(): OptionDao
}