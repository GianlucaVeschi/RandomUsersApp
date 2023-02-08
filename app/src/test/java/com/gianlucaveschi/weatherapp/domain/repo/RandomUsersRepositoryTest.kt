package com.gianlucaveschi.weatherapp.domain.repo

import com.gianlucaveschi.randomusersapp.data.db.UsersDao
import com.gianlucaveschi.randomusersapp.data.db.users.UserEntity
import com.gianlucaveschi.randomusersapp.data.remote.RandomUsersService
import com.gianlucaveschi.randomusersapp.data.remote.users.*
import com.gianlucaveschi.randomusersapp.data.repo.UsersRepositoryImpl
import com.gianlucaveschi.randomusersapp.domain.model.*
import com.gianlucaveschi.randomusersapp.domain.repo.RandomUsersRepository
import com.gianlucaveschi.randomusersapp.domain.util.Resource
import com.gianlucaveschi.weatherapp.BaseJunitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import retrofit2.Response


@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
class RandomUsersRepositoryTest : BaseJunitTest<RandomUsersRepository>() {

    private val api: RandomUsersService = mockk()
    private val dao: UsersDao = mockk()

    override fun initSelf() = UsersRepositoryImpl(
        api = api,
        dao = dao
    )

    @Test
    fun `GIVEN a successful database hit THEN return successful response`() = runTest {
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

    @Test
    fun `GIVEN an empty database hit THEN fetch data from api`() = runTest {
        coEvery { dao.getAllUsers() } returns listOf()
        coEvery {
            api.getUsers(
                page = 1,
                seed = "abc",
                results = 20
            )
        } returns Response.success(
            UsersApiResponse(
                info = ApiResponseInfoApiModel(
                    page = 1,
                    results = 10,
                    seed = "abc",
                    version = "1.0"
                ),
                results = listOf(
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
            )
        )


        val result: Resource<Users> = systemUnderTest.getUsers()

        coVerify(exactly = 1) {
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

    @Test
    fun `GIVEN an empty database hit AND an unsuccessful api call THEN return error`() = runTest {
        coEvery { dao.getAllUsers() } returns listOf()
        coEvery {
            api.getUsers(
                page = 1,
                seed = "abc",
                results = 20
            )
        } returns Response.error(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )

        val result: Resource<Users> = systemUnderTest.getUsers()

        assertThat(result)
            .isInstanceOf(Resource.Error::class.java)
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