package com.example.news_app.domain.usecases

import com.example.news_app.domain.manager.LocalUseManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUseManager: LocalUseManager
) {
    suspend operator fun invoke(): Flow<Boolean>{
        return localUseManager.readAppEntry()
    }

}