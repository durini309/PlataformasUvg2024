package com.uvg.ejercicioslabs.ejercicios.bottomNavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationMainScreen() {
    val navController = rememberNavController()
    val menuItems = listOf(
        BottomNavigationItem(
            title = "Home",
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            hasNews = false,
            badgeCount = null,
            destination = BNHomeDestination
        ),
        BottomNavigationItem(
            title = "Messages",
            unselectedIcon = Icons.Outlined.Message,
            selectedIcon = Icons.Filled.Message,
            hasNews = true,
            badgeCount = 6,
            destination = BNMessagesDestination
        ),
        BottomNavigationItem(
            title = "Profile",
            unselectedIcon = Icons.Outlined.PersonOutline,
            selectedIcon = Icons.Filled.Person,
            hasNews = true,
            badgeCount = null,
            destination = BNProfileDestination
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    Scaffold(
        bottomBar = {
            NavigationBar {
                menuItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.destination)
                        },
                        label = { Text(text = item.title) },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if(item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if(item.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BNHomeDestination,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable<BNHomeDestination> {
                BNHomeScreen(modifier = Modifier.fillMaxWidth())
            }
            composable<BNMessagesDestination> {
                BNMessagesScreen(modifier = Modifier.fillMaxWidth())
            }
            composable<BNProfileDestination> {
                BNProfileScreen(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}