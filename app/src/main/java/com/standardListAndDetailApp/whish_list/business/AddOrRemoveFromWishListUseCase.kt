package com.standardListAndDetailApp.whish_list.business

import javax.inject.Inject

class AddOrRemoveFromWishListUseCase @Inject constructor(
    private val isHomeInWishListUseCase: IsHomeInWishListUseCase,
    private val repository: WishListRepository
    ){
    suspend fun execute(homeId : Int) {
        if(isHomeInWishListUseCase.execute(homeId)) {
            repository.removeFromWishlist(homeId)
        }
        else {
            repository.addToWishlist(homeId)
        }
    }

}