package com.senacor.testing.d_most_recently_used_list;

import java.util.List;


/**
 * A list like data strcuture that holds recently used items
 * TODO: No strict semantics are given at this point
 */
public interface MostRecentlyUsedList {

    /**
     * Adds an item to the list.
     * The lists capacity must not be exceeded.
     * TODO: No strict semantics are given at this point
     */
    void add(Object o);

    /**
     * @return number of recently used items in this list
     */
    int size();

    /**
     * @return all elements of this MostRecentlyUsedList
     */
    List<Object> getRecentlyUsed();
}
