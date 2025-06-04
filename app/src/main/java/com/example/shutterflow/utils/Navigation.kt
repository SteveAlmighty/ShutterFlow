package com.example.shutterflow.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shutterflow.R
import com.example.shutterflow.presentation.explore.ExploreScreen
import com.example.shutterflow.presentation.gallery.GalleryScreen
import com.example.shutterflow.presentation.home.HomeScreen
import com.example.shutterflow.presentation.log.LogScreen
import com.example.shutterflow.presentation.profile.ProfileScreen
import com.example.shutterflow.ui.theme.TealBlue


@Composable
fun NavHostScreen() {
    val navController = rememberNavController()
    var bottomBarVisibility by remember {
        mutableStateOf(true)

    }
    Scaffold(bottomBar = {

        AnimatedVisibility(visible = bottomBarVisibility) {
            NavigationBottomBar(
                navController = navController,
                items = listOf(
                    NavItem(route = "/home", icon = R.drawable.home),
                    NavItem(route = "/explore", icon = R.drawable.explore),
                    NavItem(route = "/log", icon = R.drawable.log),
                    NavItem(route = "/profile", icon = R.drawable.profile)
                )
            )
        }
    },
        floatingActionButton = {
            AnimatedVisibility(visible = bottomBarVisibility) {
                FloatingActionButton(
                    onClick = {},
                    containerColor = TealBlue,
                    modifier = Modifier
                        .offset(y = 43.dp)
                        .clip(CircleShape)
                        .size(70.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add",)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,


    ) {
        NavHost(
            navController = navController,
            startDestination = "/home",
            modifier = Modifier.padding(it)
        ) {
            composable(route = "/home") {
                bottomBarVisibility = true
                HomeScreen()
            }

            composable(route = "/explore") {
                bottomBarVisibility = false
                ExploreScreen()
            }
            composable(route = "/log") {
                bottomBarVisibility = true
                LogScreen()
            }

            composable(route = "/profile") {
                bottomBarVisibility = false
                ProfileScreen()
            }
            composable(route = "/gallery") {
                bottomBarVisibility = true // Show the bottom bar if you want it visible
                GalleryScreen()
            }
        }
    }
}




data class NavItem(
    val route: String,
    val icon: Int
)

@Composable
fun NavigationBottomBar(
    navController: NavController,
    items: List<NavItem>
) {
    // Bottom Navigation Bar
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    BottomAppBar (
        modifier = Modifier.height(100.dp) ,//
        tonalElevation = 4.dp
    ){
        // Bottom Navigation Items

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = null)
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = TealBlue,
                    selectedIconColor = TealBlue,
                    unselectedTextColor = Color.Gray,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}
