package com.example.chagecolor.ui.features.auth.signup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chagecolor.Deps
import com.example.chagecolor.ui.features.news.NewsPage
import com.example.chagecolor.R
import com.example.chagecolor.saveCredentials
import com.example.chagecolor.ui.design_system.PasswordField

class RegistrationPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        RegistrationScreen()
    }

    @Composable
    fun RegistrationScreen() {

        var email by remember { mutableStateOf("") }
        var textPassword by remember { mutableStateOf("") }
        val context = LocalContext.current
        var showPassword by remember { mutableStateOf(value = false) }
        val isEmailValid by derivedStateOf {
            email.isNotEmpty() && "@" in email && ".com" in email
        }
        val isPasswordValid by derivedStateOf {
            textPassword.isNotEmpty() && textPassword.length >= 6 && textPassword.any { it.isUpperCase() }
        }


        //clearSharedPreferences(context)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Deps.Paddings.paddingMidl),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            NewsPage().Back()

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            )
            if (!isEmailValid) {
                Text(
                    text = "Email должен содержать символ '@' и '.com'",
                    color = MaterialTheme.colors.error
                )
            }

            PasswordField(
                password = textPassword,
                passwordChanged = { textPassword = it },
                showPassword = showPassword,
                showPasswordChanged = { showPassword = it },
                clear = { textPassword = String() }
            )

            if (!isEmailValid) {
                Text(
                    text = "Пароль должен быть не короче 6 символов и содержать хотя бы одну заглавную букву",
                    color = MaterialTheme.colors.error
                )
            }
            Button(
                onClick = {
                    val message = "Email: $email\nPassword: $textPassword"
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    saveCredentials(context, email, textPassword)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.light_green),
                    contentColor = Color.White
                ),
                enabled = email.isNotEmpty() && textPassword.isNotEmpty() && isEmailValid && isPasswordValid

            ) {
                Text(
                    stringResource(R.string.app_registration),
                    fontSize = Deps.TextSize.fontMidl,
                    modifier = Modifier.padding(Deps.Paddings.paddingLittle)
                )

            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun RegistrationScreenPreview() {

        RegistrationScreen()

    }
}
