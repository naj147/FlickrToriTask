package com.example.datalayer.entity.mapper;

import com.example.datalayer.entity.UserEntity;
import com.example.domainlayer.model.User;

import javax.inject.Inject;

public class UserMapper {
    @Inject
    public UserMapper() {
    }
    public User  transform(UserEntity userEntity){
        User user = null;
        if(userEntity!=null){
            user = new User(userEntity.getUsername().get_content(),userEntity.getId(),userEntity.getNsid(),userEntity.getIconserver(),userEntity.getIconfarm());
        }
        return user;
    }
}
