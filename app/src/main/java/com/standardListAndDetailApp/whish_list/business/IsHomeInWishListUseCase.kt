package com.standardListAndDetailApp.whish_list.business

import javax.inject.Inject

class IsHomeInWishListUseCase @Inject constructor(private val repository : WishListRepository){
    suspend fun execute(homeId : Int) : Boolean {
        return repository.isInWishList(homeId)
    }
}