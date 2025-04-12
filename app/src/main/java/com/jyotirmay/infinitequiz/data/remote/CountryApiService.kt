package com.jyotirmay.infinitequiz.data.remote

import com.jyotirmay.infinitequiz.data.remote.model.CountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiService {

    @GET("all")
    suspend fun getCountry(): Response<List<CountryResponse>>
}