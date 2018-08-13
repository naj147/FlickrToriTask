package com.example.naj_t.flickrtoritask.view;

import com.example.naj_t.flickrtoritask.models.PhotoModel;
import com.example.naj_t.flickrtoritask.models.PhotosModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link PhotosModel}.
 */
public interface PhotosListView extends LoadDataView {
    /**
     * Render a Photo list in the UI.
     *
     * @param photosModel The collection of {@link PhotoModel} that will be shown.
     */
    void renderPhotosList(PhotosModel photosModel);

}
