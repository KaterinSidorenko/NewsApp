package com.example.chagecolor.ui.design_system.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
fun LoginField(
    modifier: Modifier = Modifier,
    textlogin: String,
    textloginChanged: (String) -> Unit,
    clear: () -> Unit,
) {

    OutlinedTextField(
        value = textlogin,
        onValueChange = textloginChanged,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        label = { Text(stringResource(id = R.string.app_email)) },
        modifier = modifier
            .fillMaxWidth()
            .clickable { clear() }
    )

}
