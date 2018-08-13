package com.example.naj_t.flickrtoritask.models.Mapper;

import com.example.domainlayer.model.Photo;
import com.example.naj_t.flickrtoritask.models.PhotoModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Photo} (in the domain layer) to {@link PhotoModel} in the
 * presentation layer.
 */
public class PhotoModelMapper {
    @Inject
    public PhotoModelMapper() { }

    /**
     * Transform a {@link Photo} into an {@link PhotoModel}.
     *
     * @param photoEntity Object to be transformed.
     * @return {@link PhotoModel}.
     */
    public PhotoModel transform(Photo photoEntity){
        PhotoModel photo = null;

        if(photoEntity!=null){
            //Photo(String id, String owner, String secret, String server, String farm, String title, int isPublic, int isFriend, int isFamily)
            photo = new PhotoModel(photoEntity.getId(),photoEntity.getOwner(),photoEntity.getSecret(),photoEntity.getServer(),photoEntity.getFarm(),photoEntity.getTitle(),photoEntity.getIsPublic(),photoEntity.getIsFriend(),photoEntity.getIsFamily());
        }
        return  photo;
    }

    /**
     * Transform a Collection of {@link PhotoModel} into a Collection of {@link PhotoModel}.
     *
     * @param photoEntities Objects to be transformed.
     * @return List of {@link PhotoModel}.
     */
    public List<PhotoModel> transformList(List<Photo> photoEntities){
        List<PhotoModel> photoList = new ArrayList<>();
        for(Photo photoEntity : photoEntities){
            if(photoEntity!=null){
                photoList.add(transform(photoEntity));
            }
        }
        return photoList ;
    }
}
