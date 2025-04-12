package com.jyotirmay.infinitequiz.domain.usecases

import com.jyotirmay.infinitequiz.data.local.BookmarkedEntity
import com.jyotirmay.infinitequiz.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedQuestion@Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Flow<List<BookmarkedEntity>> {
        return repository.getBookmarkedQuestions()
    }
}