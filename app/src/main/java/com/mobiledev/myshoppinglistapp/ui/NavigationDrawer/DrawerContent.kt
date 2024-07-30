package com.mobiledev.myshoppinglistapp.ui.NavigationDrawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(onMenuSelected: (String) -> Unit) {
    Column {
        Text(text = "Menu", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))
        Divider()
        DrawerItem(text = "Produk", onClick = { onMenuSelected("Produk") })
        DrawerItem(text = "Transaksi", onClick = { onMenuSelected("Transaksi") })
    }
}