package id3kt

import java.io.File

class MP3File @Throws(FileNotFoundException::class) constructor() {
    private lateinit var bytes: ByteArray

    var id3v1: ID3v1Tag? = null

    constructor(file: File) : this() {
        if (!file.exists()) {
            throw FileNotFoundException("File \"$file\" does not exists")
        }

        bytes = file.readBytes()
        init(bytes)
    }

    constructor(filename: String) : this() {
        val file = File(filename)
        if (!file.exists()) {
            throw FileNotFoundException("File \"$file\" does not exists")
        }
        bytes = file.readBytes()
        init(bytes)
    }

    private fun init(bytes: ByteArray) {
        val id3v1Bytes: ByteArray = bytes.copyOfRange(bytes.size - ID3v1Tag.TAG_LENGTH, bytes.lastIndex)
        if (id3v1Bytes.decodeToString(0, 3) == "TAG") {
            id3v1 = ID3v1Tag(id3v1Bytes)
        }
    }
}