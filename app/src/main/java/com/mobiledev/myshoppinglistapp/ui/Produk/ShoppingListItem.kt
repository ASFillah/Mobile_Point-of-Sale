package com.mobiledev.myshoppinglistapp.ui.Produk

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobiledev.myshoppinglistapp.Response.DataItem

@Composable
fun ShoppingListItem(
    item: DataItem,
    onEditClick: (DataItem) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    if (showDeleteConfirmation){
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(onClick = { showDeleteConfirmation = false}){
                        Text(text = "Cancel")
                    }
                    Button(onClick = {
                        item.id?.let { id -> onDeleteClick(id)}
                        showDeleteConfirmation = false
                    }) {
                        Text(text = "Delete")
                    }
                }
            },
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Delete Confirmation")
                }
            },
            text = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Are you sure you want to delete this product ?")
                }
            }
        )
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color(0xFFD0BCFF)),
                shape = RoundedCornerShape(10)
            )
    ) {
        Column(modifier = Modifier.weight(1f)) {
            item.namaProduk?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(start = 8.dp, bottom = 2.dp,top = 8.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        lineHeight = 1.sp
                    ),
                )
            }
            Text(text = "Stock: ${item.stokProduk}",
                modifier = Modifier.padding(start = 8.dp))
            Text(
                text = "Price: ${item.hargaProduk}",
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }

        Row(modifier = Modifier.align(Alignment.CenterVertically)) {
            IconButton(onClick = { onEditClick(item) }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            IconButton(onClick = { showDeleteConfirmation = true }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}