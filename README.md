**News App**

This is a fully functional Android application built using Kotlin. The app fetches the latest news articles from the NewsAPI, displays them in a user-friendly manner, and allows users to save articles for offline access. It follows Android best practices and uses a modular architecture.

**Features**

Fetches and displays the latest news articles with headlines, descriptions, and images.

Opens full articles in an in-app WebView.

Allows users to save articles locally for offline access using Room Database.

Provides a saved articles section with the ability to delete saved items.

Clean and modern UI using RecyclerView.

**Tech Stack**

Kotlin: Programming language.

Retrofit: For making API calls.

Glide: For image loading.

Room Database: For local data storage.

Coroutines: For asynchronous operations.

RecyclerView: For displaying a scrollable list of news articles.

ViewModel and LiveData: For managing UI-related data in a lifecycle-conscious way.

**Prerequisites**

Android Studio with proper setup installed on your machine.


**Setup Instructions**

Clone the repository:

git clone https://github.com/your-repo/news-app.git

open the cloned folder in Android Studio

Sync the project with Gradle.

Run the application on an emulator or physical device.

**Code Architecture**

The app follows the MVVM (Model-View-ViewModel) architecture:

Model:

Data classes for API responses.

Room database entities and DAOs.

ViewModel:

Manages UI-related data.

Handles API and database operations via a Repository.

View:

Fragments for UI screens (e.g., SavedFragment, DetailsFragment).

RecyclerView adapters for dynamic lists.

**Key Components**

1. API Integration

Retrofit is used to fetch data from NewsAPI.

API responses are mapped to data classes.

2. Room Database

Articles can be saved locally for offline access.

Provides DAOs for inserting, retrieving, and deleting articles.

3. RecyclerView

Displays news articles in a scrollable list.

Custom adapters (NewsAdapter, SavedAdapter) for dynamic content.

How to Use

Browse News:

The home screen displays the latest news articles fetched from NewsAPI.

View Full Article:

Click on any article to view it in a WebView.

Save Articles:

Use the save button in the WebView to store articles locally.

Manage Saved Articles:

Open the "Saved" section to view and manage saved articles.

Use the delete button to remove unwanted articles.

**Libraries Used**

Retrofit: REST API client.

Glide: Image loading and caching.

Room: Local database for offline storage.

Coroutines: Asynchronous programming.

**Future Enhancements**

Implement pagination for large datasets.

Add a "Search" feature to find specific articles.

Introduce user preferences such as dark mode.

Optimize offline-first behavior.

**Contributing**

Fork the repository.

Create a new branch for your feature: git checkout -b feature-name.

Commit your changes: git commit -m 'Add new feature'.

Push to your branch: git push origin feature-name.

Create a pull request.

**License**

This project is licensed under the MIT License.

