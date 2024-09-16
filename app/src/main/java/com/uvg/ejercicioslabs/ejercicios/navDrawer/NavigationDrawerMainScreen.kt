package com.uvg.ejercicioslabs.ejercicios.navDrawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.ejercicioslabs.ejercicios.bottomNavigation.BNHomeDestination
import com.uvg.ejercicioslabs.ejercicios.bottomNavigation.BNHomeScreen
import com.uvg.ejercicioslabs.ejercicios.bottomNavigation.BNMessagesDestination
import com.uvg.ejercicioslabs.ejercicios.bottomNavigation.BNMessagesScreen
import com.uvg.ejercicioslabs.ejercicios.bottomNavigation.BNProfileDestination
import com.uvg.ejercicioslabs.ejercicios.bottomNavigation.BNProfileScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerMainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val menuItems = listOf(
        NavDrawerItem(
            title = "Home",
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            hasNews = false,
            badgeCount = null,
            destination = NDHomeDestination
        ),
        NavDrawerItem(
            title = "Messages",
            unselectedIcon = Icons.Outlined.Message,
            selectedIcon = Icons.Filled.Message,
            hasNews = true,
            badgeCount = 6,
            destination = NDMessagesDestination
        ),
        NavDrawerItem(
            title = "Profile",
            unselectedIcon = Icons.Outlined.PersonOutline,
            selectedIcon = Icons.Filled.Person,
            hasNews = true,
            badgeCount = null,
            destination = NDProfileDestination
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                menuItems.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.destination)
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        val item = menuItems[selectedItemIndex]
                        Text(text = item.title)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = NDHomeDestination,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                composable<NDHomeDestination> {
                    NDHomeScreen(modifier = Modifier.fillMaxWidth())
                }
                composable<NDMessagesDestination> {
                    NDMessagesScreen(modifier = Modifier.fillMaxWidth())
                }
                composable<NDProfileDestination> {
                    NDProfileScreen(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}