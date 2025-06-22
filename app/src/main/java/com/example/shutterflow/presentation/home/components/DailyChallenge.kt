package com.example.shutterflow.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shutterflow.ui.theme.LightTeal
import com.example.shutterflow.ui.theme.TealBlue
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime



data class ChallengeItem(
    val title: String,
    val url: String
)


val dailyChallenges = listOf(
    ChallengeItem(title = "Master the Art of Bokeh Backgrounds", url = "https://photographylife.com/better-bokeh-photography"),
    ChallengeItem(title = "Capture the Wild: A Wildlife Photo Adventure", url = "https://photographylife.com/wildlife-photography-tips-for-beginners"),
    ChallengeItem(title = "Frame the Skyline: Cityscape Challenge", url = "https://photographylife.com/cityscape-photography-tips-for-beginners"),
    ChallengeItem(title = "Freeze the Action: Sports Photography Prompt", url = "https://photographylife.com/sports-photography-tips"),
    ChallengeItem(title = "Feathered Focus: Photograph a Bird in Motion", url = "https://photographylife.com/20-tips-for-bird-photography"),
    ChallengeItem(title = "Delicious Details: Food Photography at Its Best", url = "https://photographylife.com/food-photography-tips-introduction"),
    ChallengeItem(title = "Chase the Blue Hour: Magical Light Moments", url = "https://photographylife.com/blue-hour"),
    ChallengeItem(title = "Shoot the Heights: Photograph a Cathedral Interior", url = "https://photographylife.com/how-to-photograph-cathedrals"),
    ChallengeItem(title = "Texture Hunt: Capture Intriguing Surface Patterns", url = "https://photographylife.com/how-to-photograph-textures"),
    ChallengeItem(title = "Shoot Indoors: Tell a Story with Indoor Lighting", url = "https://photographylife.com/how-to-take-good-photos-indoors"),
    ChallengeItem(title = "Capture the Outdoors: Nature in Everyday Places", url = "https://photographylife.com/how-to-take-better-photographs-outdoors"),
    ChallengeItem(title = "Own the Night: Take a Stunning Low-Light Shot", url = "https://photographylife.com/how-to-take-better-photos-at-night"),
    ChallengeItem(title = "Reflect and Shoot: Water or Mirror Reflections", url = "https://photographylife.com/reflection-photos"),
    ChallengeItem(title = "Manual Mode Only: Full Control Challenge", url = "https://photographylife.com/manual-mode-auto-iso-wildlife-photography"),
    ChallengeItem(title = "Low Light Mastery: Push the Limits of ISO", url = "https://photographylife.com/nature-photography-tips"),
    ChallengeItem(title = "Nature at Its Finest: Plants, Trees, or Landscapes", url = "https://photographylife.com/nature-photography-tips"),
    ChallengeItem(title = "Street Stories: Capture a Scene from the Streets", url = "https://photographylife.com/street-photography-tips-for-beginners"),
    ChallengeItem(title = "Cook and Click: Photograph Food in Natural Settings", url = "https://photographylife.com/tips-on-photographing-food-outdoors"),
    ChallengeItem(title = "Dog Days: Take a Beautiful Portrait of a Dog", url = "https://photographylife.com/dog-photography"),
    ChallengeItem(title = "Think in Shapes: An Abstract Photography Challenge", url = "https://photographylife.com/abstract-photography-tips-and-ideas"),
    ChallengeItem(title = "Try This Today: A Fresh Photography Idea to Explore", url = "https://photographylife.com/photography-ideas"),
    ChallengeItem(title = "Zoom In: Discover a Macro World Around You", url = "https://photographylife.com/macro-photography-tips-for-beginners"),
    ChallengeItem(title = "Lines & Angles: Capture an Architectural Masterpiece", url = "https://photographylife.com/architectural-photography-tutorial"),
    ChallengeItem(title = "Paint with Light: Nighttime Long Exposure Fun", url = "https://photographylife.com/better-light-painting-photos"),
    ChallengeItem(title = "Sun-Kissed Faces: An Outdoor Portrait Challenge", url = "https://photographylife.com/outdoor-portrait-photography-tips"),
    ChallengeItem(title = "Frame the Space: Real Estate Photography Tips in Action", url = "https://photographylife.com/real-estate-photography-tips"),
    ChallengeItem(title = "Plant Portraits: Capture the Beauty of Botany", url = "https://photographylife.com/six-tips-for-better-photographs-of-plants")

)


fun getTodayChallenge(challenges: List<ChallengeItem>): ChallengeItem {
    val dayOfYear = LocalDate.now().dayOfYear
    return challenges[dayOfYear % challenges.size]
}

fun getTimeUntilMidnight(): Duration {
    val now = LocalDateTime.now()
    val midnight = now.toLocalDate().plusDays(1).atStartOfDay()
    return Duration.between(now, midnight)
}


@Composable
fun DailyChallengeTab(
    challenge: ChallengeItem,
    onClick: (String) -> Unit
) {
    var timeLeft by remember { mutableStateOf(getTimeUntilMidnight()) }


    LaunchedEffect(Unit) {
        while (true) {
            delay(60000) // Update every second
            timeLeft = getTimeUntilMidnight()
        }
    }

    val hours = timeLeft.toHours()
    val minutes = (timeLeft.toMinutes() % 60)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = Brush.horizontalGradient(colors = listOf(LightTeal, TealBlue)))
                .padding(16.dp)
        ) {
            Text("📸 Daily Challenge", style = MaterialTheme.typography.titleMedium, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(challenge.title, color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "⏳ Expires in: ${hours}h ${minutes}m",
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { onClick(challenge.url) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Get Inspired", color = TealBlue)
            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun Challprev(){
//    DailyChallengeTab()
//}