package com.github.limerio.final_project_kotlin.ui.components.base

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import com.github.limerio.final_project_kotlin.R

@Composable
fun EmailText(email: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Text(
        text = email.lowercase(),
        style = MaterialTheme.typography.titleSmall.copy(
            textDecoration = TextDecoration.Underline
        ),
        color = MaterialTheme.colorScheme.secondary,
        modifier = modifier.clickable(
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${email}")
                }
                try {
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        context,
                        R.string.no_email_app_found,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    )
}