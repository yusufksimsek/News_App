package com.example.news_app.presentation.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.news_app.domain.model.Article
import com.example.news_app.presentation.Dimens.ExtraSmallPadding2
import com.example.news_app.presentation.Dimens.mediumPadding

@SuppressLint("SuspiciousIndentation")
@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick:(Article) -> Unit
) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = articles.size,) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onClick = { onClick(article) })
                }
                }
        }
}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick:(Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(articles)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = articles.itemCount,) {
                articles[it]?.let {article ->
                    ArticleCard(article = article, onClick = { onClick(article) } )
                }
            }
        }
    }

}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
) : Boolean{

    val loadState = articles.loadState
    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when{
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null ->{
            EmptyScreen(error = error)
            false
        }
        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(mediumPadding)) {
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = mediumPadding)
            )
        }
    }

}