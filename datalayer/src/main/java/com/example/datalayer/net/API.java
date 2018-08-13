package com.example.datalayer.net;

import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * RestApi Interface for retrieving data from the network.
 */
public interface API {


    //API SPECIFIC CONSTANTS

    String METHOD1 = "flickr.photos.getRecent";
    String METHOD2 = "flickr.photos.search";
    String METHOD3 = "flickr.people.getInfo";
    String api_key= "b59eaa142fbb03d0ba6c93882fd62e30";
    int page=1;
    int njcb = 1;
    String format = "json";

    /**
     * A method to retrieve Recent Images as {@link Observable<PhotosEntity>} of {@link PhotosEntity} from Flickr API
     */

    @GET("/services/rest/")
    Observable<PhotosEntity> listImages(@Query(("method")) String method, @Query(("api_key")) String api_key, @Query("page") int page, @Query(("format")) String format, @Query("nojsoncallback") int njcb);

    /**
     * A method to retrieve Searched Images as {@link Observable} of {@link PhotosEntity} from Flickr API
     */

    @GET("/services/rest/")
    Observable<PhotosEntity> searchForImages(@Query(("method")) String method, @Query(("api_key")) String api_key, @Query("tags") String tags, @Query("text") String text, @Query("page") int page, @Query(("format")) String format, @Query("nojsoncallback") int njcb);

    /**
     * A method to retrieve a user's profile as {@link Observable} of {@link UserEntity}  from Flickr API
     */
    @GET("/services/rest/")
    Observable<UserEntity> getUser(@Query(("method")) String method, @Query(("api_key")) String api_key, @Query("user_id") String userID, @Query("page") int page, @Query(("format")) String format, @Query("nojsoncallback") int njcb);

}
