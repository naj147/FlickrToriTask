package com.example.naj_t.flickrtoritask;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.app.AppCompatActivity;

public class MyCustomRule<A extends AppCompatActivity> extends ActivityTestRule<A> {
    public MyCustomRule(Class<A> activityClass) {
        super(activityClass);
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();

        // Maybe prepare some mock service calls
        // Maybe override some depency injection modules with mocks
    }

    @Override
    protected Intent getActivityIntent() {
        Intent customIntent = new Intent();
        // add some custom extras and stuff
        return customIntent;
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();
        // maybe you want to do something here
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
        // Clean up mocks
    }
}