package com.jyotirmay.infinitequiz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The main database class for the application.
 * This class defines the database configuration and serves as the main access point
 * for the underlying SQLite database using Room.
 *
 * @property quizDao Provides access to the DAO (Data Access Object) for performing
 * database operations on the `BookmarkedEntity` table.
 */
@Database(entities = [BookmarkedEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val quizDao: QuizDao
}