package id3kt

class ID3v1Tag(private val bytes: ByteArray) : ID3v1 {
    companion object {
        internal const val TAG_LENGTH = 128
        private const val TITLE_OFFSET = 3
        private const val TITLE_LENGTH = 30
        private const val ARTIST_OFFSET = 33
        private const val ARTIST_LENGTH = 30
        private const val ALBUM_OFFSET = 63
        private const val ALBUM_LENGTH = 30
        private const val YEAR_OFFSET = 93
        private const val YEAR_LENGTH = 4
        private const val COMMENT_OFFSET = 97
        private const val COMMENT_LENGTH_V1_0 = 30
        private const val COMMENT_LENGTH_V1_1 = 28
        private const val TRACK_MARKER_OFFSET = 125
        private const val TRACK_OFFSET = 126
        private const val TRACK_LENGTH = 1
    }

    override val title: String = bytes.decodeToString(TITLE_OFFSET, TITLE_LENGTH + TITLE_OFFSET).trimEnd()

    override val artist: String = bytes.decodeToString(ARTIST_OFFSET, ARTIST_LENGTH + ARTIST_OFFSET).trimEnd()

    override val album: String = bytes.decodeToString(ALBUM_OFFSET, ALBUM_LENGTH + ALBUM_OFFSET).trimEnd()

    override val year: Int = bytes.decodeToString(YEAR_OFFSET, YEAR_LENGTH + YEAR_OFFSET).toInt()

    override val comment: String
        get() {
            return if (bytes[TRACK_MARKER_OFFSET] != 0.toByte()) {
                bytes.decodeToString(COMMENT_OFFSET, COMMENT_LENGTH_V1_0 + COMMENT_OFFSET).trimEnd()
            } else {
                bytes.decodeToString(COMMENT_OFFSET, COMMENT_LENGTH_V1_1 + COMMENT_OFFSET).trimEnd()
            }
        }

    override val track: Int?
        get() {
            if (bytes[TRACK_MARKER_OFFSET] != 0.toByte()) {
                val trackInt: Byte = bytes[TRACK_OFFSET]
                return if (trackInt == 0.toByte()) {
                    null
                } else {
                    trackInt.toInt()
                }
            }
            return null
        }
}