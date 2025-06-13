package com.example.shutterflow.presentation.profile.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shutterflow.R


val avatarOptions = listOf(
    R.drawable.avocado1 to "avatar1",
    R.drawable.android1 to "avatar2",
    R.drawable.rubber1 to "avatar3",
    R.drawable.lemon1 to "avatar4",
    R.drawable.flowers1 to "avatar5",
    R.drawable.paper1 to "avatar6",
    R.drawable.seagull1 to "avatar7",
    R.drawable.watermelon1 to "avatar8",
    R.drawable.woman1 to "avatar9",
)

@Composable
fun ProfilePicturePicker(
    context: Context,
    currentSelection: String?,
    onImageSelected: (Int, String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(250.dp)
    ) {
        items(avatarOptions) { (resId, name) ->
            val isSelected = currentSelection == name
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .border(
                        width = if (isSelected) 3.dp else 1.dp,
                        color = if (isSelected) Color.Blue else Color.Gray,
                        shape = CircleShape
                    )
                    .clickable {
                        onImageSelected(resId, name)
                    }
            ) {
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}
