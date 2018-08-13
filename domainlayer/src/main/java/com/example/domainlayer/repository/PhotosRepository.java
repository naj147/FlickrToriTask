package com.example.domainlayer.repository;

import com.example.domainlayer.model.Photos;
import com.example.domainlayer.model.User;

import io.reactivex.Observable;


/**
 * Interface that represents a Repository for getting {@link User} and {@link Photos} related data.
 */
public interface PhotosRepository {
    /**
     * Get an {@link Observable} which will emit a List of {@link Photos}.
     *
     * @param method which method to use for the Flickr Api (GetRecent or SearchForImage)
     * @param text   Usually Represents text or tags used to retrieve an image
     * @param page   Represents the page to be retrieved
     */
    Observable<Photos> photos(int method, String text, int page);

    /**
     * Get an {@link Observable} which will emit a List of {@link User}.
     *
     * @param method which method to use for the Flickr Api (GetUserDetails)
     * @param userId Represents the user to be retrieved
     */
    Observable<User> userDetails(int method, String userId);
}
