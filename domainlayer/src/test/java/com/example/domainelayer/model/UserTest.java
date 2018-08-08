package com.example.domainelayer.model;

import com.example.domainlayer.model.User;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserTest {
    private static final String FAKE_ID="12@3";
    private static final String FAKE_USERNAME="fakeGuy";
    private User user;

    @Before
    public void setUp(){
        user = new User();
        user.setId(FAKE_ID);
        user.setUsername(FAKE_USERNAME);
    }
    @Test
    public void testConstructorWork(){
        assertThat(user.getId()).isEqualTo(FAKE_ID);
        assertThat(user.getUsername()).isEqualTo(FAKE_USERNAME);
    }
}
