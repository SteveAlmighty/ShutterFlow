package com.example.shutterflow.presentation.home.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shutterflow.R
import com.example.shutterflow.ui.theme.TealBlue


@Composable
fun PracticeTab(
    item: PracticeTabData
){

    Card(
        modifier = Modifier
            .width(170.dp)
            .height(200.dp)
            .clickable {  },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
            // 1. Image Section

            AsyncImage(
                model = item.image,
                contentDescription = "Card Image",
                modifier = Modifier
                    .width(170.dp).
                    height(110.dp),
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
    val image: Int
)

@Composable
fun PracticeCarousel(
    items: List<PracticeTabData>
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
                text = "Practice challenges",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "See All",
                modifier = Modifier.padding(end = 10.dp),
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






@Preview(showBackground = true)
@Composable
fun PracticeTabPrev(){
    val samplePracticeTabs = listOf(
        PracticeTabData(
            id = 1,
            title = "Portraits",
            description = "Master depth & focus",
            image = R.drawable.portrait1 // Replace with your actual drawable resource
        ),
        PracticeTabData(
            id = 2,
            title = "Landscapes",
            description = "Capture scenic views",
            image = R.drawable.landscape1 // Replace with your actual drawable resource
        ),
        PracticeTabData(
            id = 3,
            title = "Street Style",
            description = "Urban life moments",
            image = R.drawable.street1 // Replace with your actual drawable resource
        ),
        PracticeTabData(
            id = 4,
            title = "Food",
            description = "Capture culinary moments",
            image = R.drawable.food1 // Replace with your actual drawable resource
        ),
        PracticeTabData(
            id = 5,
            title = "Night Sky",
            description = "Astrophotography basics",
            image = R.drawable.astro1
        ),
        PracticeTabData(
            id = 6,
            title = "Macro",
            description = "Capture stunning details",
            image = R.drawable.macro1
        )

    )
    PracticeCarousel(items = samplePracticeTabs)
}