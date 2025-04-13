package com.jyotirmay.infinitequiz.data.repository

import com.jyotirmay.infinitequiz.data.local.BookmarkedEntity
import com.jyotirmay.infinitequiz.data.local.QuizDao
import com.jyotirmay.infinitequiz.data.remote.CountryApiService
import com.jyotirmay.infinitequiz.data.remote.QuizApiService
import com.jyotirmay.infinitequiz.data.remote.model.CountryResponse
import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import com.jyotirmay.infinitequiz.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of the Repository interface.
 * This class acts as a single source of truth for data, handling both remote and local data sources.
 */
class RepositoryImpl @Inject constructor(
    private val countryApi: CountryApiService,
    private val quizApi: QuizApiService,
    private val quizDao: QuizDao
) : Repository {

    /**
     * Fetches the list of countries from the remote API.
     * Emits the list of countries as a Flow.
     */
    override suspend fun getCountryList(): Flow<List<CountryResponse>> = flow {
        try {
            val response = countryApi.getCountry()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it) // Emit the list of countries if the response is successful
                } ?: throw Exception("Response body is null")
            } else {
                throw Exception("An error occurred: ${response.code()}")
            }
        } catch (e: Exception) {
            throw Exception("Unknown error: ${e.message}")
        }
    }

    /**
     * Fetches the list of quiz questions from the remote API.
     * Emits the list of quiz questions as a Flow.
     */
    override suspend fun getQuizQuestion(): Flow<List<QuizResponse>> = flow {
        try {

            val response = quizApi.getQuestions()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it) // Emit the list of quiz questions if the response is successful
                } ?: throw Exception("Response body is null")
            } else {
                throw Exception("An error occurred: ${response.code()}")
            }
        } catch (e: Exception) {
            throw Exception("Unknown error: ${e.message}")
        }
    }

    /**
     * Adds a quiz question to the bookmarks in the local database.
     * @param bookmarkedEntity The entity to be added to the bookmarks.
     */
    override suspend fun addToBookmark(bookmarkedEntity: BookmarkedEntity) {
        quizDao.insert(bookmarkedEntity)
    }

    /**
     * Removes a quiz question from the bookmarks in the local database.
     * @param bookmarkedEntity The entity to be removed from the bookmarks.
     */
    override suspend fun removeBookmark(bookmarkedEntity: BookmarkedEntity) {
        quizDao.delete(bookmarkedEntity)
    }

    /**
     * Fetches the list of bookmarked quiz questions from the local database.
     * Emits the list of bookmarked questions as a Flow.
     */
    override suspend fun getBookmarkedQuestions(): Flow<List<BookmarkedEntity>> = flow {
        val record = quizDao.getBookmarkedQuiz()
        emit(record)
    }

}