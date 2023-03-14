package id3kt

interface ID3v1 {
    val title: String
    val artist: String
    val album: String
    val year: Int
    val comment: String
    val track: Int?
}