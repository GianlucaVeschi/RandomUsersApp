package com.gianlucaveschi.randomusersapp.data.db

import com.gianlucaveschi.randomusersapp.BaseJunitTest
import com.gianlucaveschi.randomusersapp.data.db.users.UserEntity
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

@ExperimentalCoroutinesApi
class LocalDataSourceTest : BaseJunitTest<LocalDataSource>() {

    private val dao: UsersDao = mockk()

    override fun initSelf(): LocalDataSource = LocalDataSource(dao)

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

        val result = systemUnderTest.getDataFromCache()

        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(mockedUsersList);
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
    }
}