package com.example.datalayer.net;

import com.example.datalayer.entity.PhotosEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    //GET RECENT SERVICE

    @GET("/services/rest/")
    Observable<PhotosEntity> listImages(@Query(("method")) String method, @Query(("api_key")) String api_key, @Query("page") int page, @Query(("format")) String format, @Query("nojsoncallback") int njcb);

   //TODO : Add the search Service

    /*
    * public interface RecipeService {
    @GET("{recipeId}")
        //=cuisine^cuisine-NAME OF THE CUISINE
    Call<RecipeAfterLoad> listRecipes(@Path("recipeId") String recipeId,@Query("_app_id") String app_id, @Query("_app_key") String app_key);
}*/
}
