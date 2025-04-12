package com.jyotirmay.infinitequiz.presentation.quiz

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jyotirmay.infinitequiz.R
import com.jyotirmay.infinitequiz.data.local.BookmarkedEntity
import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import com.jyotirmay.infinitequiz.presentation.quiz.component.QuestionSection
import com.jyotirmay.infinitequiz.presentation.quiz.component.ResultSection
import com.jyotirmay.infinitequiz.presentation.quiz.component.SolutionSection
import com.jyotirmay.infinitequiz.ui.theme.PurpleGrey40

@Composable
fun QuizScreen(
    navController: NavController,
    isBookmark: Boolean,
    quizViewModel: QuizViewModel,
    quizList: List<QuizResponse>
) {
    var bookmarkedQuestionCounter by remember { mutableStateOf(0) }
    var questionCounter by remember { mutableStateOf(0) }
    var correctCounter by remember { mutableStateOf(0) }
    var wrongCounter by remember { mutableStateOf(0) }
    var skipCounter by remember { mutableStateOf(0) }

    var selectedAnswer by remember { mutableStateOf(-1) }
    var question by remember { mutableStateOf(quizList[0]) }
    var mAnswered by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf(false) }
    var mSelectedTimer by remember { mutableStateOf(-1) }
    var mTimerEnabled by remember { mutableStateOf(false) }

    var addBookmarked by remember { mutableStateOf(false) }

    if (showResult) {
        ResultSection(skipped = skipCounter, correct = correctCounter) {
            showResult = false
            question = quizList.random()
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            // Scrollable content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFF1E1E1E))
                    .padding(bottom = 72.dp) // Leave space for the footer
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            ) {
                QuestionSection(
                    navController = navController,
                    questionCounter = questionCounter,
                    currentQuestion = question,
                    selectedAnswer = selectedAnswer,
                    answered = mAnswered,
                    selectedTimer = mSelectedTimer,
                    timerSelected = mTimerEnabled,
                    onSelectAnswer = { isCorrect, answerIndex ->
                        mAnswered = true
                        mSelectedTimer = -1
                        selectedAnswer = answerIndex
                        if (isCorrect) {
                            correctCounter++
                        } else {
                            wrongCounter++
                        }
                    },
                    setTimer = { time ->
                        mTimerEnabled = true
                        mSelectedTimer = time
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (mAnswered) {
                    SolutionSection(question)
                }
            }

            // Fixed footer
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(color = PurpleGrey40),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (isBookmark) {
                            addBookmarked = false
                            quizViewModel.removeBookmark(question)
                        } else {
                            addBookmarked = true
                            quizViewModel.addBookmark(question)
                        }
                    },
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            id = when {
                                addBookmarked && isBookmark ->  R.drawable.ic_bookmark_red
                                isBookmark || !addBookmarked -> R.drawable.ic_bookmark_outline
                                //isBookmark -> R.drawable.ic_bookmark_red
                                else -> R.drawable.ic_bookmark_outline
                            }
                        ),
                        contentDescription = "Bookmark",
                        tint = Color.White
                    )
                }

                Button(
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = {
                        //enable timer
                        mTimerEnabled = false
                        //stop time counter
                        mSelectedTimer = -1

                        //if not answer, increase skip counter
                        if (!mAnswered) {
                            skipCounter++
                        } else {
                            mAnswered = false
                        }

                        //increase question counter
                        questionCounter++
                        //if 5 question viewed, show result
                        if (questionCounter == 5) {
                            questionCounter = 0
                            showResult = true
                        } else {
                            if (showResult) {
                                showResult = false
                            }

                            if (isBookmark) {
                                if (bookmarkedQuestionCounter < quizList.size-1) {
                                    bookmarkedQuestionCounter++
                                } else {
                                    bookmarkedQuestionCounter = 0
                                }
                                Log.e("test", "$bookmarkedQuestionCounter")
                                question = quizList[bookmarkedQuestionCounter]
                            } else {
                                addBookmarked = false
                                question = quizList.random()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB39DDB))
                ) {
                    val btnText = if (mAnswered) "NEXT" else "SKIP"
                    Text(text = btnText, color = Color.White)
                }
            }
        }
    }
}
