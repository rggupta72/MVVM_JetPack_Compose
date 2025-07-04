package com.example.composepoc.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workers_table")
data class RoomDataModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val worker: String,
    val email: String = "Email Address"
)
