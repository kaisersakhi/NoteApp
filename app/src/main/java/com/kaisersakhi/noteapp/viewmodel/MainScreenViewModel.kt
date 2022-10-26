package com.kaisersakhi.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaisersakhi.noteapp.data.model.Note
import com.kaisersakhi.noteapp.data.repository.NoteRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

class MainScreenViewModel(private val context: Application) : AndroidViewModel(context) {

    private val noteRepository = NoteRepository(application = context)

    val notes = noteRepository.getAllNotes()
    var selectedNote = MutableLiveData<Note>()
        private set

    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun getNote(id: Int)  {
        viewModelScope.launch {
           selectedNote.postValue(noteRepository.getNote(id))
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
    fun deleteNote(id: Int){
        viewModelScope.launch {
            noteRepository.deleteNote(id)
        }
    }

}