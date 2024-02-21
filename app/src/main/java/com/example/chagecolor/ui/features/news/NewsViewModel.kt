package com.example.chagecolor.ui.features.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chagecolor.data.NewsDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class NewsItem(
    val news: String,
    val prof: String,
    val imageId: Int
)

class NewsViewModel : ViewModel() {

    private val _news: MutableLiveData<List<NewsItem>> = MutableLiveData()
    val news: LiveData<List<NewsItem>> = _news

    fun getNews() {
        viewModelScope.launch {
            delay(1_000)
            _news.postValue(NewsDataSource.getNews())
        }
    }

}


