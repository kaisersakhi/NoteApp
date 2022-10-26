package com.kaisersakhi.noteapp.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kaisersakhi.noteapp.data.model.Note
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase() : RoomDatabase(){
    abstract fun getNoteDao(): NoteDao

    companion object {
        private var INSTANCE: NoteDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(application: Application): NoteDatabase {
            if (this.INSTANCE == null) {
                synchronized(this) {
                    this.INSTANCE = Room.databaseBuilder(
                        application,
                        NoteDatabase::class.java,
                        "note_db"
                    ).build()
                }
            }
            return this.INSTANCE!!
        }
    }
}

