package com.example.naj_t.flickrtoritask.view.adapter;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A wrapper for {@link FilteredObject}
 */
public class FilteredObjects {
    @SerializedName("FilteredObject")
    List<FilteredObject> filteredObject;

    public FilteredObjects(List<FilteredObject> filteredObject) {
        this.filteredObject = filteredObject;
    }

    public FilteredObjects() {
    }

    public List<FilteredObject> getFilteredObject() {
        return filteredObject;
    }

    public void setFilteredObject(List<FilteredObject> filteredObject) {
        this.filteredObject = filteredObject;
    }
}
