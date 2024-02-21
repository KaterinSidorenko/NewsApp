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
import androidx.lifecycle.ViewModelProvider
import com.example.chagecolor.Deps
import com.example.chagecolor.R
import com.example.chagecolor.ui.features.auth.login.LoginPage


class NewsPage : ComponentActivity() {

    val viewModel = NewsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.news.observe(this) { news ->

        }

        setContent {


            Column(
                modifier = Modifier
                    .fillMaxWidth(1f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {
                Back()
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    ListItem(news = "Fire", prof = "Fire in the JK", imageId = R.drawable.news_fire)
                    ListItem(news = "Earthquake", prof = "Earthquake in Korea", imageId = R.drawable.earthquake)
                    ListItem(news = "Flood", prof = "Flood in Japan", imageId = R.drawable.flood)
                    ListItem(news = "Fire", prof = "The hospital building is in fire", imageId = R.drawable.news_fire)
                    ListItem(news = "Health", prof = "A flu vaccine vas been created", imageId = R.drawable.vaccine)
                    ListItem(news = "Earthquake", prof = "Victims have been found", imageId = R.drawable.earthquake)
                    ListItem(news = "Fire", prof = "Fire in the JK", imageId = R.drawable.news_fire)
                    ListItem(news = "Earthquake", prof = "The number of vicims", imageId = R.drawable.earthquake)
                    ListItem(news = "Fire", prof = "Fire in the JK Turkish", imageId = R.drawable.flood)
                    ListItem(news = "Fire", prof = "Fire in the JK Moscow", imageId = R.drawable.flood)

                }

            }


        }
    }

    @Composable
    fun Back() {
        val context = LocalContext.current
        val intent = Intent(context, LoginPage::class.java)
        Button(

            onClick = {
                context.startActivity(intent)
            },
            modifier = Modifier
                .padding(Deps.Paddings.paddingMidl),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.light_red),
                contentColor = colorResource(id = R.color.white)
            )
        ) {
            Text(stringResource(R.string.app_back))
        }
    }

    @Composable
    private fun ListItem(news: String, prof: String, imageId: Int) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Deps.Paddings.paddingList),
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
                            .padding(5.dp)
                            .size(64.dp)
                            .clip(CircleShape)
                    )
                    Column(modifier = Modifier.padding(Deps.Paddings.paddingMidl)) {
                        Text(text = news)
                        Text(text = prof)
                    }

                }
            }
        }
    }
}