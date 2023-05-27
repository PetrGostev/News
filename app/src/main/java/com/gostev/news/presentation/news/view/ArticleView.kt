package com.gostev.news.presentation.news.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

@Composable
fun ArticleView(
    article: Article,
    isVisibleImage: Boolean,
    onClickItem: (article: Article) -> Unit,
    onClickFavorite: () -> Unit,
) {
    var isFavorite by rememberSaveable {
        mutableStateOf(article.isFavorite)
    }

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            val context = LocalContext.current
            if (isVisibleImage) {
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = stringResource(id = R.string.image),
                    placeholder = painterResource(id = R.drawable.image_placholder),
                    error = painterResource(id = R.drawable.image_placholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )
            }
            Column(modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)) {
                Text(text = article.title ?: "")
                Text(
                    text = DateFormat().stringDateToString(article.publishedAt, context),
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color.Gray
                )
                if (isVisibleImage) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(id = R.string.more_detailed),
                            modifier = Modifier
                                .clickable { onClickItem.invoke(article) }
                                .weight(1f),
                            color = colorResource(id = R.color.purple_200)
                        )
                        Icon(
                            painter = painterResource(id = if (isFavorite) R.drawable.favorite else R.drawable.favorite_border),
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                if (!isFavorite) {
                                    isFavorite = true
                                    onClickFavorite.invoke()
                                }
                            }
                        )
                    }
                }
            }
        }
        Divider(modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Preview
@Composable
fun PreviewArticleView() {
    ArticleView(
        Article(
            0, "Yo", "Hello", "start",
            "0000000000", "Title", null, null,
        ), isVisibleImage = true, {}, {}
    )
}