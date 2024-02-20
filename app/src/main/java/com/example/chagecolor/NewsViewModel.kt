package com.example.chagecolor

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class NewsItem(
    val news: String,
    val prof: String,
    val imageId: Int
)

class NewsViewModel : ViewModel() {
    val newsList: MutableState<List<NewsItem>> = mutableStateOf(
        listOf(
            NewsItem("Fire", "Fire in the JK", R.drawable.news_fire),
            NewsItem("Earthquake", "Earthquake in Korea", R.drawable.earthquake),
            NewsItem("Flood", "Flood in Japan", R.drawable.flood),
            NewsItem("Fire", "The hospital building is in fire", R.drawable.news_fire),
            NewsItem("Health", "A flu vaccine vas been created", R.drawable.vaccine),
            NewsItem("Earthquake", "Victims have been found", R.drawable.earthquake),
            NewsItem("Fire", "Fire in the JK", R.drawable.news_fire),
            NewsItem("Earthquake", "The number of vicims", R.drawable.earthquake),
            NewsItem("Fire", "Fire in the JK Turkish", R.drawable.flood),
            NewsItem("Fire", "Fire in the JK Moscow", R.drawable.flood)
        )
    )
}
