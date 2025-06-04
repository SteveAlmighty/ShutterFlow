package com.example.shutterflow.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shutterflow.R
import com.example.shutterflow.presentation.profile.components.AlbumCard
import com.example.shutterflow.presentation.profile.components.AlbumData
import com.example.shutterflow.ui.theme.TealBlue


val sampleImages = listOf(
    R.drawable.astro1,
    R.drawable.food1,
    R.drawable.macro1,
    R.drawable.nature1,
    R.drawable.landscape1,
    R.drawable.street1
)

val sampleAlbumDataList: List<AlbumData> = listOf(

    AlbumData(
        id = 2,
        title = "Urban Landscapes",
        number = "8"
    ),
    AlbumData(
        id = 3,
        title = "Mountain Hike",
        number = "2"
    ),
    AlbumData(
        id = 4,
        title = "Family Portraits",
        number = "8"
    ),
    AlbumData(
        id = 5,
        title = "Food Photography",
        number = "13"
    ),
    AlbumData(
        id = 6,
        title = "Pet Antics",
        number = "23"
    ),
    AlbumData(
        id = 7,
        title = "Abstract Nature",
        number = "9"
    ),
    AlbumData(
        id = 8,
        title = "Road Trip Memories",
        number = "7"
    )
)


@Composable
fun ProfileScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = Color.DarkGray
            )
            Text(
                text = "My Profile",
                color = Color.DarkGray,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
                ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Image(
                    painter = painterResource(id = R.drawable.portrait1),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(100.dp))
                    ,
                    contentScale = ContentScale.Crop
                )


            Text(
                "Dominic Szlabozlai",
                fontSize = 16.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 10.dp)
            )

        }

        Surface(
            modifier = Modifier.clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .background(Color.White)

            ) {
                // Title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "My Portfolio",
                        color = Color.DarkGray,
                        modifier = Modifier.padding(start = 10.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "See All",
                        modifier = Modifier.padding(end = 10.dp),
                        fontSize = 12.sp,
                        color = TealBlue
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Portfolio Grid
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
                    items(6) { index ->
                        Image(
                            painter = painterResource(id = sampleImages[index]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }

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
                        modifier = Modifier.padding(end = 10.dp),
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
                    items(6) { index ->
                        AlbumCard(item = sampleAlbumDataList[index])
                    }
                }

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

