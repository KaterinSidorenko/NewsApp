package com.example.chagecolor.ui.design_system

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
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
