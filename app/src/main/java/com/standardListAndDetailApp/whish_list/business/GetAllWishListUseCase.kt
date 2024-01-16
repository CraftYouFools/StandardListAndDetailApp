package com.standardListAndDetailApp.whish_list.business

import com.standardListAndDetailApp.shared.business.HomesRepository
import com.standardListAndDetailApp.shared.business.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllWishListUseCase @Inject constructor(
    private val wishListRepository: WishListRepository
) {
    suspend fun execute(): Result<Flow<List<WishListDetail>>> {
        return wishListRepository.getAllWishList()
    }

}