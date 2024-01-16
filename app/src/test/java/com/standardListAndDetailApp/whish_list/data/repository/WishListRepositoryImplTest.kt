package com.standardListAndDetailApp.whish_list.data.repository

import android.util.Log
import com.standardListAndDetailApp.shared.business.Result
import com.standardListAndDetailApp.shared.data.repository.database.HomeEntity
import com.standardListAndDetailApp.shared.data.repository.database.HomesDatabase
import com.standardListAndDetailApp.whish_list.data.repository.database.WishListEntity
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WishListRepositoryImplTest {

    private lateinit var repository: WishListRepositoryImpl
    private val homesDatabase: HomesDatabase = mockk(relaxed = true)
    private val homeId = 1

    @Before
    fun setup() {
        repository = WishListRepositoryImpl(homesDatabase)
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
    }

    @Test
    fun `When given home is saved in Database, then repository returns true`() = runTest {
        coEvery {
            homesDatabase.homesDao.isHomeInWishList(homeId)
        } returns WishListEntity(homeId)
        assert(repository.isInWishList(homeId))
    }


    @Test
    fun `When given home is not saved in Database, then repository returns false`() = runTest {
        coEvery {
            homesDatabase.homesDao.isHomeInWishList(homeId)
        } returns null
        assert(!repository.isInWishList(homeId))
    }

    @Test
    fun `When given multiple homes are saved in Database, then repository returns all`() = runTest {
        val wishlist = (0..4).map {
            HomeEntity(
                bedrooms = it,
                area = it.toLong(),
                city = "city$it",
                id = it,
                url = "url$it",
                price = it.toLong(),
                professional = "pro$it",
                homeType = "homeType$it",
                offerType = it,
                rooms = it
            )
        }

        coEvery {
            homesDatabase.homesDao.getWishList()
        } returns flow { emit(wishlist) }

        when (val result = repository.getAllWishList()) {
            is Result.OnSuccess -> {
                result.data.collect { list ->
                    assert(list.size == wishlist.size)
                    wishlist.map { t -> t.id }
                        .forEach { wishListId ->
                            list.map { t -> t.id }.contains(wishListId)
                        }
                    wishlist.map { t -> t.city }
                        .forEach { wishListCity ->
                            list.map { t -> t.city }.contains(wishListCity)
                        }

                    wishlist.map { t -> t.url }
                        .forEach { wishListUrl ->
                            list.map { t -> t.url }.contains(wishListUrl)
                        }

                    assert(!list.map { t -> t.id }.contains(5))

                }
            }

            else -> {
                throw Exception("Error with result type")
            }
        }

    }


}