package com.example.naj_t.flickrtoritask.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

public class PhotosLayoutManager extends StaggeredGridLayoutManager {

    public PhotosLayoutManager(int columnCount) {
        super(columnCount,VERTICAL);
    }
}
