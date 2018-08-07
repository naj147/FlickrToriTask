package com.example.domainlayer.repository;

import com.example.domainlayer.model.Photos;

import io.reactivex.Observable;


public interface PhotosRepository {
    Observable<Photos> photos(int method,String title, String tags);
}
