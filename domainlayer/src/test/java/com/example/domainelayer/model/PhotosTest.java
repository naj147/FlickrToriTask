package com.example.domainelayer.model;

import com.example.domainlayer.model.Photo;
import com.example.domainlayer.model.Photos;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PhotosTest {

    private  static final  String FAKE_ID="123";
    private  static final  int FAKE_NBR_PGS=44;

    private Photos photos;



    @Before
    public void setUp(){
        Photo photo = new Photo(FAKE_ID);
        List<Photo> photoList = new ArrayList<>();
        photoList.add(photo);
        photos = new Photos();
        photos.setPages(FAKE_NBR_PGS);
        photos.setPhoto(photoList);
    }

    @Test
    public void testConstructorWorks(){
        assertThat(photos).isInstanceOf(Photos.class);
        assertThat(photos.getPages()).isEqualTo(FAKE_NBR_PGS);
        assertThat(photos.getPhoto().toArray()[0]).isInstanceOf(Photo.class);
        assertThat(photos.getPhoto().size()).isEqualTo(1);
        assertThat(photos.getPhoto().get(0).getId()).isEqualTo(FAKE_ID);
    }
}
