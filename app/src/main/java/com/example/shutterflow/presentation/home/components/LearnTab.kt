package com.example.shutterflow.presentation.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shutterflow.presentation.explore.TutorialViewModel
import com.example.shutterflow.ui.theme.TealBlue

data class Tutorial(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val color: String,
    val duration: String,
    val url: String
)


@SuppressLint("DiscouragedApi")
@Composable
fun LearnTab(
 item: Tutorial,
 onClick: () -> Unit = {}
){

    val context = LocalContext.current
    val imageResId = remember(item.image) {
        context.resources.getIdentifier(item.image, "drawable", context.packageName)
    }

    val tutorialColor = Color(android.graphics.Color.parseColor(item.color))

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 15.dp)
            .clickable { onClick() }

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
                    .background(tutorialColor.copy(alpha = 0.15f)),

                ) {
                AsyncImage(
                    model =  imageResId,
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
                        .background(tutorialColor.copy(alpha = 0.15f)),

                    ) {
                    Text(
                        text = "${item.duration} min",
                        fontSize = 7.sp,
                        color = tutorialColor,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun LearnList(
    viewModel: TutorialViewModel,
    navController: NavController
){
    val uriHandler = LocalUriHandler.current

    val tutorials by viewModel.tutorials.collectAsState()

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
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { navController.navigate("learning_resources") }
                ,
                fontSize = 12.sp,
                color = TealBlue
            )
        }

        for (tutorial in tutorials.take(5)) {
            LearnTab(
                item = tutorial,
                onClick = {
                    uriHandler.openUri(tutorial.url)
                }
            )
        }
    }
}






