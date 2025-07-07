package com.example.composepoc.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composepoc.database.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun setNote(note: Note)

    @Query("SELECT * FROM notes_table")
    suspend fun getNoteList(): List<Note>
}