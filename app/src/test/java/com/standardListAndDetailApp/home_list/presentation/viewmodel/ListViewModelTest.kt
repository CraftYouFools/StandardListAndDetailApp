package com.standardListAndDetailApp.home_list.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.standardListAndDetailApp.home_list.business.GetAllHomesFromDatabaseUseCase
import com.standardListAndDetailApp.home_list.business.RefreshHomeListUseCase
import com.standardListAndDetailApp.home_list.presentation.HomeListViewState
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
import java.io.IOException

class ListViewModelTest {


    private lateinit var viewModel: ListViewModel
    private val refreshHomeListUseCase : RefreshHomeListUseCase = mockk(relaxed = true)
    private val getAllHomesFromDatabaseUseCase: GetAllHomesFromDatabaseUseCase= mockk(relaxed = true)
    private val isHomeInWishListUseCase: IsHomeInWishListUseCase= mockk(relaxed = true)
    private val addOrRemoveFromWishListUseCase: AddOrRemoveFromWishListUseCase= mockk(relaxed = true)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    private val homeId = 1

    @Before
    fun setup() {
        viewModel = ListViewModel(
            refreshHomeListUseCase,
            getAllHomesFromDatabaseUseCase,
            isHomeInWishListUseCase,
            addOrRemoveFromWishListUseCase,
            dispatcher
        )
    }

    @Test
    fun `Test loading state is shown before the content`() {
        val viewStates = mutableListOf<HomeListViewState>()
        viewModel.viewState.observeForever {
            viewStates.add(it)
        }
        coEvery { getAllHomesFromDatabaseUseCase.execute() } returns Result.OnSuccess(mockk())
        viewModel.onRefresh()
        dispatcher.scheduler.advanceUntilIdle()
        coVerify { refreshHomeListUseCase.execute() }
        assert(viewStates[0] is HomeListViewState.Loading)
        assert(viewStates[1] is HomeListViewState.Content)
    }

    @Test
    fun `Test loading state is shown before network Error`() {
        val viewStates = mutableListOf<HomeListViewState>()
        viewModel.viewState.observeForever {
            viewStates.add(it)
        }
        coEvery { getAllHomesFromDatabaseUseCase.execute() } throws IOException()
        viewModel.onRefresh()
        dispatcher.scheduler.advanceUntilIdle()
        coVerify { refreshHomeListUseCase.execute() }
        assert(viewStates[0] is HomeListViewState.Loading)
        assert(viewStates[1] is HomeListViewState.NetWorkError)
    }

    @Test
    fun `Test loading state is shown before Error`() {
        val viewStates = mutableListOf<HomeListViewState>()
        viewModel.viewState.observeForever {
            viewStates.add(it)
        }
        coEvery { getAllHomesFromDatabaseUseCase.execute() } returns Result.OnFailure(mockk())
        viewModel.onRefresh()
        dispatcher.scheduler.advanceUntilIdle()
        coVerify { refreshHomeListUseCase.execute() }
        assert(viewStates[0] is HomeListViewState.Loading)
        assert(viewStates[1] is HomeListViewState.Error)
    }

    @Test
    fun `WHEN on favorite icon clicked addOrRemoveFromWishListUseCase is called `() = runTest {
        viewModel.onFavoriteIconClicked(homeId)
        dispatcher.scheduler.advanceUntilIdle()
        coVerify { addOrRemoveFromWishListUseCase.execute(homeId) }
    }


}