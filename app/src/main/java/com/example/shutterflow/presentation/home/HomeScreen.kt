package com.example.shutterflow.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shutterflow.R
import com.example.shutterflow.presentation.home.components.LearnList
import com.example.shutterflow.presentation.home.components.LearnTabData
import com.example.shutterflow.presentation.home.components.PracticeCarousel
import com.example.shutterflow.presentation.home.components.PracticeTabData
import com.example.shutterflow.presentation.home.components.ProgressTab
import com.example.shutterflow.presentation.home.components.TopAppBar

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

@Composable
fun HomeScreen(){
  val scrollState = rememberScrollState()
  Surface {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
    ) {

      TopAppBar()

      HorizontalDivider(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp))

      ProgressTab()

      HorizontalDivider(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp))

      PracticeCarousel(items = samplePracticeTabs)

      HorizontalDivider(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp))

      LearnList(items = sampleLearnTabs)


    }
  }



}






@Preview(showBackground = true)
@Composable
fun HomeScreenPrev(){
  HomeScreen()
}