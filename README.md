# AndroidClient-YelpAPI

This project is an android client that communicates with Yelp's fusion API to fetch a list of nearby places - searched by a keyword. Additionally, It also provides details on the selected places such as its address, a photo and a review. Apart from yelp, it also utilizes shared preferences to keep track of your favourited places.

![List Screen](/Assets/Images/list_screen.png "List Screen") ![Details Screen](/Assets/Images/details_screen.png "Details Screen")

It follows a MVVM pattern along with a repository. The following is the description of the packages and classes:
1. Views Package - Contains all the fragments.
2. ViewModels package - Contains all the ViewModels. For the current features and funtionalities, there is only 1 viewmodel shared between both the fragments.
3. Tools package - Any class additional classes required that does not deal with data or network calls.
  1. RetaurantsAdapter - List adapter for recycler on the list screen.
  2. SavedLocation - Singleton that keeps the last saved location from google play. Needs the permission ACCESS_COARSE_LOCATION
4. Service package - Has packages and classes related to read and write of persistent data. It communicates with network for the yelp api and shared preferences to keep track of user's favourite places.
  1. dtos package - Data transfer objects for fetch raw data from yelp api.
  2. models package - Models to be used within the rest of the project. They have a get companion method which converts the respective dto to the model.
  3. network package - Containts the Rest api client to communicate with yelp over network. It's implemented with retrofit.
  4. SharedPreferenceManager - Singleton responsible to handle communication with SharedPreferences. It stores the place id, name and address as a String to Strong map of id and json of the object. This ensures that atleast while viewing all the favourites, no network calls are made. Upon viewing details however, most of the information is updated. We are assuming id, name and address of a place never changes since if they change address, I would expect yelp to make it a new entry with a new id.
  5. RestaurantsRepository - Repository class which acts as a single source of truth for the rest of the app. It combines the use of SharedPreferenceManager and the retrofit client at one place so that the rest of the app doesn't have to care about where or how the data comes and goes.
  
Currently it has 2 UI Automation tests and 3 unit tests. For setup, since I wasn't testing the repository yet, I have made a mocked copy of the RestaurantRepository class within a new sharedTest Source Set - shared between androidTest and test source sets.

Components used: Navigation component, Data Binding, Live Data, Retrofit, Glide, Recycler Views, Espresso, Kotlin Coroutines, gson, play-services-location and mockito.
