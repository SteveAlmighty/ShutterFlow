package com.example.shutterflow.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shutterflow.R
import com.example.shutterflow.data.UserPreferencesManager
import com.example.shutterflow.presentation.gallery.PhotoGalleryViewModel
import com.example.shutterflow.presentation.home.UserSettingsViewModel
import com.example.shutterflow.presentation.profile.components.AlbumTab
import com.example.shutterflow.presentation.profile.components.ProfilePicturePicker
import com.example.shutterflow.presentation.profile.components.avatarOptions
import com.example.shutterflow.ui.theme.TealBlue
import kotlinx.coroutines.launch
import java.io.File


val sampleImages = listOf(
    R.drawable.astro1,
    R.drawable.food1,
    R.drawable.macro1,
    R.drawable.nature1,
    R.drawable.landscape1,
    R.drawable.street1
)

@SuppressLint("RememberReturnType")
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: PhotoGalleryViewModel,
    sharedVm: UserSettingsViewModel
){
    val scrollState = rememberScrollState()

    val images by viewModel.images.collectAsState()

    val userName by sharedVm.userName.collectAsState()

    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val selectedImageName by UserPreferencesManager.getProfilePictureUriFlow(context).collectAsState(initial = null)

    val selectedImageResId = remember(selectedImageName) {
        avatarOptions.find { it.second == selectedImageName }?.first ?: R.drawable.watermelon1
    }

    var showImagePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = Color.DarkGray,
                modifier = Modifier.clickable { navController.navigate("/home") }
            )
            Text(
                "My Profile",
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

        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model =   selectedImageResId,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable { showImagePicker = true },
                contentScale = ContentScale.Crop
            )
        }

        if (showImagePicker) {
            AlertDialog(
                onDismissRequest = { showImagePicker = false },
                title = { Text("Select Profile Picture") },
                text = {
                    ProfilePicturePicker(
                        context = context,
                        currentSelection = selectedImageName
                    ) { _, imageName ->
                        scope.launch {
                            UserPreferencesManager.setProfilePictureUri(context, imageName)
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showImagePicker = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            userName?.let {
                Text(
                    text = it,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = 10.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

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
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clickable { navController.navigate("/gallery") }
                        ,
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
                    items(images.take(6)) { image ->
                        AsyncImage(
                            model =  File(image.filePath),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }

               AlbumTab(navController, viewModel)

            }
        }
    }
}



