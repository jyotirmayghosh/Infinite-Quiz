package com.jyotirmay.infinitequiz.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data Access Object (DAO) for performing database operations on the `BookmarkedEntity` table.
 * This interface provides methods to insert, retrieve, and delete bookmarked quiz questions.
 */
@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookmarkedEntity: BookmarkedEntity)

    @Query("SELECT * FROM BookmarkedEntity")
    suspend fun getBookmarkedQuiz(): List<BookmarkedEntity>

    @Delete
    suspend fun delete(bookmarkedEntity: BookmarkedEntity)
}