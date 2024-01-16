package com.standardListAndDetailApp.home_list.business

import com.standardListAndDetailApp.shared.business.HomesRepository
import com.standardListAndDetailApp.shared.business.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllHomesFromDatabaseUseCase @Inject constructor(private val repository: HomesRepository) {

    suspend fun execute() : Result<Flow<List<HomeListDetails>>> {
        return repository.getHomes()
    }
}