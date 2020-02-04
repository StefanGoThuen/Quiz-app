package com.example.oblig1.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz")
data class QuizItem(
        @PrimaryKey(autoGenerate = true) val id : Long = 0,
        @ColumnInfo(name="name") val name : String?,
        @ColumnInfo(name="bitmap") val bitmapPath : String?
)
