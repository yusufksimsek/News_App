package com.example.news_app.domain.usecases.app_entry

import com.example.news_app.domain.manager.LocalUseManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUseManager: LocalUseManager
) {
    operator fun invoke(): Flow<Boolean>{
        return localUseManager.readAppEntry()
    }

}