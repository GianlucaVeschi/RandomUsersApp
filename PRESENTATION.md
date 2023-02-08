# Introduction 📓
Hi Dear code Reviewer, 
this file will guide you through my mental workflow while developing this app.
First of all, I decided to focus on the tasks with which I am most familiar, which are fetching data from an API and saving data in a database. Therefore I decided to leave pagination out.

As Always, I first created a "Workflow". Usually I use Notion, but this time I simply wrote down all the steps in this markdown document. My Commit strategy loosely follows my workflow.

I mostly pushed my commits directly into the `:feature` branch, but for the database creation I created a new branch called `:implement_database`. If I had more time I would have done the same for pagination.

I didn't feel stressed during the coding challenge, I had plenty of time to dig in into old projects and medium articles. Furthermore, I am going through a lot of coding challenges and I am taking each and one of them as a learning opportunity, so I am definitely looking forward to your feedback!

# Workflow ⏳
- Read Instructions :heavy_check_mark:
- Read API documentation :heavy_check_mark:
- Add Gradle dependencies :heavy_check_mark:

## Fetch Data from the API :heavy_check_mark:
- Create API model :heavy_check_mark:
- Create API service to get 10 users only :heavy_check_mark:
- Create Repo, ViewModel and fetch raw data :heavy_check_mark:
- Create parameterized service :heavy_check_mark:
- Create Domain Model & Mapper :heavy_check_mark:
- Create UI Model :heavy_check_mark:
- Display UI Model in the presentation layer :heavy_check_mark:
- Create a decent UI :heavy_check_mark:

## Implement Room :heavy_check_mark:
- Include gradle dependencies :heavy_check_mark:
- Create Entities :heavy_check_mark:
- Create DAO & Database :heavy_check_mark:
- Create business logic to save and retrieve data locally :heavy_check_mark:

![Database Access Strategy](https://user-images.githubusercontent.com/19254758/217319552-3a9bb508-6d0b-410e-afa7-8b02fc059606.png)


## Implement Pagination (ToDo 👷)
- Define strategy 

## Add tests 🧪
- Test Repository
- Test UseCases
- Test ViewModel

# Tech Stack & Third party libraries 👨‍💻
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

# Architecture 🏗️
I decided to use MVVM because it's the architecture with which I am mostly familiar, furthermore is it also one the most common architectures in the Android World.

![architecture](https://user-images.githubusercontent.com/19254758/217590884-9c642786-bde7-4ee9-b082-375ff1bc507b.png)


# Logs 🖥️
The codebase is intentionally dissamenated with logs, so that it's possible to see which methods are called.

## Scenario 1: App is freshly installed, caches are empty, data is fetched from the network.
![image](https://user-images.githubusercontent.com/19254758/217559446-1e1ba7ea-57ce-4209-ad92-af8403b390b4.png)

## Scenario 2: Data is cached in the app, no network call is needed.
![image](https://user-images.githubusercontent.com/19254758/217559734-773061cf-6db7-47cb-b7ff-90f8c544e137.png)

## Scenario 3: Data is not cached in the app, internet not available.
![image](https://user-images.githubusercontent.com/19254758/217559971-3dedc0b3-c243-41a9-9cec-88b9dbedac42.png)
