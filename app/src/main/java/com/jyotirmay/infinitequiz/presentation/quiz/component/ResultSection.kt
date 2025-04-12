package com.jyotirmay.infinitequiz.presentation.quiz.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jyotirmay.infinitequiz.presentation.common.CustomOutlineButton
import com.jyotirmay.infinitequiz.ui.theme.Correct
import com.jyotirmay.infinitequiz.ui.theme.Dimension
import com.jyotirmay.infinitequiz.ui.theme.Wrong

@Composable
fun ResultSection(
    skipped: Int,
    correct: Int,
    onContinue: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C2C2C)) // dark gray
            .padding(Dimension.padding_8),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Awesome you got",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$correct",
                            color = Correct,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = "/5", color = Color.White)
                    }

                    Text(
                        text = "Correct",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$skipped",
                            color = Wrong,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = "/5", color = Color.White)
                    }

                    Text(
                        text = "Skipped",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            CustomOutlineButton("Continue") {
                onContinue()
            }

        }
    }
}

@Composable
@Preview
fun ResultPreview() {
    ResultSection(0, 1) {}
}