package com.example.shutterflow.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shutterflow.R
import com.example.shutterflow.ui.theme.TealBlue

@Composable
fun TopAppBar(){
    Surface () {

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

                )

            Image(
                painter =
                painterResource(id =  R.drawable.portrait1)
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