package com.example.chagecolor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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

            OutlinedTextField(
                value = textPassword,
                onValueChange = { textPassword = it },
                label = { Text(stringResource(id = R.string.app_password)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
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
