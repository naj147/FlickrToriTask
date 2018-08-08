package com.example.naj_t.flickrtoritask.models;

import com.example.datalayer.entity.UserEntity;
import com.example.domainlayer.model.User;
import com.example.naj_t.flickrtoritask.models.Mapper.UserModelMapper;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserModelMapperTest {
    private static final String FAKE_ID="12@3";
    private static final String FAKE_USERNAME="fakeGuy";
    private  UserModelMapper userModelMapper;

    @Before
    public void setUp() {
        userModelMapper = new UserModelMapper();
    }

    @Test
    public void transfromUserModelMapperTest(){
        UserModel userModel = userModelMapper.transfrom(createFakeUser());
        assertThat(userModel).isInstanceOf(UserModel.class);
        assertThat(userModel.getId()).isEqualTo(FAKE_ID);
        assertThat(userModel.getUsername()).isEqualTo(FAKE_USERNAME);
    }

    public User createFakeUser(){
        User user = new User();
        user.setId(FAKE_ID);
        user.setUsername(FAKE_USERNAME);
        return user;
    }

}
