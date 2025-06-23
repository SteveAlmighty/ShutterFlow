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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shutterflow.R
import com.example.shutterflow.presentation.explore.TutorialViewModel
import com.example.shutterflow.presentation.home.components.DailyChallengeTab
import com.example.shutterflow.presentation.home.components.LearnList
import com.example.shutterflow.presentation.home.components.PracticeCarousel
import com.example.shutterflow.presentation.home.components.PracticeTabData
import com.example.shutterflow.presentation.home.components.TopAppBar
import com.example.shutterflow.presentation.home.components.dailyChallenges
import com.example.shutterflow.presentation.home.components.getTodayChallenge
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDateTime


val samplePracticeTabs = listOf(
  PracticeTabData(
    id = 1,
    title = "Portrait Photography",
    description = "Master depth & focus",
    image = R.drawable.portrait1,
    url = "https://photographylife.com/portrait-photography"
  ),
  PracticeTabData(
    id = 2,
    title = "Landscape Photography",
    description = "Capture scenic views",
    image = R.drawable.landscape1 ,
    url = "https://photographylife.com/landscape-photography-guide"
  ),
  PracticeTabData(
    id = 3,
    title = "Street Style",
    description = "Urban life moments",
    image = R.drawable.street1 ,
    url = "https://photographylife.com/what-is-street-photography"
  ),
  PracticeTabData(
    id = 4,
    title = "WildLife Photography",
    description = "Capture wildlife",
    image = R.drawable.wildlife,
    url = "https://photographylife.com/wildlife-photography-tutorial"
  ),
  PracticeTabData(
    id = 5,
    title = "Night Sky",
    description = "Astro photography basics",
    image = R.drawable.astro1,
    url = "https://photographylife.com/how-to-photograph-the-milky-way"
  ),
  PracticeTabData(
    id = 6,
    title = "Macro",
    description = "Capture stunning details",
    image = R.drawable.macro1,
    url = "https://photographylife.com/macro-photography-tutorial"
  ),
  PracticeTabData(
    id = 7,
    title = "B&W Photography",
    description = "Monochromatic",
    image = R.drawable.bw,// Replace with your actual drawable resource
    url = "https://photographylife.com/black-and-white-photography"
  ),
  PracticeTabData(
    id = 8,
    title = "Composition",
    description = "Compose Better Photos",
    image = R.drawable.composition,
    url = "https://photographylife.com/composition-in-photography"
  ),
  PracticeTabData(
    id = 9,
    title = "Photography Basics",
    description = "Beginner’s Guide",
    image = R.drawable.beginner,// Replace with your actual drawable resource
    url = "https://photographylife.com/photography-basics"
  ),
  PracticeTabData(
    id = 10,
    title = "Photography Videos",
    description = "Multi-Video Courses",
    image = R.drawable.ytcourse,// Replace with your actual drawable resource
    url = "https://photographylife.com/photography-videos"
  ),
  PracticeTabData(
    id = 11,
    title = "Post Processing",
    description = "how to edit images properly",
    image = R.drawable.food1,// Replace with your actual drawable resource
    url = "https://photographylife.com/post-processing-tips-for-beginners"
  )

)


@Composable
fun HomeScreen(
  sharedVm: UserSettingsViewModel,
  navController: NavController,
  tutVm: TutorialViewModel
){
  val uriHandler = LocalUriHandler.current

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


      val challenge = remember {
        mutableStateOf(getTodayChallenge(dailyChallenges))
      }

      // Optional: update every day at midnight
      LaunchedEffect(Unit) {
        while (true) {
          val now = LocalDateTime.now()
          val nextMidnight = now.toLocalDate().plusDays(1).atStartOfDay()
          val durationUntilMidnight = Duration.between(now, nextMidnight)
          delay(durationUntilMidnight.toMillis())
          challenge.value = getTodayChallenge(dailyChallenges)
        }
      }

      DailyChallengeTab(challenge = challenge.value, onClick = { url ->
        uriHandler.openUri(url)
      })

      HorizontalDivider(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp))

      PracticeCarousel(
        items = samplePracticeTabs,
        navController
      )

      HorizontalDivider(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp))

      LearnList(
          viewModel = tutVm,
        navController
      )


    }
  }

}






