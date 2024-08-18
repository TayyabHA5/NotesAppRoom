package com.example.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "note_table")
data class ModelNotes(
    @PrimaryKey(autoGenerate = true) val id : Int =0,
    @ColumnInfo("title")
    val title : String = "",
    @ColumnInfo("description")
    val desc : String = "",
    @ColumnInfo("date")
    val date : Date
)
