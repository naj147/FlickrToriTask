##FlickrToriTask
----------

** greetings, ** 

This is a project of a Flickr Search App using Clean Architecture + Dagger 2 + RxJava (UI and Unit testing done with Mickito, Robolectric, JUnit and Espresso) 

!["App Images"](https://photos.app.goo.gl/QGh3YcvsVaxfwTwWA) 
    
### Environment :
----------
####Hardware And Operating systems
* Android Studio 3.1
* Windows 10 Anniversary Version
* Minimum Android OS Supported 4.0.3 (Jelly_Bean), targeted 
* Android OS Targeted 8.1 (Oreo)
* Native Phone : OnePlus 3

####Libraries

* RetroFit Library Used : 2.4 
* Dagger Library Used : 2.16 
* Gson Library Used : 2.8.5 
* OkHttp3 Library Used : 3.11.0 
* ButterKnife Library Used : 8.8.1
* RxJava Library Used : 8.8.1
* ButterKnife Library Used :  2.16
* Java version 8 Update 25

####Testing Environment

* JUnit Library Used : 4.12
* Espresso Library Used : 3.0.1
* Mockito Library Used : 2.21.0
* Robolectric Library Used : 3.8
* AssertJ Library Used : 3.10.0

####Development Environment

* LeakCanary Library Used : 1.6.1
* Timber Library Used : 4.7.1
* Stetho Library Used : 1.5.0


####Installation  

	Just be sure to respect the environment mentioned above and download the repos on your pc    
    and then import it using Android studio then clean the project and rebuild it.   
    In case you face some errors be sure to invalidate the cache and repeat the process previously mentioned.  
### Architecture Applied 

Clean architecture :

its purpose is the separation of concerns by keeping the business rules not knowing anything at all about the outside world, thus, they can can be tested without any dependency to any external element.

To achieve this, my proposal is about breaking up the project into 3 different layers, in which each one has its own purpose and works separately from the others.

!["Play"](https://raw.githubusercontent.com/bufferapp/android-clean-architecture-boilerplate/master/art/architecture.png) 
    
### Note
    This code includes JavaDoc Convention Comments on all the Classes and it's easy to follow
    
####Video Demo  


	This video showcases all the fonctionalities of the app 

!["Play"](https://i.ytimg.com/vi/CVXp3ZgUIr8/maxresdefault.jpg) 

["Play The Demo"]( https://youtu.be/k4mdyPxnFAQ)
------------------------------
    
    
*####End Note*  

    I hope you guys have liked the application and looking forward for your feedback. 
