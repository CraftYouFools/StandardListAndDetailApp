package com.standardListAndDetailApp.home_detail.business

import com.standardListAndDetailApp.shared.business.HomesRepository
import javax.inject.Inject

class RefreshHomeItemUseCase @Inject constructor(private val repository: HomesRepository) {
    suspend fun execute(homeId: Int) {
        repository.refreshHomeItem(homeId)
    }

}