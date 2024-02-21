package com.example.chagecolor.ui.features.news

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chagecolor.Deps
import com.example.chagecolor.R
import com.example.chagecolor.data.NewsDataSource.getNews
import com.example.chagecolor.ui.design_system.Back
import com.example.chagecolor.ui.features.auth.login.LoginActivity


class NewsActivity : ComponentActivity() {

    val viewModel = NewsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = NewsViewModel()

        viewModel.news.observe(this) { news ->
            setContent {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.Start
                ) {
                    Back()
                    news.forEach { newsItem ->
                        ListItem(newsItem)
                    }
                }
            }
        }
        viewModel.getNews()
    }
}


@Composable
private fun ListItem(newsItem: NewsItem) {
    val news = newsItem.news
    val prof = newsItem.prof
    val imageId = newsItem.imageId
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Deps.Paddings.List),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {

        Box() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(Deps.Paddings.Little)
                        .size(Deps.Size.imageCald)
                        .clip(CircleShape)
                )
                Column(modifier = Modifier.padding(Deps.Paddings.Midl)) {
                    Text(text = stringResource(id = R.string.news_item_title, news))
                    Text(text = stringResource(id = R.string.news_item_description, prof))
                }

            }
        }
    }
}
