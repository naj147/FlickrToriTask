package com.example.datalayer.net;

import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {


    //API SPECIFIC CONSTANTS

    String METHOD1 = "flickr.photos.getRecent";
    String METHOD2 = "flickr.photos.search";
    String METHOD3 = "flickr.people.getInfo";
    String api_key= "b59eaa142fbb03d0ba6c93882fd62e30";
    int page=1;
    int njcb = 1;
    String format = "json";

    //GET RECENT SERVICE

    @GET("/services/rest/")
    Observable<PhotosEntity> listImages(@Query(("method")) String method, @Query(("api_key")) String api_key, @Query("page") int page, @Query(("format")) String format, @Query("nojsoncallback") int njcb);

   //TODO : Add the search Service


    @GET("/services/rest/")
    Observable<PhotosEntity> searchForImages(@Query(("method")) String method, @Query(("api_key")) String api_key, @Query("text") String text,@Query("page") int page, @Query(("format")) String format, @Query("nojsoncallback") int njcb);

    @GET("/services/rest/")
    Observable<UserEntity> getUser(@Query(("method")) String method, @Query(("api_key")) String api_key, @Query("user_id") String userID, @Query("page") int page, @Query(("format")) String format, @Query("nojsoncallback") int njcb);
    /*
    * public interface RecipeService {
    @GET("{recipeId}")
        //=cuisine^cuisine-NAME OF THE CUISINE
    Call<RecipeAfterLoad> listRecipes(@Path("recipeId") String recipeId,@Query("_app_id") String app_id, @Query("_app_key") String app_key);
}*/
}
