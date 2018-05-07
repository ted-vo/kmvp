package com.horical.kmvparchitect.data.db.option

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.horical.kmvparchitect.data.db.model.Option

@Dao
interface OptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(option: Option)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(options: List<Option>)

    @Query("SELECT * FROM options")
    fun loadAll(): List<Option>

    @Query("SELECT * FROM options WHERE question_id = :questionId")
    fun loaAllByQuestionId(questionId: Long): List<Option>
}
