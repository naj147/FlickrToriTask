package com.example.naj_t.flickrtoritask.models.Mapper;

import com.example.naj_t.flickrtoritask.models.UserModel;

import javax.inject.Inject;

public class UserModelMapper {
    @Inject
    public UserModelMapper() {
    }
    public UserModel transfrom(com.example.domainlayer.model.User user){
    UserModel userModel = null;
    if(user!=null){
        userModel = new UserModel(user.getUsername(),user.getId(),user.getNsid(),user.getIconserver(),user.getIconfarm());
    }
    return userModel;
    }
}
