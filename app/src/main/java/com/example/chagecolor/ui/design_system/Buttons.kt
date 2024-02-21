package com.example.chagecolor.ui.design_system

import android.content.Intent
import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chagecolor.Deps
import com.example.chagecolor.R
import com.example.chagecolor.ui.features.auth.login.LoginActivity

    @Composable
    fun Back() {
        val context = LocalContext.current
        val intent = Intent(context, LoginActivity::class.java)
        Button(

            onClick = {
                context.startActivity(intent)
            },
            modifier = Modifier
                .padding(Deps.Paddings.Midl),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.light_red),
                contentColor = colorResource(id = R.color.white)
            )
        ) {
            Text(stringResource(R.string.app_back))
        }
    }
@Composable
fun CustomButton(
    buttonText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.light_green),
            contentColor =  colorResource(id = R.color.white)
        ),
        enabled = enabled
    ) {
        Text(
            text = buttonText,
            fontSize = MaterialTheme.typography.button.fontSize,
            modifier = Modifier.padding(8.dp)
        )
    }
}
