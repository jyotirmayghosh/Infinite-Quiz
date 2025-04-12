package com.jyotirmay.infinitequiz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookmarkedEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val quizDao: QuizDao
}