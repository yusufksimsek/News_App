package com.example.news_app.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.news_app.presentation.onboarding.Page
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news_app.R
import com.example.news_app.presentation.Dimens.mediumPadding
import com.example.news_app.presentation.Dimens.mediumPadding2
import com.example.news_app.presentation.onboarding.pages
import com.example.news_app.ui.theme.News_AppTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
){
    Column(modifier = Modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f)
            ,painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop

        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
            )
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}
