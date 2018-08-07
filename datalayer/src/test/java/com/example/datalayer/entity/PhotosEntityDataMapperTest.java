package com.example.datalayer.entity;


import android.util.Log;

import com.example.datalayer.entity.mapper.PhotosMapper;
import com.example.domainlayer.model.Photo;
import com.example.domainlayer.model.Photos;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import io.realm.RealmList;

import static org.assertj.core.api.Assertions.assertThat;


public class PhotosEntityDataMapperTest {

    private static final String FAKE_PHOTO_ID = "123";
    private static final int FAKE_NBR_PAGES = 55;
    private static final String FAKE_OWNER = "@48484642";
    private Long LASTUPDATED ;

    private PhotosMapper photosMapper;

@Before
public void setUp() throws Exception{
    photosMapper = new PhotosMapper();
}

@Test
public void testTransformPhotosMapper() {
    PhotosEntity photosEntity = createFakesPhotosEntity();
    Photos photos = photosMapper.transform(photosEntity);
    assertThat(photos).isInstanceOf(Photos.class);
    assertThat(photos.getPages()).isEqualTo(FAKE_NBR_PAGES);
    LASTUPDATED = System.currentTimeMillis();
    assertThat(Long.parseLong(photos.getLastUpdated())).isLessThan(LASTUPDATED);
    assertThat(photos.getPhoto().toArray()[0]).isInstanceOf(Photo.class);
    assertThat(photos.getPhoto()).hasSize(1);
    assertThat(photos.getPhoto().get(0).getId()).isEqualTo(FAKE_PHOTO_ID);
    assertThat(photos.getPhoto().get(0).getOwner()).isEqualTo(FAKE_OWNER);

}

public PhotosEntity createFakesPhotosEntity(){
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setId(FAKE_PHOTO_ID);
        photoEntity.setOwner(FAKE_OWNER);
        PhotosEntity photosEntity = new PhotosEntity();
        photosEntity.setPages(FAKE_NBR_PAGES);
        photosEntity.setPhoto(new RealmList<PhotoEntity>(photoEntity));
        return photosEntity;
}
}
