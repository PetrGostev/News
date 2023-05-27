package com.gostev.news.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gostev.news.R
import com.gostev.news.ui.theme.Purple80
import com.gostev.news.utils.FAVORITE_SCREEN
import com.gostev.news.utils.NEWS_SCREEN
import com.gostev.news.utils.SEARCH_SCREEN

private const val NEWS ="news"
private const val SEARCH ="Search"
private const val FAVORITE ="Favorite"

sealed class BottomItem(
    val title: String,
    val icon: Int,
    val route: String
){
    object ScreenHome : BottomItem(NEWS, R.drawable.newspaper, NEWS_SCREEN)
    object ScreenSearch : BottomItem(SEARCH, R.drawable.search, SEARCH_SCREEN)
    object ScreenFavorite : BottomItem(FAVORITE, R.drawable.favorite, FAVORITE_SCREEN)
}

@Composable
fun NewsBottomBar(
    currentRoute: String,
    onClickBottomNavigation: (route: String) -> Unit,
) {
    val listItems = listOf(
        BottomItem.ScreenHome,
        BottomItem.ScreenSearch,
        BottomItem.ScreenFavorite,
    )

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = Purple80,
    ) {

        listItems.forEach { item ->
            NavigationRailItem(
                selected = currentRoute == item.route,
                onClick = {
                        onClickBottomNavigation(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "",
                    )
                },
                modifier = Modifier.weight(1f),
                enabled = item.route != currentRoute,
                label = {
                    Text(text = item.title, fontSize = 9.sp)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun PreviewNewsBottomBar() {
    NewsBottomBar(NEWS_SCREEN) {}
}
