package com.senacor.testing.d_most_recently_used_list;

import java.util.List;

public interface MostRecentlyUsedList {

    void add(Object o);

    int size();

    List<Object> getRecentlyUsed();
}
