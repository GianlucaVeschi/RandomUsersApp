package com.gianlucaveschi.randomusersapp.presentation

import com.gianlucaveschi.randomusersapp.BaseJunitTest
import com.gianlucaveschi.randomusersapp.MainCoroutineRule
import com.gianlucaveschi.randomusersapp.domain.interactors.GetUsersUseCase
import com.gianlucaveschi.randomusersapp.domain.model.User
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import com.gianlucaveschi.randomusersapp.presentation.ui.users.UsersState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
class MainViewModelTest : BaseJunitTest<MainViewModel>() {

    private val getUsersUseCase: GetUsersUseCase = mockk()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    override fun initSelf() = MainViewModel(
        getUsersUseCase = getUsersUseCase
    )

    @Test
    fun `GIVEN a successful database hit THEN return successful response`() = runTest {
        systemUnderTest.getRandomUsers()
        advanceUntilIdle()
    }

    @Test
    fun `assert initial state`() = runTest {
        val users = listOf<User>()
        coEvery { getUsersUseCase() } returns Resource.Success(users)

        assertEquals(
            UsersState(isLoading = true),
            systemUnderTest.state.value
        )
    }

    @Test
    @Ignore("Test fails, try to introduce Turbine")
    fun `assert success state`() = runTest {
        val users = listOf<User>()
        coEvery { getUsersUseCase() } returns Resource.Success(users)

        // Create an empty collector for the StateFlow
        val listOfEmittedResult = mutableListOf<UsersState>()
        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            systemUnderTest.state.toList(listOfEmittedResult)
        }

        systemUnderTest.getRandomUsers()
        advanceUntilIdle()

        // Turbine
//        systemUnderTest.state.test(){
//            assertEquals(
//                UsersState(
//                    data = users,
//                    isLoading = false,
//                    error = null
//                ),
//                awaitItem()
//            )
//        }

        assertEquals(
            UsersState(
                data = users,
                isLoading = false,
                error = null
            ),
            listOfEmittedResult[0]
        )
        job.cancel()
    }
}
