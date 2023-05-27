package com.gostev.news.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.gostev.news.navigation.screens.*
import com.gostev.news.ui.theme.NewsTheme
import com.gostev.news.utils.SPLASH_SCREEN

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NewsTheme() {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        AnimatedNavHost(
            navController = navController,
            startDestination = SPLASH_SCREEN,
            modifier = modifier,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            splashScreen(onClickNavigationToNews = {
                navController.popBackStack()
                navController.navigateToNews()
            })
            newsScreen(currentRoute = currentRoute,
                onClickBottomNavigation = {
                    navController.navigate(it)
                })
            searchScreen(currentRoute = currentRoute,
                onClickBottomNavigation = {
                    navController.navigate(it)
                },
                onClickItem = {
                    it.url?.let { it1 -> navController.navigateToWeb(it1) }
                })
            webScreen(currentRoute = currentRoute)
            favoriteScreen(currentRoute = currentRoute,   onClickBottomNavigation = {
                navController.navigate(it)
            },
                onClickItem = {
                    it.url?.let { it1 -> navController.navigateToWeb(it1) }
                })
        }
    }
}