package com.gianlucaveschi.randomusersapp.data.remote

import com.gianlucaveschi.randomusersapp.BaseJunitTest
import com.gianlucaveschi.randomusersapp.data.remote.users.*
import com.gianlucaveschi.randomusersapp.domain.model.Id
import com.gianlucaveschi.randomusersapp.domain.model.Name
import com.gianlucaveschi.randomusersapp.domain.model.Picture
import com.gianlucaveschi.randomusersapp.domain.model.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RemoteDataSourceTest : BaseJunitTest<RemoteDataSource>() {

    private val api: RandomUsersService = mockk()

    override fun initSelf(): RemoteDataSource = RemoteDataSource(api)

    @Test
    fun `GIVEN a successful api call WHEN fetching data THEN return mapped data`() = runTest {
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
                results = usersApiModel
            )
        )

        val result = systemUnderTest.getDataFromNetwork()

        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(mockedUsersList);
    }

    private companion object {
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
    }
}