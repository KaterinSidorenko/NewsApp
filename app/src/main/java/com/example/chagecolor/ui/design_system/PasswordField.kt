package com.example.chagecolor.ui.design_system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.chagecolor.Deps
import com.example.chagecolor.R

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    password: String,
    passwordChanged: (String) -> Unit,
    showPassword: Boolean,
    showPasswordChanged: (Boolean) -> Unit,
    clear: () -> Unit,
) {
    OutlinedTextField(
        value = password,
        onValueChange = passwordChanged,
        label = { Text(stringResource(id = R.string.app_password)) },
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = Deps.Paddings.paddingMidl)
            .clickable { clear() },
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPasswordChanged(false) }) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "hide_password"
                    )
                }
            } else {
                IconButton(
                    onClick = { showPasswordChanged(true) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "hide_password"
                    )
                }
            }
        }
    )
}