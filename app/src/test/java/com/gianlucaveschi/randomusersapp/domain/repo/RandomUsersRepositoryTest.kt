package com.gianlucaveschi.randomusersapp.domain.repo

import com.gianlucaveschi.randomusersapp.BaseJunitTest
import com.gianlucaveschi.randomusersapp.data.db.LocalDataSource
import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToEntityList
import com.gianlucaveschi.randomusersapp.data.remote.RemoteDataSource
import com.gianlucaveschi.randomusersapp.data.remote.users.*
import com.gianlucaveschi.randomusersapp.data.repo.UsersRepositoryImpl
import com.gianlucaveschi.randomusersapp.domain.model.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
class RandomUsersRepositoryTest : BaseJunitTest<RandomUsersRepository>() {

    private val remoteDataSource: RemoteDataSource = mockk()
    private val localDataSource: LocalDataSource = mockk(relaxed = true)

    override fun initSelf() = UsersRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )

    @Test
    fun `GIVEN a successful database hit THEN return data`() = runTest {
        coEvery {
            localDataSource.saveDataLocally(mockedUsersList.mapToEntityList())
        }
        coEvery { localDataSource.getDataFromCache() } returns listOf(
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

        val result: List<User> = systemUnderTest.getUsers()

        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(mockedUsersList);
    }

    @Test
    fun `GIVEN an empty database hit THEN fetch data from api`() = runTest {
        coEvery { localDataSource.getDataFromCache() } returns listOf()
        coEvery {
            remoteDataSource.getDataFromNetwork()
        } returns listOf(
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
        coEvery {
            localDataSource.saveDataLocally(mockedUsersList.mapToEntityList())
        }
        coEvery { localDataSource.getDataFromCache() } returns listOf(
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

        val result: List<User> = systemUnderTest.getUsers()

        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(mockedUsersList);
    }

    @Test
    fun `GIVEN an empty database hit AND an unsuccessful api call THEN return empty list`() =
        runTest {
            coEvery {
                localDataSource.getDataFromCache()
            } returns listOf()
            coEvery {
                remoteDataSource.getDataFromNetwork()
            } returns listOf()

            val result: List<User> = systemUnderTest.getUsers()

            assertThat(result)
                .isEqualTo(listOf<List<User>>())
        }

    private companion object {
        val mockedUsersList = listOf(
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

        val usersApiModel = listOf(
            UserApiModel(
                cell = "123456789",
                dob = DateOfBirthApiModel(date = "2000-01-01T00:00:00.000Z", age = 23),
                email = "example@email.com",
                gender = "Male",
                id = IdApiModel(
                    name = "1234567890",
                    value = "1234567890"
                ),
                location = LocationApiModel(
                    street = StreetApiModel(
                        name = "",
                        number = 0
                    ),
                    city = "",
                    state = "",
                    postcode = "",
                    coordinates = CoordinatesApiModel(
                        latitude = "latitude",
                        longitude = "longitude"
                    ),
                    country = "",
                    timezone = TimezoneApiModel(
                        description = "description",
                        offset = "offset"
                    ),
                ),
                login = null,
                name = NameApiModel(
                    title = "Mr",
                    first = "John",
                    last = "Doe"
                ),
                nat = "US",
                phone = "1234567890",
                picture = PictureApiModel(
                    large = "https://example.com/large.jpg",
                    medium = "https://example.com/medium.jpg",
                    thumbnail = "https://example.com/thumbnail.jpg"
                ),
                registered = RegisteredApiModel(date = "2000-01-01T00:00:00.000Z", age = 23)
            )
        )
    }
}