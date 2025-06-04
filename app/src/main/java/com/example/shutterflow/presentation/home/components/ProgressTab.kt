package com.example.shutterflow.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shutterflow.R
import com.example.shutterflow.ui.theme.GreyText
import com.example.shutterflow.ui.theme.LightTeal
import com.example.shutterflow.ui.theme.TealBlue


@Composable
fun ProgressTab(){

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
                text = "Your Progress",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "View All",
                modifier = Modifier.padding(end = 10.dp),
                fontSize = 12.sp,
                color = TealBlue
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush = Brush.horizontalGradient(colors = listOf(LightTeal, TealBlue)))

        ){

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(start = 10.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "This week",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreyText
                    )

                    Text(
                        "7 photos",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        "Today: Wednesday",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreyText,
                        modifier = Modifier.padding(top = 40.dp)
                    )

                }

                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(end = 10.dp, bottom = 40.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.25f)),

                    ){
                    Image(
                        painter = painterResource(id = R.drawable.whitecamera),
                        contentDescription = "Camera",
                        modifier = Modifier
                            .size(25.dp)

                    )
                }
            }


        }

    }

}