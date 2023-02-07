# Introduction
Hi Dear code Reviewer, 
this file will guide you through my mental workflow while developing this app.

# Tech Stack & Third party libraries
- Kotlin :heart:
- Jetpack Compose :heart:
- Coroutines
- Flow
- Dagger Hilt
- Coil
- OkHttp
- Timber
- Mockk
- Junit4

# Workflow
- Read Instructions :heavy_check_mark:
- Read API documentation :heavy_check_mark:
- Add Gradle dependencies :heavy_check_mark:

## Fetch Data from the API
- Create API model :heavy_check_mark:
- Create API service to get 10 users only :heavy_check_mark:
- Create Repo, ViewModel and fetch raw data :heavy_check_mark:
- Create parameterized service :heavy_check_mark:
- Create Domain Model & Mapper :heavy_check_mark:
- Create UI Model :heavy_check_mark:
- Display UI Model in the presentation layer :heavy_check_mark:
- Create a decent UI :heavy_check_mark:

## Implement Room
- Include gradle dependencies :heavy_check_mark:
- Create Entities :heavy_check_mark:
- Create DAO & Database :heavy_check_mark:
- Create business logic to save and retrieve data locally :heavy_check_mark:

![Database Access Strategy](https://user-images.githubusercontent.com/19254758/217319552-3a9bb508-6d0b-410e-afa7-8b02fc059606.png)


## Implement Pagination
- Define strategy

## Add tests
- Test Repository
- Test UseCases
- Test ViewModel

# Personal Notes
- Exclude login from api response because it's an intensive field according to the DOCs.
