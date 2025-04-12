package com.jyotirmay.infinitequiz.domain.usecases

import com.jyotirmay.infinitequiz.data.remote.model.CountryResponse
import com.jyotirmay.infinitequiz.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountry @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Flow<List<CountryResponse>> {
        return repository.getCountryList()
    }
}