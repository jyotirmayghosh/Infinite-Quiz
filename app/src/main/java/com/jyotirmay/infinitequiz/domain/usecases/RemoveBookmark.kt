package com.jyotirmay.infinitequiz.domain.usecases

import com.jyotirmay.infinitequiz.data.local.BookmarkedEntity
import com.jyotirmay.infinitequiz.domain.Repository
import javax.inject.Inject

class RemoveBookmark @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(bookmarkedEntity: BookmarkedEntity) {
        repository.removeBookmark(bookmarkedEntity)
    }
}