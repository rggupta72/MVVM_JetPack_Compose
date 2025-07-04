package com.example.composepoc.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composepoc.database.RoomDataModel

@Dao
interface WorkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setData(worker: List<RoomDataModel>)

    @Query("SELECT * FROM workers_table")
    suspend fun getWorker(): List<RoomDataModel>
}