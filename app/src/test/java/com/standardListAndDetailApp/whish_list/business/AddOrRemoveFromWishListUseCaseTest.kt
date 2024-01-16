package com.standardListAndDetailApp.whish_list.business

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddOrRemoveFromWishListUseCaseTest {

    private val repository: WishListRepository = mockk(relaxed = true)
    private var isHomeInWishListUseCase: IsHomeInWishListUseCase = mockk(relaxed = true)
    private lateinit var useCase : AddOrRemoveFromWishListUseCase
    private val homeId = 1
    @Before
    fun setUp() {
        useCase = AddOrRemoveFromWishListUseCase(isHomeInWishListUseCase,repository)
    }

    @Test
    fun `GIVEN use case is call WHEN home is in wish list THEN remove from wish list methode is called `() = runTest {
        coEvery { isHomeInWishListUseCase.execute(homeId) } returns true
        useCase.execute(homeId)
        coVerify(exactly = 1) { repository.removeFromWishlist(homeId) }
        coVerify(exactly = 0) { repository.addToWishlist(homeId) }

    }

    @Test
    fun `GIVEN use case is call WHEN home is NOT in wish list THEN remove from wish list methode is called `() = runTest {
        coEvery { isHomeInWishListUseCase.execute(homeId) } returns false
        useCase.execute(homeId)
        coVerify(exactly = 0) { repository.removeFromWishlist(homeId) }
        coVerify(exactly = 1) { repository.addToWishlist(homeId) }
    }

}