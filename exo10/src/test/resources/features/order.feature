Feature: Gestion des commandes

  Scenario: Ajout d'un produit à une commande
    Given une commande existe
    And un produit "chaussures" est disponible
    When l'utilisateur ajoute le produit "chaussures" à la commande
    Then le produit "chaussures" est présent dans la commande

  Scenario: Ajout d'un produit déjà présent dans la commande
    Given une commande existe
    And le produit "chaussures" est déjà dans la commande avec une quantité de 1
    When l'utilisateur ajoute le produit "chaussures" à la commande
    Then la quantité du produit "chaussures" est de 2

  Scenario: Ajout d'un produit à une commande inexistante
    Given aucune commande n'existe
    When l'utilisateur ajoute le produit "chaussures" à la commande
    Then une erreur "Commande introuvable" est renvoyée

  Scenario: Diminution de la quantité d'un produit
    Given une commande existe
    And le produit "chaussures" est déjà dans la commande avec une quantité de 2
    When l'utilisateur supprime le produit "chaussures" de la commande
    Then la quantité du produit "chaussures" est de 1

  Scenario: Suppression d'un produit dont la quantité est 1
    Given une commande existe
    And le produit "chaussures" est déjà dans la commande avec une quantité de 1
    When l'utilisateur supprime le produit "chaussures" de la commande
    Then le produit "chaussures" n'est plus dans la commande

  Scenario: Suppression d'un produit absent de la commande
    Given une commande existe
    And aucun produit "chaussures" n'est dans la commande
    When l'utilisateur supprime le produit "chaussures" de la commande
    Then une erreur "Produit introuvable dans la commande" est renvoyée

  Scenario: Validation d'une commande
    Given une commande existe
    When l'utilisateur valide la commande
    Then l'utilisateur reçoit une confirmation de commande

  Scenario: Validation d'une commande inexistante
    Given aucune commande n'existe
    When l'utilisateur valide la commande
    Then une erreur "Commande introuvable" est renvoyée