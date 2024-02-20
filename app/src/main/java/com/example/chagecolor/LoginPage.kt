package com.example.chagecolor

import com.example.chagecolor.RegistrationPage
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
        val intent = Intent(context, RegistrationPage::class.java)
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

        // Password input field
        OutlinedTextField(
            value = textPassword,
            onValueChange = { textPassword = it },
            label = { Text(stringResource(id = R.string.app_password)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Deps.Paddings.paddingMidl)
                .clickable { textPassword = "" },
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "hide_password"
                        )
                    }
                }
            }
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
                context.startActivity(intent)
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

