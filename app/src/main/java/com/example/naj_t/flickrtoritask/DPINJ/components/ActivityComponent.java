package com.example.naj_t.flickrtoritask.DPINJ.components;


import android.app.Activity;

import com.example.naj_t.flickrtoritask.DPINJ.PerActivity;
import com.example.naj_t.flickrtoritask.DPINJ.modules.ActivityModule;

import dagger.Component;
@PerActivity
@Component (dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
