package com.jyotirmay.infinitequiz.presentation.common

import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage

@Composable
fun CustomText(type: String, content: String) {
    when (type) {
        "text" -> {
            Text(
                text = content,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        "htmlText" -> {
            val spanned = remember(content) {
                HtmlCompat.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
            AndroidView(
                factory = { context ->
                    TextView(context).apply {
                        setTextColor(android.graphics.Color.WHITE)
                    }
                },
                update = {
                    it.text = spanned
                }
            )
        }
        "image" -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = content,
                    contentDescription = "Loaded Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        else -> {
            Text(text = "Unsupported type")
        }
    }
}
