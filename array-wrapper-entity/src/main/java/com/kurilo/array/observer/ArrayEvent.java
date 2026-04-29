package com.kurilo.array.observer;

import com.kurilo.array.entity.IntArray;

import java.util.EventObject;

public class ArrayEvent extends EventObject {
    public ArrayEvent(IntArray source){
        super(source);
    }

    @Override
    public IntArray getSource(){
        return (IntArray) super.getSource();
    }
}
