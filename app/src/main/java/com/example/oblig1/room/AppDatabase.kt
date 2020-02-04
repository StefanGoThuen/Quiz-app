package com.example.oblig1.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuizItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao
}