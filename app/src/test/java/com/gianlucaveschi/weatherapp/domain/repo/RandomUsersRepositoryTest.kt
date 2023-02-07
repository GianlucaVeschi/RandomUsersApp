package com.gianlucaveschi.weatherapp.domain.repo

import com.gianlucaveschi.randomusersapp.data.db.UsersDao
import com.gianlucaveschi.randomusersapp.data.db.users.UserEntity
import com.gianlucaveschi.randomusersapp.data.remote.RandomUsersService
import com.gianlucaveschi.randomusersapp.data.repo.DatabaseUsersRepositoryImpl
import com.gianlucaveschi.randomusersapp.domain.model.*
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import com.gianlucaveschi.weatherapp.BaseJunitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
class RandomUsersRepositoryTest : BaseJunitTest<RandomUsersRepository>() {

    private val api: RandomUsersService = mockk()
    private val dao: UsersDao = mockk()

    override fun initSelf() = DatabaseUsersRepositoryImpl(
        api = api,
        dao = dao
    )

    @Test
    fun `Given a successful database hit THEN return successful response`() = runTest {
        coEvery {
            dao.getAllUsers()
        } returns listOf(
            UserEntity(
                id = "1234567890",
                idValue = "1234567890",
                lastName = "Doe",
                firstName = "John",
                titleName = "Mr",
                largePicture = "https://example.com/large.jpg",
                mediumPicture = "https://example.com/medium.jpg",
                thumbnailPicture = "https://example.com/thumbnail.jpg",
                email = "example@email.com",
                gender = "Male"
            )
        )

        val result: Resource<Users> = systemUnderTest.getUsers()

        coVerify(exactly = 0) {
            api.getUsers(
                page = 1,
                seed = "abc",
                results = 20
            )
        }
        assertThat(result.data)
            .usingRecursiveComparison()
            .isEqualTo(mockedUsersList);
    }

    private companion object {
        val mockedUsersList = Users(
            users = listOf(
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
        )
    }
}