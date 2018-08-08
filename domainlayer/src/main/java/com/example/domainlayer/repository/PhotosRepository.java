package com.example.domainlayer.repository;

import com.example.domainlayer.model.Photos;
import com.example.domainlayer.model.User;

import io.reactivex.Observable;


public interface PhotosRepository {
    Observable<Photos> photos(int method,String title, String tags);
    Observable<User> userDetails(int method, String userId);
}
