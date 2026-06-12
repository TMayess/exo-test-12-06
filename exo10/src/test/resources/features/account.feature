Feature: Gestion du compte utilisateur

  Scenario: Création d'un compte avec succès
    Given un formulaire d'inscription est disponible
    When l'utilisateur s'inscrit avec l'email "john@example.com" le nom "john" et le mot de passe "pass123"
    Then l'utilisateur reçoit une confirmation d'inscription

  Scenario: Création d'un compte avec un identifiant déjà existant
    Given un utilisateur avec le nom "john" existe déjà
    When l'utilisateur s'inscrit avec l'email "john@example.com" le nom "john" et le mot de passe "pass123"
    Then un message d'erreur "Compte déjà existant" est affiché

  Scenario: Connexion réussie
    Given un utilisateur avec le nom "john" et le mot de passe "pass123" existe
    When l'utilisateur se connecte avec le nom "john" et le mot de passe "pass123"
    Then l'utilisateur est redirigé vers la page d'accueil

  Scenario: Connexion échouée
    Given un utilisateur avec le nom "john" et le mot de passe "pass123" existe
    When l'utilisateur se connecte avec le nom "john" et le mot de passe "mauvais"
    Then un message d'erreur "Identifiants incorrects" est affiché