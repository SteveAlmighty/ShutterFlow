package com.example.shutterflow.presentation.explore

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shutterflow.presentation.home.components.LearnTab


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LearningResourcesScreen(
    viewModel: TutorialViewModel
) {

    Spacer(modifier = Modifier.height(10.dp))

        val uriHandler = LocalUriHandler.current

        val tutorials by viewModel.tutorials.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)

        ) {

            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    "Learning Resource",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

            }
            LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                    ) {
                items(tutorials) { tutorial ->
                    LearnTab(
                        item = tutorial,
                        onClick = {
                            uriHandler.openUri(tutorial.url)
                        }
                    )
                }
            }
        }

}

