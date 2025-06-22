package com.example.shutterflow.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shutterflow.ui.theme.TealBlue


@Composable
fun PracticeTab(
    item: PracticeTabData,
){
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier
            .width(170.dp)
            .height(200.dp)
            .clickable { uriHandler.openUri(item.url) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
            // 1. Image Section

            AsyncImage(
                model = item.image,
                contentDescription = "Card Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                contentScale = ContentScale.Crop
            )

            // 2. Title and Description
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)

            ) {
                Text(
                    text = item.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 10.dp, top = 4.dp)
                )
            }

    }

}

data class PracticeTabData(
    val id: Int,
    val title: String,
    val description: String,
    val image: Int,
    val url: String
)

@Composable
fun PracticeCarousel(
    items: List<PracticeTabData>,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tutorials",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "See All",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { navController.navigate("tutorials") },
                fontSize = 12.sp,
                color = TealBlue
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items, key = { it.id }) { item ->
                PracticeTab(item = item)
            }
        }

    }

}






