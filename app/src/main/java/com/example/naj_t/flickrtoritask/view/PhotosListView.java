package com.example.naj_t.flickrtoritask.view;

import android.content.Loader;

import com.example.naj_t.flickrtoritask.models.PhotosModel;

public interface PhotosListView extends LoadDataView {

    void renderPhotosList(PhotosModel photosModel);

}
