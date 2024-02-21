package com.example.chagecolor.ui.features.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chagecolor.Deps
import com.example.chagecolor.ui.features.news.NewsPage
import com.example.chagecolor.R
import com.example.chagecolor.ui.features.auth.signup.RegistrationPage
import com.example.chagecolor.getSavedCredentials
import com.example.chagecolor.ui.design_system.PasswordField

class LoginPage : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        ColumnContent()
    }

    @Composable
    fun ColumnContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Deps.Paddings.paddingMidl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NewText()
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(1f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginPassword()
            }
        }
    }

    @Composable
    fun LoginPassword() {
        var textLogin by remember { mutableStateOf("") }
        var textPassword by remember { mutableStateOf("") }
        val context = LocalContext.current
        val intentNews = Intent(context, NewsPage::class.java)
        var showPassword by remember { mutableStateOf(value = false) }
        val savedCredentials = getSavedCredentials(context)
        if (savedCredentials != null) {

            textLogin = savedCredentials.first
            textPassword = savedCredentials.second
        }

        OutlinedTextField(
            value = textLogin,
            onValueChange = { textLogin = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Deps.Paddings.paddingMidl)
        )

        PasswordField(
            password = textPassword,
            passwordChanged = { textPassword = it },
            showPassword = showPassword,
            showPasswordChanged = { showPassword = it },
            clear = { textPassword = String() }
        )

        Button(
            onClick = {
                context.startActivity(intentNews)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.light_green),
                contentColor = Color.White
            ),
            enabled = textLogin.isNotEmpty() && textPassword.isNotEmpty() && savedCredentials != null

        ) {
            Text(
                text = stringResource(id = R.string.app_continue),
                fontSize = Deps.TextSize.fontMidl,
                modifier = Modifier.padding(Deps.Paddings.paddingLittle)
            )
        }

        ClickableText(
            text = AnnotatedString("Вы ещё не зарегистрированы?"),
            style = TextStyle(
                colorResource(id = R.color.teal_700)
            ),
            onClick = {
                context.startActivity(Intent(context, RegistrationPage::class.java))
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { /* no-op */ }
        )
    }

    @Composable
    fun NewText() {
        Text(
            text = stringResource(id = R.string.app_login),
            fontSize = Deps.TextSize.fontMidl,
            modifier = Modifier.padding(Deps.Paddings.paddingLarge)
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun PrewiewLoginPage() {
        ColumnContent()
    }
}

