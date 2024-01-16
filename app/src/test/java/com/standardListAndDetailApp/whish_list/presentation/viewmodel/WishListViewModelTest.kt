package com.standardListAndDetailApp.whish_list.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.standardListAndDetailApp.shared.business.Result
import com.standardListAndDetailApp.whish_list.business.GetAllWishListUseCase
import com.standardListAndDetailApp.whish_list.presentation.HomeWishListViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.io.IOException

class WishListViewModelTest {

    private lateinit var viewModel: WishListViewModel
    private val getAllWishListUseCase : GetAllWishListUseCase= mockk(relaxed = true)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    private val homeId = 1

    @Before
    fun setup() {
        viewModel = WishListViewModel(
            getAllWishListUseCase,
            dispatcher
        )
    }

    @Test
    fun `Test loading state is shown before the content`() {
        val viewStates = mutableListOf<HomeWishListViewState>()
        viewModel.viewState.observeForever {
            viewStates.add(it)
        }
        coEvery { getAllWishListUseCase.execute() } returns Result.OnSuccess(mockk(relaxed = true))
        viewModel.refreshScreen()
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewStates[0] is HomeWishListViewState.Loading)
        assert(viewStates[1] is HomeWishListViewState.Content)
    }

    @Test
    fun `Test loading state is shown before network Error`() {
        val viewStates = mutableListOf<HomeWishListViewState>()
        viewModel.viewState.observeForever {
            viewStates.add(it)
        }
        coEvery { getAllWishListUseCase.execute() } throws IOException()
        viewModel.refreshScreen()
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewStates[0] is HomeWishListViewState.Loading)
        assert(viewStates[1] is HomeWishListViewState.NetWorkError)
    }

    @Test
    fun `Test loading state is shown before Error`() {
        val viewStates = mutableListOf<HomeWishListViewState>()
        viewModel.viewState.observeForever {
            viewStates.add(it)
        }
        coEvery { getAllWishListUseCase.execute() } returns Result.OnFailure(mockk())
        viewModel.refreshScreen()
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewStates[0] is HomeWishListViewState.Loading)
        assert(viewStates[1] is HomeWishListViewState.Error)
    }


}