package com.gianlucaveschi.randomusersapp.domain.interactors

import com.gianlucaveschi.randomusersapp.BaseJunitTest
import com.gianlucaveschi.randomusersapp.domain.model.Id
import com.gianlucaveschi.randomusersapp.domain.model.Name
import com.gianlucaveschi.randomusersapp.domain.model.Picture
import com.gianlucaveschi.randomusersapp.domain.model.User
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
class GetUsersUseCaseTest : BaseJunitTest<GetUsersUseCase>() {

    private val usersRepository: RandomUsersRepository = mockk()

    override fun initSelf() = GetUsersUseCase(
        usersRepository = usersRepository,
    )

    @Test
    fun `WHEN repository returns empty list THEN return error`() = runTest {
        coEvery { usersRepository.getUsers() } returns listOf()

        val result = systemUnderTest()

        assertThat(result)
            .isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `WHEN repository returns data THEN return success`() = runTest {
        val usersDataModel = listOf(
            User(
                email = "example@email.com",
                gender = "Male",
                id = Id(name = "1234567890", value = "1234567890"),
                name = Name(first = "John", last = "Doe", title = "Mr"),
                picture = Picture(
                    large = "https://example.com/large.jpg",
                    medium = "https://example.com/medium.jpg",
                    thumbnail = "https://example.com/thumbnail.jpg"
                )
            )
        )
        coEvery { usersRepository.getUsers() } returns usersDataModel

        val result = systemUnderTest()

        assertThat(result)
            .isInstanceOf(Resource.Success::class.java)
    }
}