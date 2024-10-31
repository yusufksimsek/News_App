package com.example.news_app.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.news_app.R
import com.example.news_app.presentation.Dimens.mediumPadding
import com.example.news_app.presentation.common.ArticlesList
import com.example.news_app.presentation.navgraph.Route

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigate:(String)-> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .padding(top = mediumPadding, start = mediumPadding, end = mediumPadding)
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(mediumPadding))
        
        ArticlesList(articles = state.articles, onClick = { navigate(Route.DetailsScreen.route) })

    }
}