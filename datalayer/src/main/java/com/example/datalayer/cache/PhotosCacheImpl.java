package com.example.datalayer.cache;

import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;

@Singleton
public class PhotosCacheImpl implements PhotosCache {
    @Inject
    public PhotosCacheImpl() {
    }
    private static final long EXP_TIME =  10 * 1000; //1m

//    private String modifyDateLayout(String inputDate) throws ParseException{
//        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z",Locale.ENGLISH).parse(inputDate);
//        return  new SimpleDateFormat("dd.MM.yyyy HH:mm:ss",Locale.ENGLISH).format(date);
//    }
    @Override
    public boolean isExpired() {
        Realm realm = Realm.getDefaultInstance();
        if (realm.where(PhotosEntity.class).count() != 0) {
            Long currentTime = System.currentTimeMillis();
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            Long lastUpdated;
            try {
                PhotosEntity photosEntity = realm.where(PhotosEntity.class).findFirst();
                if(photosEntity!=null){
                    lastUpdated = Long.parseLong(photosEntity.getLastUpdated());
                    boolean isExpired = currentTime - lastUpdated > EXP_TIME;
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
    public boolean isCached(UserEntity userEntity) {
        return getUserEntity(userEntity) != null;
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
    public Observable<UserEntity> getUser(String userID) {
        UserEntity userEntity =  getUserEntity(new UserEntity(userID));
        return  Observable.just(userEntity);
    }

    @Override
    public void put(UserEntity userEntity) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(userEntity);
        realm.commitTransaction();
        realm.close();
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

    private UserEntity getUserEntity(UserEntity givenUserEntity) {
        UserEntity userEntity = new UserEntity();
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            RealmQuery<UserEntity> query = realm.where(UserEntity.class).equalTo("id", givenUserEntity.getId());
            UserEntity first= query.findFirst();
            if (first == null) {
                realm.cancelTransaction();
                return null;
            }
            userEntity = first;


        } catch (Throwable e) {
            if (realm != null && realm.isInTransaction())
                realm.cancelTransaction();

        } finally {
            if (realm != null) {
                realm.close();
            }

        }
        return userEntity;
    }


}
