package com.example.chagecolor.data

import com.example.chagecolor.R
import com.example.chagecolor.ui.features.news.NewsItem

object NewsDataSource {

    private val newsList = listOf(
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


    fun getNews() = newsList

}