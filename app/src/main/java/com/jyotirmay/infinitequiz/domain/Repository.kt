package com.jyotirmay.infinitequiz.domain

import com.jyotirmay.infinitequiz.data.local.BookmarkedEntity
import com.jyotirmay.infinitequiz.data.remote.model.CountryResponse
import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCountryList(): Flow<List<CountryResponse>>

    suspend fun getQuizQuestion(): Flow<List<QuizResponse>>

    suspend fun addToBookmark(bookmarkedEntity: BookmarkedEntity)

    suspend fun removeBookmark(bookmarkedEntity: BookmarkedEntity)

    suspend fun getBookmarkedQuestions(): Flow<List<BookmarkedEntity>>
}