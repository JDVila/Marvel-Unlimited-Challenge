# Marvel Unlimited App Challenge

This app was constructed to the specifications detailed in the challenge sent via email. This app: 

* Is a Single-Screen app, implementing a new Comic Detail Screen
* Contains both Kotlin and Java language code
* Parses a JSON response from Marvel's Developer API using Retrofit2
* Implements both Unit and UI Tests
* Persists data to file via Shared Preferences Asynchronously on a Background Thread
* Displays JSON Data within an ImageView (using Picasso) and TextViews, inflated within a Fragment hosted by a Single Activity
* Is hosted in a Git Repository on Github

### Setting Up:

In the folder where this project folder will eventually reside (i.e.: immediately outside of the root project folder), create a gradle file called `keys.gradle`:

```gradle
ext {
    $PUBLIC_KEY = "<<YOUR_PUBLIC_API_KEY>>"
    $PRIVATE_KEY = "<<YOUR_PRIVATE_API_KEY>>"
}
```

Once created - sync gradle files, then build/rebuild as needed.