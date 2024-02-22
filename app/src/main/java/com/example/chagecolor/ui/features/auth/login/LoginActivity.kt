package com.example.chagecolor.ui.features.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.chagecolor.Deps
import com.example.chagecolor.R
import com.example.chagecolor.ui.features.auth.signup.RegistrationActivity
import com.example.chagecolor.getSavedCredentials
import com.example.chagecolor.ui.design_system.ClickText
import com.example.chagecolor.ui.design_system.LargeButton
import com.example.chagecolor.ui.design_system.LargeLable
import com.example.chagecolor.ui.design_system.PasswordField
import com.example.chagecolor.ui.design_system.theme.LoginField
import com.example.chagecolor.ui.features.news.NewsActivity

class LoginActivity : ComponentActivity() {

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
                .padding(Deps.Paddings.Midl),
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
                LargeLable(stringResource(id = R.string.app_login))
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(1f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginScreen()
            }
        }
    }

    @Composable
    fun LoginScreen() {
        var email by remember { mutableStateOf("") }
        var textPassword by remember { mutableStateOf("") }
        val context = LocalContext.current
        val intentNews = Intent(context, NewsActivity::class.java)
        var showPassword by remember { mutableStateOf(value = false) }
        val savedCredentials = getSavedCredentials(context)
        if (savedCredentials != null) {

            email = savedCredentials.first
            textPassword = savedCredentials.second
        }


        LoginField(textlogin = email,
            textloginChanged = { email = it },
            clear = { email = "" }
        )

        PasswordField(
            password = textPassword,
            passwordChanged = { textPassword = it },
            showPassword = showPassword,
            showPasswordChanged = { showPassword = it },
            clear = { textPassword = String() }
        )


        LargeButton(
            buttonText = stringResource(id = R.string.app_continue),
            onClick = {
                context.startActivity(intentNews)
            },
            enabled = email.isNotEmpty() && textPassword.isNotEmpty() && savedCredentials != null
        )

        ClickText(annotatedText = stringResource(id = R.string.app_annotated), onClick = {
            context.startActivity(Intent(context, RegistrationActivity::class.java))
        })

    }

    @Preview(showBackground = true)
    @Composable
    fun PrewiewLoginPage() {
        ColumnContent()
    }
}

