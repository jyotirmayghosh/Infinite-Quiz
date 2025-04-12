package com.jyotirmay.infinitequiz.util

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream
import java.util.Base64

object StringCompress {
    fun compressString(input: String): String {
        val byteStream = ByteArrayOutputStream()
        GZIPOutputStream(byteStream).use { gzipStream ->
            OutputStreamWriter(gzipStream, Charsets.UTF_8).use { writer ->
                writer.write(input)
            }
        }
        return Base64.getEncoder().encodeToString(byteStream.toByteArray())
    }

    fun decompressString(compressed: String): String {
        val compressedBytes = Base64.getDecoder().decode(compressed)
        return GZIPInputStream(ByteArrayInputStream(compressedBytes)).use { gzip ->
            InputStreamReader(gzip, Charsets.UTF_8).readText()
        }
    }
}