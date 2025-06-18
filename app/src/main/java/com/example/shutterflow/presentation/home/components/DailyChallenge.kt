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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shutterflow.ui.theme.LightTeal
import com.example.shutterflow.ui.theme.TealBlue


@Composable
fun DailyChallengeTab() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
            Text("Capture something with leading lines", color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text("⏳ Expires in: 10h 34m", fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f))
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { /* navigate to inspiration or camera */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Get Inspired", color = TealBlue)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Challprev(){
    DailyChallengeTab()
}