package com.senacor.testing.state_vs_behavior_verification;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<String, Integer> inventory = new HashMap<>();

    public void add(String product, int amountChange) {
        Integer oldAmount = getInventory(product);
        inventory.put(product, oldAmount + amountChange);
    }

    public boolean hasInventory(String product, int amount) {
        return getInventory(product) >= amount;
    }

    public void removeFromInventory(String product, int amount) {
        add(product, -amount);
    }

    public int getInventory(String product) {
        return inventory.getOrDefault(product, 0);
    }
}
