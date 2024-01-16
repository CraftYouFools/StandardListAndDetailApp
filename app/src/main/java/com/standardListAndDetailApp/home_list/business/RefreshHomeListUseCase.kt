package com.standardListAndDetailApp.home_list.business

import com.standardListAndDetailApp.shared.business.HomesRepository
import javax.inject.Inject

class RefreshHomeListUseCase @Inject constructor(private val homesRepository: HomesRepository){
    suspend fun execute() {
        homesRepository.refreshList()
    }
}