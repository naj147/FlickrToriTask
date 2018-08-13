package com.example.datalayer.repository.datasource;

import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface PhotosDataStore {

    /**
     * Get an {@link Observable} which will emit a List of {@link PhotosEntity}.
     *
     * @param method which method to use for the Flickr Api (GetRecent, SearchForImage or GetUserDetails)
     * @param param1 Usually Represents text or tags used to retrieve an image
     * @param param2 Usually Represents the page to be retrieved
     **/
    Observable<PhotosEntity> photos(int method, String param1, int param2);

    /**
     * Get an {@link Observable} which will emit a List of {@link UserEntity}.
     *
     * @param method which method to use for the Flickr Api (GetUserDetails)
     * @param param1 Usually Represents UserID
     */
    Observable<UserEntity> userDetails(int method, String param1);

}
