package com.senacor.testing.d_most_recently_used_list;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ListBackedMostRecentlyUsedList implements MostRecentlyUsedList {

    private final LinkedList<Object> elements = new LinkedList<>();

    public ListBackedMostRecentlyUsedList(Object... toAdd) {
        if(Objects.isNull(toAdd)) {
            throw new IllegalArgumentException(String.format("The class %s does not support nulls.",this.getClass().getSimpleName()));
        }

        for (Object o : toAdd) {
            this.add(o);
        }
    }

    @Override
    public void add(Object toAdd) {

        if(Objects.equals(toAdd, null)) {
            throw new IllegalArgumentException(String.format("The class %s does not support nulls.",this.getClass().getSimpleName()));
        }

        if (toAdd.equals(elements.peekFirst())) {
            return;
        }

        elements.addFirst(toAdd);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public List<Object> getRecentlyUsed() {
        return elements;
    }
}
