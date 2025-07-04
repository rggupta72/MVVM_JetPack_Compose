package com.example.composepoc.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.composepoc.database.RoomDataModel

@Database(entities = [RoomDataModel::class], version = 1)
abstract class WorkerDatabase : RoomDatabase() {

    abstract fun workerDao(): WorkerDao

    companion object {
        private var INSTANCE: WorkerDatabase? = null

        // Define your Migration object
        val MIGRATION_1_2: Migration =
            object : Migration(1, 2) { // From old version 1 to new version 2
                override fun migrate(database: SupportSQLiteDatabase) {
                    // SQL command to add the new 'email_address' column to the 'users' table
                    // Make sure the column definition here matches your entity
                    // (type, nullability, default value if non-null)
                    database.execSQL(
                        "ALTER TABLE workers_table ADD COLUMN email TEXT" // TEXT for String, INTEGER for Int, etc.
                        // If 'email' was non-nullable String and you wanted a default empty string:
                        // "ALTER TABLE users ADD COLUMN email_address TEXT NOT NULL DEFAULT ''"
                        // If 'email' was non-nullable Int and you wanted a default 0:
                        // "ALTER TABLE users ADD COLUMN email_address INTEGER NOT NULL DEFAULT 0"
                    )
                    // If you added multiple columns or made other changes, add more execSQL commands here
                }
            }

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
            ).addMigrations(MIGRATION_1_2) // <--- ADD your migration here
                // You can add multiple migrations: .addMigrations(MIGRATION_1_2, MIGRATION_2_3, ...)
                // .fallbackToDestructiveMigration() // AVOID in production, use for development if you don't care about data loss on schema change
                .build()


    }
}