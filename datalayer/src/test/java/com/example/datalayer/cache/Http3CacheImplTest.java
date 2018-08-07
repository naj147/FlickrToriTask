package com.example.datalayer.cache;

import com.example.datalayer.ApplicationTestCase;
import com.example.datalayer.net.Http3Gen;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class Http3CacheImplTest extends ApplicationTestCase {

    Http3Cache http3Cache;
    Http3Gen http3Gen;
    @Before
    public void setUp(){
        http3Cache = new Http3CacheImpl(RuntimeEnvironment.application);

    }
    @Test
    public void Http3FileCreationTest(){
        http3Cache.getCache();
        http3Gen = new Http3Gen(http3Cache);
        http3Gen.okHttpClient();
    }
}
