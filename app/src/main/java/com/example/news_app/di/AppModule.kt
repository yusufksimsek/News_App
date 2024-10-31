package com.example.news_app.di

import android.app.Application
import androidx.room.Room
import com.example.news_app.data.local.NewsDao
import com.example.news_app.data.local.NewsDatabase
import com.example.news_app.data.local.NewsTypeConvertor
import com.example.news_app.data.manager.LocalUserManagerImpl
import com.example.news_app.data.remote.NewsApi
import com.example.news_app.data.repository.NewsRepositoryImpl
import com.example.news_app.domain.manager.LocalUseManager
import com.example.news_app.domain.repository.NewsRepository
import com.example.news_app.domain.usecases.app_entry.AppEntryUseCases
import com.example.news_app.domain.usecases.app_entry.ReadAppEntry
import com.example.news_app.domain.usecases.app_entry.SaveAppEntry
import com.example.news_app.domain.usecases.news.DeleteArticle
import com.example.news_app.domain.usecases.news.GetNews
import com.example.news_app.domain.usecases.news.NewsUseCases
import com.example.news_app.domain.usecases.news.SearchNews
import com.example.news_app.domain.usecases.news.SelectArticle
import com.example.news_app.domain.usecases.news.SelectArticles
import com.example.news_app.domain.usecases.news.UpsertArticle
import com.example.news_app.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUseManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUseManager: LocalUseManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUseManager),
        saveAppEntry = SaveAppEntry(localUseManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNewRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao),
            selectArticle = SelectArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            application, NewsDatabase::class.java, "news_db"
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}