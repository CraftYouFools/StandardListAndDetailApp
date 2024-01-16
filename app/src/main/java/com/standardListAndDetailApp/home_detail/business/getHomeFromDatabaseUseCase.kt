package com.standardListAndDetailApp.home_detail.business

import com.standardListAndDetailApp.shared.business.HomesRepository
import com.standardListAndDetailApp.shared.business.Result
import javax.inject.Inject

class GetHomeFromDatabaseUseCase @Inject constructor(private val repository: HomesRepository) {
    suspend fun execute(homeId: Int) : Result<HomeDetails> {
        return repository.getHome(homeId)
    }
}