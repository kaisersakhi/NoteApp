package com.kaisersakhi.noteapp.data.repository

import android.app.Application
import com.kaisersakhi.noteapp.data.local.NoteDatabase
import com.kaisersakhi.noteapp.data.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(application: Application) {
    private val database = NoteDatabase.getInstance(application).getNoteDao()

    fun getAllNotes() = database.getAllNotes()

    suspend fun getNote(id: Int) = database.getNote(id)

    suspend fun insertNote(note: Note) = withContext(Dispatchers.IO) {
        database.insert(note)
    }

    suspend fun updateNote(note: Note) = withContext(Dispatchers.IO) {
        database.update(note)
    }

    suspend fun deleteNote(note: Note) = withContext(Dispatchers.IO){
        database.delete(note)

    }

    suspend fun deleteNote(id : Int) = withContext(Dispatchers.IO){
        database.delete(id)
    }
}