package com.uvg.ejercicioslabs.ejercicios.navDrawer

import androidx.compose.ui.graphics.vector.ImageVector
import com.uvg.ejercicioslabs.ejercicios.bottomNavigation.BNDestination

data class NavDrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val destination: NDDestination
)

