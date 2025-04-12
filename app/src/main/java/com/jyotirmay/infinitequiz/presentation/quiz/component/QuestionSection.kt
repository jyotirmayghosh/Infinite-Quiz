package com.jyotirmay.infinitequiz.presentation.quiz.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jyotirmay.infinitequiz.R
import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import com.jyotirmay.infinitequiz.presentation.common.CustomText
import com.jyotirmay.infinitequiz.ui.theme.Purple40

@Composable
fun QuestionSection(
    navController: NavController,
    questionCounter: Int,
    currentQuestion: QuizResponse,
    selectedAnswer: Int,
    answered: Boolean,
    selectedTimer: Int,
    timerSelected: Boolean,
    onSelectAnswer: (Boolean, Int) -> Unit,
    setTimer: (Int) -> Unit
) {
    Log.d("test", currentQuestion.toString())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E))
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }

            AudioPlayer(mp3ResId = R.raw.track)
        }

        TimerSelectionRow(
            selectedTime = selectedTimer,
            answered = answered,
            timerEnabled = timerSelected,
            onSelectTime = { time ->
                setTimer(time)
            }) {
            //OnTime Completed
            onSelectAnswer(false, -1)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Level text
        Text(
            text = "Question ${questionCounter+1}",
            color = Purple40,
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Question
        CustomText(type = currentQuestion.questionType, content = currentQuestion.question)

        Spacer(modifier = Modifier.height(24.dp))

        // Options
        QuizOptions(
            quizResponse = currentQuestion,
            selectedAnswer = selectedAnswer,
            answered = answered,
            timerSelected = timerSelected,
            onSelectAnswer = { isCorrect, answerIndex ->
                onSelectAnswer(isCorrect, answerIndex)
            }
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}
