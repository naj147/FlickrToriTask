package com.example.datalayer.net;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;


/**
 * A method to exclude a field from being retrieved by the GSON library
 */
public class AnnotationExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(Exclude.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}