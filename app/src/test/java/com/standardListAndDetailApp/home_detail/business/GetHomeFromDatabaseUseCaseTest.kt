package com.standardListAndDetailApp.home_detail.business

import com.standardListAndDetailApp.shared.business.HomesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetHomeFromDatabaseUseCaseTest {

    private val repository: HomesRepository = mockk(relaxed = true)
    private lateinit var useCase : GetHomeFromDatabaseUseCase
    private val homeId = 1
    @Before
    fun setUp() {
        useCase = GetHomeFromDatabaseUseCase(repository)
    }

    @Test
    fun `GIVEN use case is call THEN get home methode is called `() = runTest {
            useCase.execute(homeId)
            coVerify(exactly = 1) { repository.getHome(homeId) }
    }

}