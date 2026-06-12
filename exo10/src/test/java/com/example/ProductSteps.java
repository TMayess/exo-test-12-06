package com.example;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import io.cucumber.java.en.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductSteps {

    private ProductRepository productRepository = mock(ProductRepository.class);
    private ProductService productService = new ProductService(productRepository);
    private List<Product> results;

    @Given("des produits sont disponibles dans la boutique")
    public void produitsDisponibles() {
        when(productRepository.findByKeyword("chaussures"))
                .thenReturn(List.of(new Product("chaussures", 40, "sport")));
        when(productRepository.findByMaxPrice(50))
                .thenReturn(List.of(new Product("chaussures", 40, "sport")));
        when(productRepository.findByCategory("sport"))
                .thenReturn(List.of(new Product("chaussures", 40, "sport")));
    }

    @When("l'utilisateur recherche le mot-clé {string}")
    public void rechercheMotCle(String keyword) {
        results = productService.search(keyword);
    }

    @When("l'utilisateur recherche des produits avec un prix maximum de {int}")
    public void rechercheMaxPrice(int maxPrice) {
        results = productService.searchByMaxPrice(maxPrice);
    }

    @When("l'utilisateur sélectionne la catégorie {string}")
    public void selectionCategorie(String category) {
        results = productService.searchByCategory(category);
    }

    @Then("une liste de produits contenant {string} est retournée")
    public void listeProduitsContenant(String keyword) {
        assertFalse(results.isEmpty());
        assertTrue(results.stream().anyMatch(p -> p.getName().contains(keyword)));
    }

    @Then("seuls les produits dont le prix est inférieur ou égal à {int} sont retournés")
    public void produitsMaxPrice(int maxPrice) {
        assertFalse(results.isEmpty());
        assertTrue(results.stream().allMatch(p -> p.getPrice() <= maxPrice));
    }

    @Then("les produits de la catégorie {string} sont retournés")
    public void produitsCategorie(String category) {
        assertFalse(results.isEmpty());
        assertTrue(results.stream().allMatch(p -> p.getCategory().equals(category)));
    }
}