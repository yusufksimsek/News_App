package com.example.news_app.domain.usecases.news

import com.example.news_app.data.local.NewsDao
import com.example.news_app.domain.model.Article

class DeleteArticle (
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.delete(article)
    }
}