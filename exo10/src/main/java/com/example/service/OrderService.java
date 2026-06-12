package com.example.service;


import com.example.model.Order;
import com.example.model.Product;
import com.example.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String addProduct(Product product) {
        Order order = orderRepository.findCurrent()
                .orElseThrow(() -> new IllegalArgumentException("Commande introuvable"));
        order.addProduct(product);
        return "Produit ajouté";
    }

    public String removeProduct(Product product) {
        Order order = orderRepository.findCurrent()
                .orElseThrow(() -> new IllegalArgumentException("Commande introuvable"));
        order.removeProduct(product);
        return "Produit supprimé";
    }

    public String validate() {
        orderRepository.findCurrent()
                .orElseThrow(() -> new IllegalArgumentException("Commande introuvable"));
        return "Confirmation de commande";
    }
}