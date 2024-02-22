package com.example.chagecolor.ui.design_system

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import com.example.chagecolor.Deps
import com.example.chagecolor.R
import com.example.chagecolor.ui.features.auth.signup.RegistrationActivity


@Composable
fun LargeLable(lableText: String) {
    Text(
        text = lableText,
        fontSize = Deps.TextSize.Midl,
        modifier = Modifier.padding(Deps.Paddings.Large)
    )
}

@Composable
fun ClickText(
    annotatedText: String,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,

    ) {
    androidx.compose.foundation.text.ClickableText(
        onClick = onClick,
        text = AnnotatedString(annotatedText),
        style = TextStyle(
            colorResource(id = R.color.teal_700)
        ),
        modifier = modifier.padding(Deps.Paddings.topClickableText)
    )

}