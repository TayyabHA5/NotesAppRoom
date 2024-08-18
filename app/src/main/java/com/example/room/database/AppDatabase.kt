package com.example.room.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.room.models.ModelNotes

@Database(entities = [ModelNotes::class], version = 1, exportSchema = true)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notesDAO() : NotesDAO

    companion object{
        @Volatile
       private var INSTANCE : AppDatabase? = null
        fun getData(context: Context) : AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}