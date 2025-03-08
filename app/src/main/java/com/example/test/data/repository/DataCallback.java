package com.example.test.data.repository;

import androidx.annotation.NonNull;

public interface DataCallback<T> {
    void onReceiveData(@NonNull T data);

    void onFailure(Throwable t);
}
