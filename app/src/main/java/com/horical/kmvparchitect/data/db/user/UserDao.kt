package com.horical.kmvparchitect.data.db.user

import android.arch.persistence.room.*
import com.horical.kmvparchitect.data.db.model.User

@Dao
interface UserDao {
    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM users WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Query("SELECT * FROM users")
    fun loadAll(): List<User>

    @Query("SELECT * FROM users WHERE id IN (:ids)")
    fun loadByIds(ids: List<Long>): List<User>
}