package com.senacor.testing.d_most_recently_used_list_possible_solution;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.senacor.testing.d_most_recently_used_list.MostRecentlyUsedList;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MostRecentlyUsedListExample implements MostRecentlyUsedList {

    private static int UNBOUND = Integer.MAX_VALUE;

    private final int capacity;

    private LinkedList<Object> recentlyUsedItems = Lists.newLinkedList();

    MostRecentlyUsedListExample() {
        this(UNBOUND);
    }

    MostRecentlyUsedListExample(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void add(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("null values are not supported");
        }

        if (recentlyUsedItems.size() == capacity) {
            recentlyUsedItems.remove(recentlyUsedItems.size() - 1);
        }

        addRecentlyUsedItem(o);
    }

    @Override
    public int size() {
        return recentlyUsedItems.size();
    }

    private void addRecentlyUsedItem(Object o) {
        recentlyUsedItems.remove(o);
        recentlyUsedItems.addFirst(o);
    }

    @Override
    public List<Object> getRecentlyUsed() {
        return ImmutableList.copyOf(recentlyUsedItems);
    }

    @Override
    public String toString() {
        return recentlyUsedItems.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}