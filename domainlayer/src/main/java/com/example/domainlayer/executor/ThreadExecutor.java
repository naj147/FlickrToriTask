package com.example.domainlayer.executor;

import java.util.concurrent.Executor;

/**
 * Executor implementation that will execute the
 * {@link com.example.domainlayer.interactor.UseCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {}
