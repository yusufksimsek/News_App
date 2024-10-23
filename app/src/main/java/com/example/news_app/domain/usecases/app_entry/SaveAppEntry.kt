package com.example.news_app.domain.usecases.app_entry

import com.example.news_app.domain.manager.LocalUseManager

class SaveAppEntry(
    private val localUseManager: LocalUseManager
) {

    suspend operator fun invoke(){
        localUseManager.saveAppEntry()
    }

}