package com.senacor.testing.d_most_recently_used_list;

import java.util.LinkedList;
import java.util.List;

public class MostRecentlyUsedListImpl implements MostRecentlyUsedList {

    LinkedList recentlyUsedObjects;
    int listSize;

    public MostRecentlyUsedListImpl(int size) {
        this.recentlyUsedObjects = new LinkedList();
        this.listSize = size;
    }

    @Override
    public void add(Object o) {
        if(o == null) {
            throw new IllegalArgumentException("Null objects can not be added.");
        }

        if(size() >= listSize) {
            this.recentlyUsedObjects.removeFirst();
        }

        this.recentlyUsedObjects.addLast(o);
    }

    @Override
    public int size() {
        return this.recentlyUsedObjects.size();
    }

    @Override
    public List<Object> getRecentlyUsed() {
        return this.recentlyUsedObjects;
    }

    public void clear() {
        this.recentlyUsedObjects.clear();
    }
}
