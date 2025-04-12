package com.jyotirmay.infinitequiz.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookmarkedEntity(
    @PrimaryKey val uuidIdentifier: String,
    val questionType: String,
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctOption: Int,
    val sort: Int,
    val solution: String
)