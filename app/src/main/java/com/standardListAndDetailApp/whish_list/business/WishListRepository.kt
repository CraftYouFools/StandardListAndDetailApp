package com.standardListAndDetailApp.whish_list.business

import com.standardListAndDetailApp.shared.business.Result
import kotlinx.coroutines.flow.Flow

interface WishListRepository {
    suspend fun isInWishList(homeId: Int): Boolean

    suspend fun addToWishlist(homeId: Int)

    suspend fun removeFromWishlist(homeId: Int)

    suspend fun getAllWishList(): Result<Flow<List<WishListDetail>>>

}