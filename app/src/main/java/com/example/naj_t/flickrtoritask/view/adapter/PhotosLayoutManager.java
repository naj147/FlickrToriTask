package com.example.naj_t.flickrtoritask.view.adapter;

import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Layout manager to position items inside a {@link android.support.v7.widget.RecyclerView}.
 */
public class PhotosLayoutManager extends StaggeredGridLayoutManager {

    public PhotosLayoutManager(int columnCount) {
        super(columnCount,VERTICAL);
    }
}
