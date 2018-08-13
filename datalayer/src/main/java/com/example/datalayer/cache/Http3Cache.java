package com.example.datalayer.cache;
import okhttp3.Cache;


/**
 * Interface representing OkHttp3Cache
 * Although I don't know if it was a good idea to share Http3 cache between all the instances of Picasso as sometimes
 * It lags to load a picture when you migrate from seeing all the picture to seeing the details of one pic
 */
public interface Http3Cache {
    /**
     * Method that acquires gets the cache allocated to OKHttp3
     */
    Cache getCache();
}
