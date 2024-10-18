package com.example.news_app.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUseManager {
    suspend fun saveAppEntry()
    fun readAppEntry(): Flow<Boolean>
}