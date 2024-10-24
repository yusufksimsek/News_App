package com.example.news_app.di

import android.app.Application
import com.example.news_app.data.manager.LocalUserManagerImpl
import com.example.news_app.data.remote.NewsApi
import com.example.news_app.data.repository.NewsRepositoryImpl
import com.example.news_app.domain.manager.LocalUseManager
import com.example.news_app.domain.repository.NewsRepository
import com.example.news_app.domain.usecases.app_entry.AppEntryUseCases
import com.example.news_app.domain.usecases.app_entry.ReadAppEntry
import com.example.news_app.domain.usecases.app_entry.SaveAppEntry
import com.example.news_app.domain.usecases.news.GetNews
import com.example.news_app.domain.usecases.news.NewsUseCases
import com.example.news_app.domain.usecases.news.SearchNews
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
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }

}