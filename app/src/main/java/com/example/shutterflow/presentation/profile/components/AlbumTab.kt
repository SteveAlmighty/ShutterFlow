package com.example.shutterflow.presentation.profile.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shutterflow.presentation.gallery.PhotoGalleryViewModel
import com.example.shutterflow.ui.theme.TealBlue

@Composable
fun AlbumTab(
    navController: NavController,
    viewModel: PhotoGalleryViewModel
) {

    val categories by viewModel.getCategoryCounts().collectAsState(initial = emptyList())


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "My Album",
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "See All",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { navController.navigate("category_list") },
                fontSize = 12.sp,
                color = TealBlue
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            userScrollEnabled = false // only show top 6
        ) {
            items(categories.size) { item ->
                AlbumCard(item = categories[item]) {
                    navController.navigate("category_gallery/${categories[item].category}")
                }
            }
        }
    }
}
