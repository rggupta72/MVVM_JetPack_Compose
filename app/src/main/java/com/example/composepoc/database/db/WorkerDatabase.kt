package com.example.composepoc.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.composepoc.database.RoomDataModel

@Database(entities = [RoomDataModel::class], version = 1)
abstract class WorkerDatabase : RoomDatabase() {

    abstract fun workerDao(): WorkerDao

    companion object {
        private var INSTANCE: WorkerDatabase? = null

        fun getInstance(context: Context): WorkerDatabase {
            if (INSTANCE == null) {
                synchronized(WorkerDatabase::class) {
                    INSTANCE = buildRoomDb(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDb(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WorkerDatabase::class.java,
                "worker.db"
            ).build()


    }
}