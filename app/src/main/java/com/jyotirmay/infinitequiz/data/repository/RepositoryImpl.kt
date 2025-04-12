package com.jyotirmay.infinitequiz.data.repository

import com.jyotirmay.infinitequiz.data.local.AppDatabase
import com.jyotirmay.infinitequiz.data.local.BookmarkedEntity
import com.jyotirmay.infinitequiz.data.local.QuizDao
import com.jyotirmay.infinitequiz.data.remote.CountryApiService
import com.jyotirmay.infinitequiz.data.remote.QuizApiService
import com.jyotirmay.infinitequiz.data.remote.model.CountryResponse
import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import com.jyotirmay.infinitequiz.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class RepositoryImpl @Inject constructor(
    private val countryApi: CountryApiService,
    private val quizApi: QuizApiService,
    private val quizDao: QuizDao
) : Repository {

    override suspend fun getCountryList(): Flow<List<CountryResponse>> = flow {
        try {
            val response = countryApi.getCountry()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it)
                } ?: throw Exception("Response body is null")
            } else {
                throw Exception("An error occurred: ${response.code()}")
            }
        } catch (e: Exception) {
            throw Exception("Unknown error: ${e.message}")
        }
    }

    override suspend fun getQuizQuestion(): Flow<List<QuizResponse>> = flow {
        try {

            val response = quizApi.getQuestions()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it)
                } ?: throw Exception("Response body is null")
            } else {
                throw Exception("An error occurred: ${response.code()}")
            }
        } catch (e: Exception) {
            throw Exception("Unknown error: ${e.message}")
        }
    }

    override suspend fun addToBookmark(bookmarkedEntity: BookmarkedEntity) {
        quizDao.insert(bookmarkedEntity)
    }

    override suspend fun removeBookmark(bookmarkedEntity: BookmarkedEntity) {
        quizDao.delete(bookmarkedEntity)
    }

    override suspend fun getBookmarkedQuestions(): Flow<List<BookmarkedEntity>> = flow {
        val record = quizDao.getBookmarkedQuiz()
        emit(record)
    }

}