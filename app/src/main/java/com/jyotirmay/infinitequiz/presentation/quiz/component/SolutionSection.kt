package com.jyotirmay.infinitequiz.presentation.quiz.component

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import com.jyotirmay.infinitequiz.presentation.common.CustomOutlineButton
import com.jyotirmay.infinitequiz.presentation.common.CustomText
import com.jyotirmay.infinitequiz.ui.theme.Purple40

@Composable
fun SolutionSection(quizResponse: QuizResponse) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E))
            .padding(16.dp)
    ) {

        Text(text = "Solution", color = Purple40)
        Spacer(modifier = Modifier.height(8.dp))
        quizResponse.solution?.forEach {
            CustomText(type = it.contentType, content = it.contentData)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            CustomOutlineButton(text = "Share") {
                val correctAns = when(quizResponse.correctOption) {
                    1 -> quizResponse.option1
                    2 -> quizResponse.option2
                    3 -> quizResponse.option3
                    4 -> quizResponse.option4
                    else -> "Invalid option"
                }
                val textToShare = "${quizResponse.question}\n\nAnswer: $correctAns"
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, textToShare)
                }
                context.startActivity(Intent.createChooser(intent, "Share via"))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}