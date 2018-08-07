package com.example.datalayer.entity;

import com.example.datalayer.entity.mapper.PhotoMapper;
import com.example.domainlayer.model.Photo;
import com.google.common.base.Verify;

import org.assertj.core.api.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.realm.RealmList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PhotoEntityDataMapperTest {

    private static final String FAKE_PHOTO_ID = "123";
    private static final String FAKE_OWNER = "@48484642";
    private PhotoMapper photoMapper;


    @Before
    public  void setUp() throws  Exception{
        MockitoAnnotations.initMocks(this);
        photoMapper = new PhotoMapper();
    }

    @Test
    public void testTransformPhotoMapper(){
        PhotoEntity photoEntity = createFakePhotoEntity();
        Photo photo = photoMapper.transform(photoEntity);
        assertThat(photo).isInstanceOf(Photo.class);
        assertThat(photo.getId()).isEqualTo(FAKE_PHOTO_ID);
        assertThat(photo.getOwner()).isEqualTo(FAKE_OWNER);
    }
    @Test
    public void testTransformPhotoCollectionMapper(){
        PhotoEntity photoEntity1 = mock(PhotoEntity.class);
        PhotoEntity photoEntity2 = mock(PhotoEntity.class);
        Collection<PhotoEntity> photoEntities= new ArrayList<>(4);
        photoEntities.add(photoEntity1);
        photoEntities.add(photoEntity2);
        RealmList<PhotoEntity> photoRealmList = new RealmList<>();
        photoRealmList.addAll(photoEntities);
        Collection<Photo> photos = photoMapper.transformList(photoRealmList);
        assertThat(photos.toArray()[0]).isInstanceOf(Photo.class);
        assertThat(photos.toArray()[1]).isInstanceOf(Photo.class);
        assertThat(photos.size()).isEqualTo(2);
    }

    public PhotoEntity createFakePhotoEntity(){
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setId(FAKE_PHOTO_ID);
        photoEntity.setOwner(FAKE_OWNER);
        return  photoEntity;
    }




}
