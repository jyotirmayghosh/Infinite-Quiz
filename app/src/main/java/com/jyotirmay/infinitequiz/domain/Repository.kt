package com.jyotirmay.infinitequiz.domain

import com.jyotirmay.infinitequiz.data.local.BookmarkedEntity
import com.jyotirmay.infinitequiz.data.remote.model.CountryResponse
import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface that defines the contract for data operations.
 * This interface abstracts the data layer, providing methods to interact
 * with both remote and local data sources.
 */
interface Repository {

    suspend fun getCountryList(): Flow<List<CountryResponse>>

    suspend fun getQuizQuestion(): Flow<List<QuizResponse>>

    suspend fun addToBookmark(bookmarkedEntity: BookmarkedEntity)

    suspend fun removeBookmark(bookmarkedEntity: BookmarkedEntity)

    suspend fun getBookmarkedQuestions(): Flow<List<BookmarkedEntity>>
}