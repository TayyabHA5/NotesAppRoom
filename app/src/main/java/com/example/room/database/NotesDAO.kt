package com.example.room.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.room.models.ModelNotes

@Dao
interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addNote(modelNotes: ModelNotes)

   @Update
   suspend fun updateNote(modelNotes: ModelNotes)

   @Delete
   suspend fun deleteNote(modelNotes: ModelNotes)

   @Query("SELECT * FROM note_table WHERE id = :noteID LIMIT 1")
   suspend fun getNoteById(noteID : Int) : ModelNotes?

   @Query("SELECT * FROM note_table")
   fun getAllNotes() : LiveData<List<ModelNotes>>

   @Query("SELECT * FROM note_table WHERE title LIKE :query OR description LIKE :query")
   fun searchNote(query: String?) : LiveData<List<ModelNotes>>
}