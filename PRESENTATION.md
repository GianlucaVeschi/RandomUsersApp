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
- Test Repository :heavy_check_mark:
- Test ViewModel

# Logs
The codebase is intentionally disseminated with logs, so that it's possible to see which methods are called.

## Scenario 1: App is freshly installed, caches are empty, data is fetched from the network.
![image](https://user-images.githubusercontent.com/19254758/217559446-1e1ba7ea-57ce-4209-ad92-af8403b390b4.png)

## Scenario 2: Data is cached in the app, no network call is needed.
![image](https://user-images.githubusercontent.com/19254758/217559734-773061cf-6db7-47cb-b7ff-90f8c544e137.png)

## Scenario 3: Data is not cached in the app, internet not available.
![image](https://user-images.githubusercontent.com/19254758/217559971-3dedc0b3-c243-41a9-9cec-88b9dbedac42.png)

# Personal Notes
- Exclude login from api response because it's an intensive field according to the DOCs.
