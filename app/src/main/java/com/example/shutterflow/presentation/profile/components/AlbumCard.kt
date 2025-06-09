package com.example.shutterflow.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shutterflow.data.ImageDao.CategoryCount



@Composable
fun AlbumCard(
    item: CategoryCount,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .width(150.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF4F4F4))
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.category,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "${item.count} photos",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}


