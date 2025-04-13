package com.jyotirmay.infinitequiz.data.remote

import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {

    /**
     * Fetches a list of quiz questions from the remote API.
     *
     * @return A `Response` object containing a list of `QuizResponse` objects.
     */
    @GET("mcq/content")
    suspend fun getQuestions(): Response<List<QuizResponse>>
}