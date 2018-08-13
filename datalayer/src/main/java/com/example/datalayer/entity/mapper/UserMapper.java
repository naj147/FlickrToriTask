package com.example.datalayer.entity.mapper;

import com.example.datalayer.entity.UserEntity;
import com.example.domainlayer.model.User;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
public class UserMapper {
    @Inject
    public UserMapper() {
    }

    /**
     * Transform a {@link UserEntity} into a {@link User}.
     *
     * @param userEntity Object to be transformed.
     * @return {@link User} if valid {@link UserEntity} otherwise null.
     */
    public User  transform(UserEntity userEntity){
        User user = null;
        if(userEntity!=null){
            user = new User(userEntity.getUsername().get_content(),userEntity.getId(),userEntity.getNsid(),userEntity.getIconserver(),userEntity.getIconfarm());
        }
        return user;
    }
}
