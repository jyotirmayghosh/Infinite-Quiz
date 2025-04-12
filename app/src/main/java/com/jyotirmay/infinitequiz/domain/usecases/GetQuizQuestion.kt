package com.jyotirmay.infinitequiz.domain.usecases

import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import com.jyotirmay.infinitequiz.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuizQuestion@Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Flow<List<QuizResponse>> {
        return repository.getQuizQuestion()
    }
}