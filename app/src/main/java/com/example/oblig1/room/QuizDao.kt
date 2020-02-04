package com.example.oblig1.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz")
    fun getAll(): List<QuizItem>

    @Query("SELECT * FROM quiz WHERE id IN (:quizIds)")
    fun loadAllByIds(quizIds: IntArray): List<QuizItem>

    @Query("SELECT * FROM quiz WHERE name LIKE :first")
    fun findByName(first: String): QuizItem

    @Insert
    @JvmSuppressWildcards
    fun insertAll(items: List<QuizItem>)

    @Insert
    fun insertSingular(item: QuizItem)

    @Delete
    fun delete(user: QuizItem)
}