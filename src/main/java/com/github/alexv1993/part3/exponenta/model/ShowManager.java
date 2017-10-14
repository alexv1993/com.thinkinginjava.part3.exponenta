package com.github.alexv1993.part3.exponenta.model;

/**
 * Created by Alex on 14.10.2017.
 */
public class ShowManager<T> implements ShowInterface<T> {
    @Override
    public void showValue(T t) {
        System.out.println(t);
    }
}
