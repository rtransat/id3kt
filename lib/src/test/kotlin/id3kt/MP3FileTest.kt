package id3kt

import java.io.File
import java.net.URL
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class MP3FileTest {
    private val mp3File: MP3File
    private val resource: URL = javaClass.classLoader.getResource("v1tag.mp3")

    init {
        val file = File(resource.toURI())
        mp3File = MP3File(file)
    }

    @Test
    fun `constructor with File throw FileNotFoundException`() {
        assertFailsWith<FileNotFoundException> {
            val file = File("file_not_found.mp3")
            MP3File(file)
        }
    }

    @Test
    fun `constructor with filename throw FileNotFoundException`() {
        assertFailsWith<FileNotFoundException> {
            MP3File("file_not_found.mp3")
        }
    }

    @Test
    fun `test constructor with a filename`() {
        val mp3File = MP3File(resource.file.toString())
        assertIs<MP3File>(mp3File)
    }

    @Test
    fun `file has id3v1 tag`() {
        assertNotNull(mp3File.id3v1)
    }
}