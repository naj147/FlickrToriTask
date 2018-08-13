package com.example.datalayer.cache;

import com.example.datalayer.entity.PhotosEntity;
import com.example.datalayer.entity.UserEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * {@link Http3Cache} Class implementation for the second Cache (Local Data Store)
 */
@Singleton
public class PhotosCacheImpl implements PhotosCache {
    @Inject
    public PhotosCacheImpl() {
    }

    /**
     * The time it takes for the Local Data to be considered expired
     */
    private static final long EXP_TIME =  10 * 1000; //1m

    /**
     * A method that verifies that the local data is expired and frees the space in that case
     */
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
                } else {
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

    /**
     * A method to verify that a {@link UserEntity} is cached
     */
    @Override
    public boolean isCached(UserEntity userEntity) {
        return getUserEntity(userEntity) != null;
    }

    /**
     * A method to verify that a {@link PhotosEntity} is cached
     */
    @Override
    public boolean isCached() {
        return getPhotosEntity() != null;
    }

    /**
     * A method to retrieve a {@link PhotosEntity} as an observable
     *
     * @return {@link Observable<PhotosEntity>}
     */
    @Override
    public Observable<PhotosEntity> get() {
        PhotosEntity photosEntity = getPhotosEntity();
        return Observable.just(photosEntity);
    }

    /**
     * A method to retrieve a {@link UserEntity} as an observable using a UserID
     *
     * @param userID Unique string used to retrieve the User
     * @return {@link Observable<UserEntity>}
     */
    @Override
    public Observable<UserEntity> getUser(String userID) {
        UserEntity userEntity =  getUserEntity(new UserEntity(userID));
        return  Observable.just(userEntity);
    }

    /**
     * A method to register a {@link UserEntity} in the local Data Store (the Real.io Database)
     *
     * @param userEntity UserEntity to be stored
     */

    @Override
    public void put(UserEntity userEntity) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(userEntity);
        realm.commitTransaction();
        realm.close();
    }

    /**
     * A method to register a {@link PhotosEntity} in the local Data Store (the Real.io Database)
     *
     * @param photosEntity PhotosEntity to be stored
     */
    @Override
    public void put(PhotosEntity photosEntity) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(photosEntity);
        realm.commitTransaction();
        realm.close();
    }

    /**
     * A method to retrieve a {@link PhotosEntity}
     *
     * @return {@link PhotosEntity} to be retrieved from the local DB
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

    /**
     * A method to retrieve a {@link UserEntity}
     * @param givenUserEntity userEntity with UsedID to be retrieved
     *@return {@link UserEntity} From Local DB
     * */
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
