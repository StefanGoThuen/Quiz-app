package com.example.oblig1.room

import androidx.room.*

@Dao
interface QuizDao {

    @Query("SELECT * FROM quiz")
    fun getAll(): List<QuizItem>

    @Query("SELECT * FROM quiz WHERE id LIKE :first LIMIT 1")
    fun findById(first: Long): QuizItem

    @Query("SELECT * FROM quiz WHERE id IN (:quizIds)")
    fun loadAllByIds(quizIds: IntArray): List<QuizItem>

    @Query("SELECT * FROM quiz WHERE name LIKE :first")
    fun findByName(first: String): QuizItem

    @Insert
    fun insertAll(items: List<QuizItem>)

    @Insert
    fun insertSingular(item: QuizItem)

    @Delete
    fun delete(user: QuizItem)
}