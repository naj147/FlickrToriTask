package com.example.naj_t.flickrtoritask.models;

import com.example.domainlayer.model.Photo;
import com.example.domainlayer.model.Photos;
import com.example.naj_t.flickrtoritask.models.Mapper.PhotosModelMapper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PhotosModelMapperTest {

    private static final String FAKE_ID = "123";
    private static final String FAKE_OWNER="@48567231";
    private static final int FAKE_PG_NBR=13;
    private PhotosModelMapper photosModelMapper;



    @Before
    public void setUp(){
        photosModelMapper = new PhotosModelMapper();
    }
    @Test
    public void transformFromPhotosTest(){
        PhotosModel photosModel = photosModelMapper.transform(createFakePhotosModel());
        assertThat(photosModel).isInstanceOf(PhotosModel.class);
        assertThat(photosModel.getPages()).isEqualTo(FAKE_PG_NBR);
        assertThat(photosModel.getPhoto().toArray()[0]).isInstanceOf(PhotoModel.class);
        assertThat(photosModel.getPhoto().size()).isEqualTo(1);
        assertThat(photosModel.getPhoto().get(0).getOwner()).isEqualTo(FAKE_OWNER);
        assertThat(photosModel.getPhoto().get(0).getId()).isEqualTo(FAKE_ID);
    }


    public Photos createFakePhotosModel(){

        Photo photo = new Photo();
        photo.setId(FAKE_ID);
        photo.setOwner(FAKE_OWNER);
        Photos photos = new Photos();
        photos.setPages(FAKE_PG_NBR);
        List<Photo> photoList = new ArrayList<>();
        photoList.add(photo);
        photos.setPhoto(photoList);
        return photos;
    }
}
