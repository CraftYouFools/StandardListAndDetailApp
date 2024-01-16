package com.standardListAndDetailApp.whish_list.business

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class IsHomeInWishListUseCaseTest {


    private val repository: WishListRepository = mockk(relaxed = true)
    private lateinit var useCase : IsHomeInWishListUseCase
    private val homeId = 1
    @Before
    fun setUp() {
        useCase = IsHomeInWishListUseCase(repository)
    }

    @Test
    fun `GIVEN use case is call THEN get all wish list methode is called `() = runTest {
        useCase.execute(homeId)
        coVerify(exactly = 1) { repository.isInWishList(homeId) }
    }

}