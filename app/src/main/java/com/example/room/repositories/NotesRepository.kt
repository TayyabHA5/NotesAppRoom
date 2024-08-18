package com.example.room.repositories

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.room.database.AppDatabase
import com.example.room.database.NotesDAO
import com.example.room.models.ModelNotes

class NotesRepository(private val notesDAO: NotesDAO) {

    suspend fun addNote(modelNotes: ModelNotes){
        notesDAO.addNote(modelNotes)
    }

    suspend fun updateNote(modelNotes: ModelNotes){
        notesDAO.updateNote(modelNotes)
    }

    suspend fun deleteNote(modelNotes: ModelNotes){
        notesDAO.deleteNote(modelNotes)
    }

    suspend fun getNoteById(noteId : Int) : ModelNotes? {
        return notesDAO.getNoteById(noteId)
    }

     fun getAllNotes() : LiveData<List<ModelNotes>>{
        return notesDAO.getAllNotes()
    }

    fun searchNote(query: String) : LiveData<List<ModelNotes>>{
        return notesDAO.searchNote(query)
    }

}