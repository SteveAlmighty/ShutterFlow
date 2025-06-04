package com.example.shutterflow.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shutterflow.R
import com.example.shutterflow.ui.theme.TealBlue

data class LearnTabData(
    val id: Int,
    val title: String,
    val description: String,
    val image: Int,
    val color: Color,
    val duration: String
)


@Composable
fun LearnTab(
 item: LearnTabData
){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 15.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .clip(RoundedCornerShape(10.dp)),

            ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 10.dp, top = 15.dp, bottom = 40.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(12))
                    .background(item.color.copy(alpha = 0.15f)),

                ) {
                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = "Camera",
                    modifier = Modifier
                        .size(25.dp)

                )
            }

            Column {
                Text(
                    text = item.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp, top = 14.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    minLines = 2,
                    modifier = Modifier.padding(start = 10.dp, top = 4.dp)
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 6.dp, bottom = 6.dp)
                        .clip(RoundedCornerShape(19))
                        .background(item.color.copy(alpha = 0.15f)),

                    ) {
                    Text(
                        text = "${item.duration} min",
                        fontSize = 7.sp,
                        color = item.color,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun LearnList(
    items: List<LearnTabData>
){
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
                text = "Learning Resources",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "More",
                modifier = Modifier.padding(end = 10.dp),
                fontSize = 12.sp,
                color = TealBlue
            )
        }
        for (item in items) {
            LearnTab(item = item)
        }
    }

}






@Preview(showBackground = true)
@Composable
fun LearnTabPrev(){
    val sampleLearnTabs = listOf(
        LearnTabData(
            id = 1,
            title = "Composition Basics",
            description = "Learn the rule of thirds and other fundamental composition techniques.",
            image = R.drawable.baseline_grid_3x3_24, // Replace with your actual drawable
            color = Color(0xFF4CAF50), // A shade of Green,
            duration = "10"
        ),
        LearnTabData(
            id = 2,
            title = "Understanding Light",
            description = "Explore natural vs. artificial light, and how to use it effectively.",
            image = R.drawable.light1,    // Replace with your actual drawable
            color = Color(0xFF2196F3) ,
            duration = "15" // A shade of Blue
        ),
        LearnTabData(
            id = 3,
            title = "Manual Mode Mastery",
            description = "Unlock your camera's full potential by mastering manual settings.",
            image = R.drawable.baseline_handyman_24, // Replace with your actual drawable
            color = Color(0xFFFF9800),
            duration = "20" // A shade of Orange
        ),
        LearnTabData(
            id = 4,
            title = "Photo Editing Fundamentals",
            description = "Basic post-processing techniques to enhance your images.",
            image = R.drawable.editphoto,     // Replace with your actual drawable
            color = Color(0xFF9C27B0),
            duration = "25" // A shade of Purple
        )
    )
    LearnList(items = sampleLearnTabs)
}