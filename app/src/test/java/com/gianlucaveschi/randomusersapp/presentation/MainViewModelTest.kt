package com.gianlucaveschi.randomusersapp.presentation

import com.gianlucaveschi.randomusersapp.BaseJunitTest
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
class MainViewModelTest : BaseJunitTest<MainViewModel>() {

    private val usersRepository: RandomUsersRepository = mockk()

    override fun initSelf() = MainViewModel(
        usersRepository = usersRepository
    )

    @Test
    fun `GIVEN a successful database hit THEN return successful response`() = runTest {

    }
}
