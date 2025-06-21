//package com.example.shutterflow.presentation.explore
//
//import android.annotation.SuppressLint
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.slideInVertically
//import androidx.compose.animation.slideOutVertically
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import coil.compose.AsyncImage
//import com.google.gson.Gson
//import kotlinx.coroutines.delay
//import java.net.URLDecoder
//
//// --- Data Models ---
//data class TutorialSection(
//    val imageRes: String,
//    val text: String
//)
//
//data class Tutorial(
//    val id: Int,
//    val title: String,
//    val sections: List<TutorialSection>,
//    val description: String,
//    val image: String,
//    val color: String,
//    val duration: String,
//    val url: String
//)
//
//
//
//// --- Tutorial Screen ---
//@SuppressLint("DiscouragedApi")
//@Composable
//fun TutorialScreen(tutorialJson: String) {
//
//    val scrollState = rememberScrollState()
//
//    val tutorial = remember {
//        Gson().fromJson(URLDecoder.decode(tutorialJson, "UTF-8"), Tutorial::class.java)
//    }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text(
//            text = tutorial.title,
//            fontSize = 28.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        tutorial.sections.forEachIndexed { index, section ->
//            var visible by remember { mutableStateOf(false) }
//
//            LaunchedEffect(Unit) {
//                delay(index * 300L)
//                visible = true
//            }
//
//
////            val context = LocalContext.current
////            val imageResId = remember(section.imageRes) {
////                context.resources.getIdentifier(section.imageRes, "drawable", context.packageName)
////            }
//
//
//            AnimatedVisibility(
//                visible = visible,
//                enter = fadeIn(animationSpec = tween(500)) + slideInVertically(initialOffsetY = { it / 2 }),
//                exit = fadeOut() + slideOutVertically()
//            ) {
//                Column(modifier = Modifier.padding(vertical = 12.dp).verticalScroll(scrollState)) {
//                    AsyncImage(
//                        model =  section.imageRes,
//                        contentDescription = null,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(200.dp)
//                            .clip(RoundedCornerShape(10.dp))
//                        ,
//                        contentScale = ContentScale.Crop
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(section.text, fontSize = 16.sp)
//                }
//            }
//        }
//    }
//}
//
