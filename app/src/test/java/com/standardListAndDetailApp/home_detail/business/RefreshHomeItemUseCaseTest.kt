package com.standardListAndDetailApp.home_detail.business

import com.standardListAndDetailApp.shared.business.HomesRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class RefreshHomeItemUseCaseTest {

    private val repository: HomesRepository = mockk(relaxed = true)
    private lateinit var useCase : RefreshHomeItemUseCase
    private val homeId = 1
    @Before
    fun setUp() {
        useCase = RefreshHomeItemUseCase(repository)
    }

    @Test
    fun `GIVEN use case is call THEN refresh home methode is called `() = runTest {
        useCase.execute(homeId)
        coVerify(exactly = 1) { repository.refreshHomeItem(homeId) }
    }

}