package com.example.naj_t.flickrtoritask.view;

import android.content.Context;

public interface LoadDataView {
    void showLoading();
    void hideLoading();
    void showRetry();
    void hideRetry();
    void showError(String message);
    Context context();
}
