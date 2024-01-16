package com.standardListAndDetailApp.home_list.business

import com.standardListAndDetailApp.shared.business.HomesRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllHomesFromDatabaseUseCaseTest {

    private val repository: HomesRepository = mockk(relaxed = true)
    private lateinit var useCase : GetAllHomesFromDatabaseUseCase
    @Before
    fun setUp() {
        useCase = GetAllHomesFromDatabaseUseCase(repository)
    }

    @Test
    fun `GIVEN use case is call THEN get homes methode is called `() = runTest {
        useCase.execute()
        coVerify(exactly = 1) { repository.getHomes() }
    }


}