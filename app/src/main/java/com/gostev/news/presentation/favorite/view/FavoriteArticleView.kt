package com.gostev.news.presentation.favorite.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gostev.news.R
import com.gostev.news.data.api.response.Article
import com.gostev.news.utils.DateFormat

@SuppressLint("SuspiciousIndentation")
@Composable
fun FavoriteArticleView(
    article: Article,
    onClickItem: (article: Article) -> Unit,
    onClickDeleteFavorite: () -> Unit,
) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            val context = LocalContext.current
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = stringResource(id = R.string.image),
                    placeholder = painterResource(id = R.drawable.image_placholder),
                    error = painterResource(id = R.drawable.image_placholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )
            Column(modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)) {
                Text(text = article.title ?: "")
                Text(
                    text = DateFormat().stringDateToString(article.publishedAt, context),
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color.Gray
                )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(id = R.string.more_detailed),
                            modifier = Modifier.clickable { onClickItem.invoke(article) }
                                .weight(1f),
                            color = colorResource(id = R.color.purple_200)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                onClickDeleteFavorite.invoke()
                            }
                        )

                }
            }
        }
        Divider(modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Preview
@Composable
fun PreviewFavoriteArticleView() {
    FavoriteArticleView(
        Article(
            0, "Yo", "Hello", "start",
            "0000000000", "Title", null, null,
        ), {}, {}
    )
}