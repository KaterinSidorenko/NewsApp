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
import com.example.chagecolor.Deps
import com.example.chagecolor.ui.features.news.NewsActivity
import com.example.chagecolor.R
import com.example.chagecolor.ui.features.auth.signup.RegistrationActivity
import com.example.chagecolor.getSavedCredentials
import com.example.chagecolor.ui.design_system.PasswordField
import com.example.chagecolor.ui.design_system.theme.LoginField

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

        Button(
            onClick = {
                context.startActivity(intentNews)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.light_green),
                contentColor = Color.White
            ),
            enabled = email.isNotEmpty() && textPassword.isNotEmpty() && savedCredentials != null

        ) {
            Text(
                text = stringResource(id = R.string.app_continue),
                fontSize = Deps.TextSize.Midl,
                modifier = Modifier.padding(Deps.Paddings.Little)
            )
        }

        ClickableText(
            text = AnnotatedString( stringResource(id = R.string.app_annotated)),
            style = TextStyle(
                colorResource(id = R.color.teal_700)
            ),
            onClick = {
                context.startActivity(Intent(context, RegistrationActivity::class.java))
            },
            modifier = Modifier
                .padding(Deps.Paddings.topClickableText)
        )
    }

    @Composable
    fun NewText() {
        Text(
            text = stringResource(id = R.string.app_login),
            fontSize = Deps.TextSize.Midl,
            modifier = Modifier.padding(Deps.Paddings.Large)
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun PrewiewLoginPage() {
        ColumnContent()
    }
}

