package com.jyotirmay.infinitequiz.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.jyotirmay.infinitequiz.data.local.BookmarkedEntity
import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import com.jyotirmay.infinitequiz.domain.usecases.AddBookmark
import com.jyotirmay.infinitequiz.domain.usecases.GetBookmarkedQuestion
import com.jyotirmay.infinitequiz.domain.usecases.GetQuizQuestion
import com.jyotirmay.infinitequiz.domain.usecases.RemoveBookmark
import com.jyotirmay.infinitequiz.util.StringCompress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizQuestion: GetQuizQuestion,
    private val getBookmarkedQuestion: GetBookmarkedQuestion,
    private val addBookmark: AddBookmark, private val removeBookmark: RemoveBookmark
) : ViewModel() {

    var question = MutableStateFlow(listOf<QuizResponse>())
        private set

    fun fetchQuestionList() {
        viewModelScope.launch {
            try {
                val questionList = getQuizQuestion.invoke()
                questionList.collect { response ->
                    Log.d("test", response.toString())
                    question.value = response
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    var bookmarked = MutableStateFlow(listOf<BookmarkedEntity>())
        private set
    var noBookmarked = MutableStateFlow(false)
        private set

    fun getBookmarked() {
        viewModelScope.launch {
            val questionList = getBookmarkedQuestion.invoke()
            questionList.collect { response ->
                Log.d("test", response.toString())
                if (response.isEmpty()) {
                    noBookmarked.value = true
                } else {
                    bookmarked.value = response
                }
            }
        }
    }

    fun addBookmark(quizResponse: QuizResponse) {
        val json = Gson().toJson(quizResponse.solution)
        Log.d("test", json)
        val bookmarkedEntity = BookmarkedEntity(
            quizResponse.uuidIdentifier,
            quizResponse.questionType,
            quizResponse.question,
            quizResponse.option1,
            quizResponse.option2,
            quizResponse.option3,
            quizResponse.option4,
            quizResponse.correctOption,
            quizResponse.sort,
            StringCompress.compressString(json)
        )
        viewModelScope.launch {
            addBookmark.invoke(bookmarkedEntity)
        }
    }

    fun removeBookmark(quizResponse: QuizResponse) {
        val json = Gson().toJson(quizResponse.solution)
        Log.d("test", json)
        val bookmarkedEntity = BookmarkedEntity(
            quizResponse.uuidIdentifier,
            quizResponse.questionType,
            quizResponse.question,
            quizResponse.option1,
            quizResponse.option2,
            quizResponse.option3,
            quizResponse.option4,
            quizResponse.correctOption,
            quizResponse.sort,
            StringCompress.compressString(json)
        )
        viewModelScope.launch {
            removeBookmark.invoke(bookmarkedEntity)
        }
    }
}