package com.example.room.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.room.database.AppDatabase
import com.example.room.models.ModelNotes
import com.example.room.repositories.NotesRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.processNextEventInCurrentThread

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var repository: NotesRepository

    init {
        val notesDao = AppDatabase.getData(application).notesDAO()
        repository = NotesRepository(notesDao)
    }

    fun addNote(modelNotes: ModelNotes) = viewModelScope.launch {
        repository.addNote(modelNotes)
    }

    fun deleteNote(modelNotes: ModelNotes) = viewModelScope.launch {
        repository.deleteNote(modelNotes)
    }

    suspend fun getNoteById(id: Int): ModelNotes? {
        return repository.getNoteById(id)
    }

    fun getAllNotes() : LiveData<List<ModelNotes>>{
        return repository.getAllNotes()
    }

    fun updateNote(modelNotes: ModelNotes) = viewModelScope.launch {
        repository.updateNote(modelNotes)
    }

    fun searchNote(query:String?) : LiveData<List<ModelNotes>>? {
      return query?.let { repository.searchNote(it) }
    }



}