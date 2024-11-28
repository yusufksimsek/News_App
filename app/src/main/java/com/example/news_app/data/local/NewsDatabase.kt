package com.example.news_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news_app.domain.model.Article

@Database(entities = [Article::class],version = 2,)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}