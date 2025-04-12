package com.jyotirmay.infinitequiz.presentation.quiz.component

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun AudioPlayer(mp3ResId: Int) {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(true) }
    val mediaPlayer = remember {
        MediaPlayer.create(context, mp3ResId)
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    if (isPlaying)
        mediaPlayer.start()

    Row {
        IconButton(onClick = {
            isPlaying = if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                false
            } else {
                mediaPlayer.start()
                true
            }
        }) {
            Icon(
                imageVector = if (isPlaying) Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
                contentDescription = "Volume",
                tint = Color.White
            )
        }
    }
}
