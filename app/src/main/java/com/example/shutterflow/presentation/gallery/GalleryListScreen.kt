package com.example.shutterflow.presentation.gallery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CategoryListScreen(
    viewModel: PhotoGalleryViewModel,
    onCategoryClick: (String) -> Unit
) {
    val categories by viewModel.getCategoryCounts().collectAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        userScrollEnabled = true
    ) {
        items(categories.size) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(vertical = 8.dp)
                    .clickable { onCategoryClick(categories[index].category) },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceAround,
                ) {
                    Text(
                        text = categories[index].category,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${categories[index].count} photo(s)",
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Right
                    )
                }
            }
        }
    }


}
