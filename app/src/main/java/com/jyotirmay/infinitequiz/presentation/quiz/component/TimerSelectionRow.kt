package com.jyotirmay.infinitequiz.presentation.quiz.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jyotirmay.infinitequiz.ui.theme.Correct
import kotlinx.coroutines.delay

@Composable
fun TimerSelectionRow(
    selectedTime: Int,
    answered: Boolean,
    timerEnabled: Boolean,
    onSelectTime: (selectedTime: Int) -> Unit,
    onTimerComplete: () -> Unit
) {
    var remainingTime by remember { mutableStateOf(-1) }

    // Timer logic
    LaunchedEffect(selectedTime) {
        remainingTime = selectedTime
        while (remainingTime > 0) {
            delay(1000L)
            remainingTime--
        }
        if (remainingTime == 0) {
            onTimerComplete()
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf(30, 60, 90).forEach { time ->
                OutlinedButton(
                    onClick = {
                        onSelectTime(time)
                    },
                    enabled = !timerEnabled,
                    border = BorderStroke(
                        2.dp,
                        if (selectedTime == time)
                            Correct
                        else
                            Color.Gray
                    )
                ) {
                    Text(
                        text = "${time}s",
                        color = if (answered)
                            Color.Gray
                        else
                            Color.White
                    )
                }
            }
        }

        if (selectedTime > 0) {
            Text(
                text = "${remainingTime}s",
                color = Color.White,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
@Preview
fun TimerSelectionRowPreview() {
    TimerSelectionRow(0, false, false, {}) {}
}
