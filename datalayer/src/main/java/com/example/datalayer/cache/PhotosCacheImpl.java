package com.example.datalayer.cache;

import com.example.datalayer.entity.PhotosEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.realm.Realm;

@Singleton
public class PhotosCacheImpl implements PhotosCache {
    @Inject
    public PhotosCacheImpl() {
    }

    private static final long EXP_TIME =  10 * 1000; //8m

//    private String modifyDateLayout(String inputDate) throws ParseException{
//        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z",Locale.ENGLISH).parse(inputDate);
//        return  new SimpleDateFormat("dd.MM.yyyy HH:mm:ss",Locale.ENGLISH).format(date);
//    }
    @Override
    public boolean isExpired() {
        Realm realm = Realm.getDefaultInstance();
        if (realm.where(PhotosEntity.class).count() != 0) {
            Date currentTime = new Date(System.currentTimeMillis());
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            Date lastUpdated;
            try {
                PhotosEntity photosEntity = realm.where(PhotosEntity.class).findFirst();
                if(photosEntity!=null){
                    lastUpdated = new Date(photosEntity.getLastUpdated());
                    boolean isExpired = currentTime.getTime() - lastUpdated.getTime() > EXP_TIME;
                    if (isExpired) {
                        realm.beginTransaction();
                        realm.delete(PhotosEntity.class);
                        realm.commitTransaction();
                    }
                    return isExpired;
                }else
                {
                    realm.beginTransaction();
                    realm.delete(PhotosEntity.class);
                    realm.commitTransaction();
                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                realm.close();
            }
        }
        return false;
    }

    @Override
    public boolean isCached() {
        return getPhotosEntity() != null;
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

    }/*
try {
     instance = Realm.getDefaultInstance();
     realm.beginTransaction();
     MeasureObject first = instance.where(MeasureObject.class).equalTo("id", "xxxx").findFirst();
     if(first == null) {
         realm.cancelTransaction();
         return;
     }
     ....
     realm.commitTransaction();
     ....
} catch(Throwable e) {
    if(instance != null && instance.isInTransaction()) {
         instance.cancelTransaction();
    }
    throw e;
} finally {
    if(instance != null) {
         instance.close();
    }
    */

    private PhotosEntity getPhotosEntity() {
        PhotosEntity photosEntity = null;
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            PhotosEntity first = realm.where(PhotosEntity.class).findFirst();
            if (first == null) {
                realm.cancelTransaction();
                return null;
            }
            photosEntity = realm.copyFromRealm(first);

        } catch (Throwable e) {
            if (realm != null && realm.isInTransaction())
                realm.cancelTransaction();

        } finally {
            if (realm != null) {
                realm.close();
            }

        }

        return photosEntity;
    }
}
