package com.example.news_app.domain.usecases.news

import com.example.news_app.data.local.NewsDao
import com.example.news_app.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {

    operator fun invoke(article: Article){
        newsDao.upsert(article)
    }
}