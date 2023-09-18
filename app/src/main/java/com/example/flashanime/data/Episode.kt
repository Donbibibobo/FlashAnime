package com.example.flashanime.data

sealed class Episode {
    data class EpisodeSelected(val episode: String): Episode()
    data class EpisodeUnSelected(val episode: String): Episode()
}
