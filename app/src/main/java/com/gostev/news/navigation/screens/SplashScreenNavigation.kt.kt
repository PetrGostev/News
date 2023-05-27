package com.gostev.news.navigation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.gostev.news.presentation.splash.SplashScreen
import com.gostev.news.utils.SPLASH_SCREEN


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splashScreen(onClickNavigationToNews: () -> Unit) {
    composable(SPLASH_SCREEN) {
        SplashScreen(onClickNavigationToNews = {onClickNavigationToNews.invoke()})
    }
}

fun NavHostController.navigateToSplash() {
    navigate(SPLASH_SCREEN)
}