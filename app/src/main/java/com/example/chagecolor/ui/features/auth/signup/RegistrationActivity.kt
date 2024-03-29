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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.chagecolor.Deps
import com.example.chagecolor.R
import com.example.chagecolor.saveCredentials
import com.example.chagecolor.ui.design_system.Back
import com.example.chagecolor.ui.design_system.LargeButton
import com.example.chagecolor.ui.design_system.PasswordField
import com.example.chagecolor.ui.design_system.theme.LoginField


class RegistrationActivity : ComponentActivity() {
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
                .padding(Deps.Paddings.Midl),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Back()

            LoginField(textlogin = email,
                textloginChanged = { email = it },
                clear = { email = "" }
            )

            if (!isEmailValid) {
                Text(
                    text = stringResource(R.string.app_annotatedErr),
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
                    text = stringResource(id = R.string.app_annotatedPasErr),
                    color = MaterialTheme.colors.error
                )
            }
            LargeButton(
                buttonText = stringResource(R.string.app_registration),
                onClick = {
                    val message = "Email: $email\nPassword: $textPassword"
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    saveCredentials(context, email, textPassword)
                },
                enabled = email.isNotEmpty() && textPassword.isNotEmpty() && isEmailValid && isPasswordValid
            )
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun RegistrationScreenPreview() {

        RegistrationScreen()

    }
}
