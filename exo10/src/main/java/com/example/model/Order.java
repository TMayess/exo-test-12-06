package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    public void removeProduct(Product product) {
        if (!items.containsKey(product)) {
            throw new IllegalArgumentException("Produit introuvable dans la commande");
        }
        if (items.get(product) > 1) {
            items.put(product, items.get(product) - 1);
        } else {
            items.remove(product);
        }
    }

    public int getQuantity(Product product) {
        return items.getOrDefault(product, 0);
    }

    public boolean containsProduct(Product product) {
        return items.containsKey(product);
    }

    public Map<Product, Integer> getItems() { return items; }
}