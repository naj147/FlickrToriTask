package com.example.datalayer.cache;

import com.example.datalayer.entity.PhotosEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;
import io.realm.Realm;

public class PhotosCachesImpl implements PhotosCache {
    private static final long EXP_TIME = 8 * 10 * 1000; //8m
    @Override
    public boolean isExpired() {
        Realm realm = Realm.getDefaultInstance();
        if (realm.where(PhotosEntity.class).count()!=0){
            Date currentTime = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
            Date lastUpdated ;
            try {
                lastUpdated = sdf.parse(realm.where(PhotosEntity.class).findFirst().getLastUpdated());
                boolean isExpired = currentTime.getTime() - lastUpdated.getTime() > EXP_TIME;
                if(isExpired){
                    realm.beginTransaction();
                    realm.delete(PhotosEntity.class);
                    realm.commitTransaction();
                }
                return isExpired;
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                realm.close();
            }
        }
        return false;
    }
    @Override
    public boolean isCached() {
        return getPhotosEntity()!= null ;
    }

    @Override
    public Observable<PhotosEntity> get() {
        PhotosEntity photosEntity = getPhotosEntity();
        return Observable.just(photosEntity);
    }

    @Override
    public void put(PhotosEntity photosEntity) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(photosEntity);
        realm.commitTransaction();
        realm.close();

    }
    private PhotosEntity getPhotosEntity(){
        Realm realm = Realm.getDefaultInstance();
        PhotosEntity photosEntity = realm.copyFromRealm(realm.where(PhotosEntity.class).findFirst());
        realm.close();
        return photosEntity;
    }
}
