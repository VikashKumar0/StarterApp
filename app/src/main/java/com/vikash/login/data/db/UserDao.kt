package com.vikash.login.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vikash.login.data.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("Select * From user WHERE userName = :userName")
    suspend fun getUser(userName: String): User

    @Query("DELETE From user WHERE id = :id")
    suspend fun deleteUser(id: Long){

    }

}