package com.jyotirmay.infinitequiz.data.remote

import com.jyotirmay.infinitequiz.data.remote.model.CountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiService {

    /**
     * Fetches a list of countries from the remote API.
     *
     * @return A `Response` object containing a list of `CountryResponse` objects.
     */
    @GET("all")
    suspend fun getCountry(): Response<List<CountryResponse>>
}