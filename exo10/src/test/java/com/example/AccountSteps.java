package com.example;


import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import io.cucumber.java.en.*;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountSteps {

    private UserRepository userRepository = mock(UserRepository.class);
    private UserService userService = new UserService(userRepository);
    private String result;
    private Exception exception;

    @Given("un formulaire d'inscription est disponible")
    public void formulaireDisponible() {
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
    }

    @Given("un utilisateur avec le nom {string} existe déjà")
    public void utilisateurExisteDeja(String username) {
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(new User("john@example.com", username, "pass123")));
    }

    @Given("un utilisateur avec le nom {string} et le mot de passe {string} existe")
    public void utilisateurExiste(String username, String password) {
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(new User("john@example.com", username, password)));
    }

    @When("l'utilisateur s'inscrit avec l'email {string} le nom {string} et le mot de passe {string}")
    public void inscription(String email, String username, String password) {
        try {
            result = userService.register(email, username, password);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("l'utilisateur se connecte avec le nom {string} et le mot de passe {string}")
    public void connexion(String username, String password) {
        try {
            result = userService.login(username, password);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("l'utilisateur reçoit une confirmation d'inscription")
    public void confirmationInscription() {
        assertEquals("Inscription réussie", result);
    }

    @Then("l'utilisateur est redirigé vers la page d'accueil")
    public void redirectionAccueil() {
        assertEquals("Redirection vers la page d'accueil", result);
    }


    @Then("un message d'erreur {string} est affiché")
    public void messageErreur(String message) {
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }
}