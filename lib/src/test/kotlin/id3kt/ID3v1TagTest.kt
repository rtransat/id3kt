package id3kt

import java.io.File
import java.net.URL
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ID3v1TagTest {
    private val mp3File: MP3File

    init {
        val resource: URL = javaClass.classLoader.getResource("v1tag.mp3")
        val file = File(resource.toURI())
        mp3File = MP3File(file)
    }

    @Test
    fun `id3v1 has title field`() {
        assertEquals("TITLE1234567890123456789012345", mp3File.id3v1?.title)
    }

    @Test
    fun `id3v1 has artist field`() {
        assertEquals("ARTIST123456789012345678901234", mp3File.id3v1?.artist)
    }

    @Test
    fun `id3v1 has album field`() {
        assertEquals("ALBUM1234567890123456789012345", mp3File.id3v1?.album)
    }

    @Test
    fun `id3v1 has year field`() {
        assertEquals(2001, mp3File.id3v1?.year)
    }

    @Test
    fun `id3v1 has comment field`() {
        println(mp3File.id3v1?.comment)
        assertEquals("COMMENT123456789012345678901", mp3File.id3v1?.comment)
    }

    @Test
    fun `id3v1 has track field`() {
        assertNull(mp3File.id3v1?.track)
    }
}
