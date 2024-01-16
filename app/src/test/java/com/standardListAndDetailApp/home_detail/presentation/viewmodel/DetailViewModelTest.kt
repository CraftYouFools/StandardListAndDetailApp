package com.standardListAndDetailApp.home_detail.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.standardListAndDetailApp.home_detail.business.GetHomeFromDatabaseUseCase
import com.standardListAndDetailApp.home_detail.business.RefreshHomeItemUseCase
import com.standardListAndDetailApp.home_detail.presentation.HomeDetailViewState
import com.standardListAndDetailApp.shared.business.Result
import com.standardListAndDetailApp.whish_list.business.AddOrRemoveFromWishListUseCase
import com.standardListAndDetailApp.whish_list.business.IsHomeInWishListUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DetailViewModelTest {


    private lateinit var viewModel: DetailViewModel

    private val refreshHomeItemUseCase: RefreshHomeItemUseCase = mockk(relaxed = true)
    private val getHomeFromDatabaseUseCase: GetHomeFromDatabaseUseCase = mockk(relaxed = true)
    private val isHomeInWishListUseCase: IsHomeInWishListUseCase = mockk(relaxed = true)
    private val addOrRemoveFromWishListUseCase: AddOrRemoveFromWishListUseCase =
        mockk(relaxed = true)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    private val homeId = 1

    @Before
    fun setup() {
        viewModel = DetailViewModel(
            refreshHomeItemUseCase,
            getHomeFromDatabaseUseCase,
            isHomeInWishListUseCase,
            addOrRemoveFromWishListUseCase,
            dispatcher
        )
    }

    @Test
    fun `Test loading state is shown before the content`() {
        val viewStates = mutableListOf<HomeDetailViewState>()
        viewModel.viewState.observeForever {
            viewStates.add(it)
        }
        coEvery { getHomeFromDatabaseUseCase.execute(homeId) } returns Result.OnSuccess(mockk(relaxed = true))
        viewModel.getHome(homeId)
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewStates[0] is HomeDetailViewState.Loading)
        assert(viewStates[1] is HomeDetailViewState.Content)
    }

    @Test
    fun `Test loading state is shown before Error`() {
        val viewStates = mutableListOf<HomeDetailViewState>()
        viewModel.viewState.observeForever {
            viewStates.add(it)
        }
        coEvery { getHomeFromDatabaseUseCase.execute(homeId) } returns Result.OnFailure(mockk())
        viewModel.getHome(homeId)
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewStates[0] is HomeDetailViewState.Loading)
        assert(viewStates[1] is HomeDetailViewState.Error)
    }

    @Test
    fun `WHEN on favorite icon clicked addOrRemoveFromWishListUseCase is called `() = runTest {
        viewModel.onFavoriteIconClicked(homeId)
        dispatcher.scheduler.advanceUntilIdle()
        coVerify { addOrRemoveFromWishListUseCase.execute(homeId) }
    }

}

