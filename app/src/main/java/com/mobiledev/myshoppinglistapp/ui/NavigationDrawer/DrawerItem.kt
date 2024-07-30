package com.mobiledev.myshoppinglistapp.ui.NavigationDrawer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerItem(text: String, onClick: () -> Unit) {
    TextButton(onClick = onClick, modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}