package com.example.domainlayer.exception;

public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}