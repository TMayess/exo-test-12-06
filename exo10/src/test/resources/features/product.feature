Feature: Recherche et navigation de produits

  Scenario: Recherche de produits par mot-clé
    Given des produits sont disponibles dans la boutique
    When l'utilisateur recherche le mot-clé "chaussures"
    Then une liste de produits contenant "chaussures" est retournée

  Scenario: Recherche de produits par prix maximum
    Given des produits sont disponibles dans la boutique
    When l'utilisateur recherche des produits avec un prix maximum de 50
    Then seuls les produits dont le prix est inférieur ou égal à 50 sont retournés

  Scenario: Navigation par catégorie
    Given des produits sont disponibles dans la boutique
    When l'utilisateur sélectionne la catégorie "sport"
    Then les produits de la catégorie "sport" sont retournés