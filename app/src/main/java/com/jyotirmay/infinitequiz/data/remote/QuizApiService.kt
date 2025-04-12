package com.jyotirmay.infinitequiz.data.remote

import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {

    @GET("mcq/content")
    suspend fun getQuestions(): Response<List<QuizResponse>>
}