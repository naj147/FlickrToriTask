package com.example.naj_t.flickrtoritask.view;

import com.example.naj_t.flickrtoritask.models.UserModel;

public interface PhotosDetailsView extends LoadDataView{
    /**
     * Render a User profile (Username and profile pic) in the UI.
     *
     * @param userModel The collection of {@link UserModel} that will be shown.
     */
    void renderPhoto(UserModel userModel);
}

