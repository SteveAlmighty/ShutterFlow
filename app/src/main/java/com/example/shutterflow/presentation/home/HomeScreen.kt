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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shutterflow.R
import com.example.shutterflow.presentation.explore.TutorialViewModel
import com.example.shutterflow.presentation.home.components.DailyChallengeTab
import com.example.shutterflow.presentation.home.components.LearnList
import com.example.shutterflow.presentation.home.components.PracticeCarousel
import com.example.shutterflow.presentation.home.components.PracticeTabData
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


@Composable
fun HomeScreen(
  sharedVm: UserSettingsViewModel,
  navController: NavController,
  tutVm: TutorialViewModel
){
  val scrollState = rememberScrollState()
  Surface {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
    ) {

      TopAppBar( navController,sharedVm)

      HorizontalDivider(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp))

//      ProgressTab()
      DailyChallengeTab()

      HorizontalDivider(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp))

      PracticeCarousel(items = samplePracticeTabs)

      HorizontalDivider(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp))

      LearnList(
          viewModel = tutVm,
          navController = navController
      )


    }
  }

}






//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPrev(){
//  HomeScreen()
//}