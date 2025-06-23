package com.example.shutterflow.presentation.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shutterflow.R
import com.example.shutterflow.presentation.home.UserSettingsViewModel
import com.example.shutterflow.presentation.profile.components.avatarOptions
import com.example.shutterflow.ui.theme.TealBlue

@SuppressLint("RememberReturnType")
@Composable
fun TopAppBar(
    navController: NavController,
    sharedVm: UserSettingsViewModel
){
    val selectedImageName by sharedVm.profileImageName.collectAsState()
    val selectedResId = remember(selectedImageName) {
        avatarOptions.find { it.second == selectedImageName }?.first ?: R.drawable.watermelon1
    }

    Surface {

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                painter = painterResource(id = R.drawable.bluecamera),
                contentDescription = "Camera",
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 15.dp)
            )

            Text(
                text = "ShutterFlow",
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 6.dp),
                fontSize = 20.sp,
                color = TealBlue
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.notifications),
                contentDescription = "notification",
                modifier = Modifier.clickable { navController.navigate("/log") }

                )

            AsyncImage(
                model =
                selectedResId
                ,
                contentDescription = "ProfilePicture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = 15.dp,start = 15.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)


            )

        }

    }
}