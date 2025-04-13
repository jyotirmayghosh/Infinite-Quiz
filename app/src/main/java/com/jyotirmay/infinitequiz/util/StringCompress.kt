package com.jyotirmay.infinitequiz.util

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream
import java.util.Base64

/**
 * Utility object for compressing and decompressing strings.
 * This uses GZIP for compression and Base64 for encoding the compressed data.
 */
object StringCompress {
    /**
     * Compresses the given string using GZIP and encodes it in Base64.
     *
     * @param input The string to be compressed.
     * @return A Base64-encoded string representing the compressed data.
     */
    fun compressString(input: String): String {
        val byteStream = ByteArrayOutputStream()
        GZIPOutputStream(byteStream).use { gzipStream ->
            OutputStreamWriter(gzipStream, Charsets.UTF_8).use { writer ->
                writer.write(input)
            }
        }
        return Base64.getEncoder().encodeToString(byteStream.toByteArray())
    }

    /**
     * Decompresses a Base64-encoded, GZIP-compressed string.
     *
     * @param compressed The Base64-encoded string to be decompressed.
     * @return The original uncompressed string.
     */
    fun decompressString(compressed: String): String {
        val compressedBytes = Base64.getDecoder().decode(compressed)
        return GZIPInputStream(ByteArrayInputStream(compressedBytes)).use { gzip ->
            InputStreamReader(gzip, Charsets.UTF_8).readText()
        }
    }
}