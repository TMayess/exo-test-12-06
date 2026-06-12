package com.example;


import com.example.model.Order;
import com.example.model.Product;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;
import io.cucumber.java.en.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderSteps {

    private OrderRepository orderRepository = mock(OrderRepository.class);
    private OrderService orderService = new OrderService(orderRepository);
    private Order currentOrder;
    private Product product;
    private String result;
    private Exception exception;

    @Given("une commande existe")
    public void commandeExiste() {
        currentOrder = new Order();
        when(orderRepository.findCurrent()).thenReturn(Optional.of(currentOrder));
    }

    @Given("aucune commande n'existe")
    public void aucuneCommande() {
        when(orderRepository.findCurrent()).thenReturn(Optional.empty());
    }

    @Given("un produit {string} est disponible")
    public void produitDisponible(String name) {
        product = new Product(name, 40, "sport");
    }

    @Given("le produit {string} est déjà dans la commande avec une quantité de {int}")
    public void produitDansCommande(String name, int quantity) {
        product = new Product(name, 40, "sport");
        for (int i = 0; i < quantity; i++) {
            currentOrder.addProduct(product);
        }
    }

    @Given("aucun produit {string} n'est dans la commande")
    public void aucunProduitDansCommande(String name) {
        product = new Product(name, 40, "sport");
    }

    @When("l'utilisateur ajoute le produit {string} à la commande")
    public void ajoutProduit(String name) {
        if (product == null) product = new Product(name, 40, "sport");
        try {
            result = orderService.addProduct(product);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("l'utilisateur supprime le produit {string} de la commande")
    public void suppressionProduit(String name) {
        try {
            result = orderService.removeProduct(product);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("l'utilisateur valide la commande")
    public void validerCommande() {
        try {
            result = orderService.validate();
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("le produit {string} est présent dans la commande")
    public void produitPresent(String name) {
        assertTrue(currentOrder.containsProduct(product));
    }

    @Then("la quantité du produit {string} est de {int}")
    public void quantiteProduit(String name, int quantity) {
        assertEquals(quantity, currentOrder.getQuantity(product));
    }

    @Then("le produit {string} n'est plus dans la commande")
    public void produitAbsent(String name) {
        assertFalse(currentOrder.containsProduct(product));
    }

    @Then("une erreur {string} est renvoyée")
    public void erreurRenvoyee(String message) {
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Then("l'utilisateur reçoit une confirmation de commande")
    public void confirmationCommande() {
        assertEquals("Confirmation de commande", result);
    }
}