package com.gianlucaveschi.randomusersapp.domain.repo

import com.gianlucaveschi.randomusersapp.BaseJunitTest
import com.gianlucaveschi.randomusersapp.data.db.LocalDataSource
import com.gianlucaveschi.randomusersapp.data.db.mapper.mapToEntityList
import com.gianlucaveschi.randomusersapp.data.db.users.UserEntity
import com.gianlucaveschi.randomusersapp.data.remote.RemoteDataSource
import com.gianlucaveschi.randomusersapp.data.remote.users.*
import com.gianlucaveschi.randomusersapp.data.repo.UsersRepositoryImpl
import com.gianlucaveschi.randomusersapp.data.user.IdDataModel
import com.gianlucaveschi.randomusersapp.data.user.NameDataModel
import com.gianlucaveschi.randomusersapp.data.user.PictureDataModel
import com.gianlucaveschi.randomusersapp.data.user.UserDataModel
import com.gianlucaveschi.randomusersapp.domain.model.*
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

    private val remoteDataSource: RemoteDataSource = mockk()
    private val localDataSource: LocalDataSource = mockk()

    override fun initSelf() = UsersRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )

    @Test
    fun `GIVEN a successful database hit THEN return successful response`() = runTest {
        coEvery {
            localDataSource.getDataFromCache()
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

        val result: List<UserDataModel> = systemUnderTest.getUsers()

        coVerify(exactly = 0) {
            remoteDataSource.getUsers(
                page = 1,
                seed = "abc",
                results = 20
            )
        }
        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(mockedUsersList);
    }

    @Test
    fun `GIVEN an empty database hit THEN fetch data from api`() = runTest {
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
        coEvery { localDataSource.getAllUsers() } returns listOf()
        coEvery {
            remoteDataSource.getUsers(
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
                results = usersApiModel
            )
        )
        coEvery {
            localDataSource.insertUsers(mockedUsersList.mapToEntityList())
        } returns longArrayOf()


        val result: List<UserDataModel> = systemUnderTest.getUsers()

        coVerify(exactly = 1) {
            remoteDataSource.getUsers(
                page = 1,
                seed = "abc",
                results = 20
            )
        }
        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(mockedUsersList);
    }

    @Test
    fun `GIVEN an empty database hit AND an unsuccessful api call THEN return empty list`() =
        runTest {
            coEvery { localDataSource.getAllUsers() } returns listOf()
            coEvery {
                remoteDataSource.getUsers(
                    page = 1,
                    seed = "abc",
                    results = 20
                )
            } returns Response.error(
                400,
                "{\"key\":[\"somestuff\"]}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )

            val result: List<UserDataModel> = systemUnderTest.getUsers()

            assertThat(result)
                .isEqualTo(listOf<List<UserDataModel>>())
        }

    private companion object {
        val mockedUsersList = listOf(
            UserDataModel(
                email = "example@email.com",
                gender = "Male",
                id = IdDataModel(name = "1234567890", value = "1234567890"),
                name = NameDataModel(first = "John", last = "Doe", title = "Mr"),
                picture = PictureDataModel(
                    large = "https://example.com/large.jpg",
                    medium = "https://example.com/medium.jpg",
                    thumbnail = "https://example.com/thumbnail.jpg"
                )
            )
        )
    }
}