package com.example.shutterflow.presentation.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shutterflow.presentation.home.components.LearnList
import com.example.shutterflow.presentation.home.components.PracticeTab
import com.example.shutterflow.presentation.home.samplePracticeTabs
import com.example.shutterflow.ui.theme.TealBlue


@Composable
fun ExploreScreen(
    navController: NavController,
    tutVm: TutorialViewModel
){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrowback",
                tint = Color.DarkGray,
                modifier = Modifier.clickable { navController.popBackStack() }
            )
            Text(
                text = "Photography Basics",
                color = Color.DarkGray,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color.DarkGray,
                modifier = Modifier.clickable { navController.navigate("/setting") }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tutorials",
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.titleLarge,
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .height(630.dp)
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = false // only show top 6
        ) {
            items(6) { index ->
                PracticeTab(item = samplePracticeTabs[index])
            }
        }

        LearnList(
            viewModel = tutVm,
            navController = navController
        )
    }

}

//@Preview(showBackground = true)
//@Composable
//fun ExploreTabPrev(){
//    val samplePracticeTabs = listOf(
//        PracticeTabData(
//            id = 1,
//            title = "Portraits",
//            description = "Master depth & focus",
//            image = R.drawable.portrait1 // Replace with your actual drawable resource
//        ),
//        PracticeTabData(
//            id = 2,
//            title = "Landscapes",
//            description = "Capture scenic views",
//            image = R.drawable.landscape1 // Replace with your actual drawable resource
//        ),
//        PracticeTabData(
//            id = 3,
//            title = "Street Style",
//            description = "Urban life moments",
//            image = R.drawable.street1 // Replace with your actual drawable resource
//        ),
//        PracticeTabData(
//            id = 4,
//            title = "Food",
//            description = "Capture culinary moments",
//            image = R.drawable.food1 // Replace with your actual drawable resource
//        ),
//        PracticeTabData(
//            id = 5,
//            title = "Night Sky",
//            description = "Astrophotography basics",
//            image = R.drawable.astro1
//        ),
//        PracticeTabData(
//            id = 6,
//            title = "Macro",
//            description = "Capture stunning details",
//            image = R.drawable.macro1
//        )
//
//    )
//    ExploreScreen()
//}