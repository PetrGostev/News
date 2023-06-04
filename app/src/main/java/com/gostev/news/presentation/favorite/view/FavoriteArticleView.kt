package com.gostev.news.presentation.favorite.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = stringResource(id = R.string.image),
            placeholder = painterResource(id = R.drawable.no_photography),
            error = painterResource(id = R.drawable.no_photography),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(400.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(10.dp)),
        )
        Text(text = article.title ?: "",
            modifier = Modifier.padding(top = 4.dp),)
        Text(
            text = DateFormat().stringDateToString(article.publishedAt),
            modifier = Modifier.padding(vertical = 4.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.more_detailed),
                modifier = Modifier
                    .clickable { onClickItem.invoke(article) }
                    .weight(1f),
                fontSize = 15.sp,
                color = colorResource(id = R.color.orange_20)
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
    Divider()
}

@Preview (showSystemUi = true)
@Composable
fun PreviewFavoriteArticleView() {
    FavoriteArticleView(
        Article(
            0, "Yo", "Hello", "start",
            "0000000000", "Title", null, null,
        ), {}, {}
    )
}