package com.standardListAndDetailApp.whish_list.business

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllWishListUseCaseTest {
    private val repository: WishListRepository = mockk(relaxed = true)
    private lateinit var useCase : GetAllWishListUseCase
    @Before
    fun setUp() {
        useCase = GetAllWishListUseCase(repository)
    }

    @Test
    fun `GIVEN use case is call THEN get all wish list methode is called `() = runTest {
        useCase.execute()
        coVerify(exactly = 1) { repository.getAllWishList() }
    }

}