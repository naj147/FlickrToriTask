package com.example.domainelayer.model;

import com.example.domainlayer.model.Photo;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PhotoTest {

    private  static final  String FAKE_ID="123";

    private Photo photo;



    @Before
    public void setUp(){
        photo = new Photo(FAKE_ID);
    }

    @Test
    public void testConstructorWorks(){
       final String id= photo.getId();
        assertThat(id).isEqualTo(FAKE_ID);
    }
}
