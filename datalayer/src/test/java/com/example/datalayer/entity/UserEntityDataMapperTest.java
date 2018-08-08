package com.example.datalayer.entity;

import com.example.datalayer.entity.mapper.UserMapper;
import com.example.domainlayer.model.User;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityDataMapperTest {
    private static final String FAKE_ID="12@3";
    private static final String FAKE_USERNAME="fakeGuy";

    private UserMapper userMapper;

    @Before
    public  void setUp(){
        userMapper = new UserMapper();
    }
    @Test
    public void testTransformFromUserMapper(){
        UserEntity userEntity = createFakeUser();
        User user = userMapper.transform(userEntity);
        assertThat(user).isInstanceOf(User.class);
        assertThat(user.getId()).isEqualTo(FAKE_ID);
        assertThat(user.getUsername()).isEqualTo(FAKE_USERNAME);
    }

    public UserEntity createFakeUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(new Username(FAKE_USERNAME));
        userEntity.setId(FAKE_ID);
        return userEntity;
    }
}
