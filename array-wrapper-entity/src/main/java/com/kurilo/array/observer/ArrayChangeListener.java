package com.kurilo.array.observer;

import com.kurilo.array.entity.IntArray;

public interface ArrayChangeListener {
    void onArrayChanged(IntArray array);
}