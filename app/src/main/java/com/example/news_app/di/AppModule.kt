package com.example.news_app.di

import android.app.Application
import com.example.news_app.data.manager.LocalUserManagerImpl
import com.example.news_app.domain.manager.LocalUseManager
import com.example.news_app.domain.usecases.app_entry.AppEntryUseCases
import com.example.news_app.domain.usecases.app_entry.ReadAppEntry
import com.example.news_app.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}