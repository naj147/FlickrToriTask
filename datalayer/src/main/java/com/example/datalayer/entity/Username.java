package com.example.datalayer.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Username used in UserEntity.
 */
public class Username extends RealmObject {
    @SerializedName("_content")
    private
    String _content;

    public Username() {
    }

    public Username(String _content) {

        this._content = _content;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}