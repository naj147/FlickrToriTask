package com.example.naj_t.flickrtoritask.models.Mapper;

import com.example.naj_t.flickrtoritask.models.UserModel;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link com.example.domainlayer.model.User} (in the domain layer) to {@link UserModel} in the
 * presentation layer.
 */
public class UserModelMapper {
    @Inject
    public UserModelMapper() {
    }

    /**
     * Transform a {@link com.example.domainlayer.model.User} into an {@link UserModel}.
     *
     * @param user Object to be transformed.
     * @return {@link UserModel}.
     */
    public UserModel transfrom(com.example.domainlayer.model.User user){
        UserModel userModel = null;
        if (user != null) {
            userModel = new UserModel(user.getUsername(), user.getId(), user.getNsid(), user.getIconserver(), user.getIconfarm());
        }
        return userModel;
    }
}
