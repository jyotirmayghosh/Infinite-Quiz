package com.jyotirmay.infinitequiz.presentation.quiz.component

import android.util.Log
import android.view.SoundEffectConstants
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import com.jyotirmay.infinitequiz.ui.theme.Correct
import com.jyotirmay.infinitequiz.ui.theme.Wrong

@Composable
fun QuizOptions(
    quizResponse: QuizResponse,
    selectedAnswer: Int,
    answered: Boolean,
    timerSelected: Boolean,
    onSelectAnswer: (isCorrect: Boolean, answerIndex: Int) -> Unit
) {

    val view = LocalView.current

    val options = listOf(
        quizResponse.option1,
        quizResponse.option2,
        quizResponse.option3,
        quizResponse.option4
    )

    for (i in options.indices) {
        val option = options[i]

        val backgroundColor = when {
            !answered -> Color.Transparent
            (i+1) == selectedAnswer && (i+1) != quizResponse.correctOption -> Wrong
            (i+1) == quizResponse.correctOption -> Correct
            else -> Color.Transparent
        }

        val isEnable = when {
            timerSelected && !answered -> true
            answered -> false
            else -> false
        }

        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .border(1.dp, Color.Gray, shape = CircleShape)
                    .clickable(enabled = isEnable) {
                        view.playSoundEffect(SoundEffectConstants.CLICK)
                        Log.d("test", "$i->${options[i]}=${i + 1} -> ${quizResponse.correctOption}")
                        val isCorrect = (i + 1 == quizResponse.correctOption)
                        onSelectAnswer(isCorrect, (i + 1))
                    }
                    .background(backgroundColor, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "${'A' + i}", color = if (isEnable) Color.White else Color.DarkGray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = option, color = if (isEnable) Color.White else Color.DarkGray)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
@Preview
fun QuizOptionsPreview() {
    val quizResponse = QuizResponse(
        uuidIdentifier = "",
        questionType = "text",
        question = "How are you?",
        option1 = "Good",
        option2 = "Bad",
        option3 = "Avg",
        option4 = "Moody",
        correctOption = 1,
        sort = 1,
        solution = listOf()
    )
}