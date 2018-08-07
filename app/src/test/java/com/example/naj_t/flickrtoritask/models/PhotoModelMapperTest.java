package com.example.naj_t.flickrtoritask.models;

import com.example.domainlayer.model.Photo;
import com.example.naj_t.flickrtoritask.models.Mapper.PhotoModelMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PhotoModelMapperTest {
    private static final String FAKE_ID = "123";
    private static final String FAKE_OWNER="@48567231";
    private PhotoModelMapper photoModelMapper;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        photoModelMapper = new PhotoModelMapper();
    }
    @Test
    public  void transformFromPhotoTest(){
        PhotoModel photoModel = photoModelMapper.transform(createFakePhotoModel());

        assertThat(photoModel).isInstanceOf(PhotoModel.class);
        assertThat(photoModel.getId()).isEqualTo(FAKE_ID);
        assertThat(photoModel.getOwner()).isEqualTo(FAKE_OWNER);


    }
    @Test
    public  void transformFromPhotoCollectionTest(){
        Photo mockedPhoto1 = mock(Photo.class);
        Photo mockedPhoto2 = mock(Photo.class);
        List<Photo> photos = new ArrayList<>(5);
        photos.add(mockedPhoto1);
        photos.add(mockedPhoto2);
        Collection<PhotoModel> photoModelCollection = photoModelMapper.transformList(photos);
        assertThat(photoModelCollection.toArray()[0]).isInstanceOf(PhotoModel.class);
        assertThat(photoModelCollection.toArray()[1]).isInstanceOf(PhotoModel.class);
        assertThat(photoModelCollection.size()).isEqualTo(2);
    }

    public Photo createFakePhotoModel(){
        Photo photo = new Photo();
        photo.setId(FAKE_ID);
        photo.setOwner(FAKE_OWNER);
        return photo;
    }


}
