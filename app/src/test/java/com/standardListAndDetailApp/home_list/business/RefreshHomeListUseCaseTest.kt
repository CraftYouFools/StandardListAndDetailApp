package com.standardListAndDetailApp.home_list.business

import com.standardListAndDetailApp.shared.business.HomesRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RefreshHomeListUseCaseTest {

    private val repository: HomesRepository = mockk(relaxed = true)
    private lateinit var useCase : RefreshHomeListUseCase
    @Before
    fun setUp() {
        useCase = RefreshHomeListUseCase(repository)
    }

    @Test
    fun `GIVEN use case is call THEN refresh list methode is called `() = runTest {
        useCase.execute()
        coVerify(exactly = 1) { repository.refreshList() }
    }
}