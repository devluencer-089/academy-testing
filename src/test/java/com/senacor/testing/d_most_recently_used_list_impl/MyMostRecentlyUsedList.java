package com.senacor.testing.d_most_recently_used_list_impl;

import com.senacor.testing.d_most_recently_used_list.MostRecentlyUsedList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavle Bektasevic on 19.07.2018.
 */
public class MyMostRecentlyUsedList implements MostRecentlyUsedList {

    private int capacity;

    private final List<Object> list;

    public MyMostRecentlyUsedList(int capacity) {
        this.capacity = capacity;
        this.list = new ArrayList<>(capacity);
    }

    @Override
    public void add(Object o) {
        if (list.size() < capacity) {
            list.add(o);
        }
        else {
            throw new IllegalArgumentException("List size exceeded!");
        }

    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public List<Object> getRecentlyUsed() {
        return new ArrayList<>(list);
    }
}
