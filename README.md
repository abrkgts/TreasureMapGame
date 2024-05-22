# TreasureMap

## Prérequis

- Java 17 ou supérieur
- Maven 3.6 ou supérieur

## Installation

Clonez le dépôt :

```sh
git clone https://github.com/abrkgts/TreasureMapGame.git
```

## Construction

A la racine, compilez et construisez le projet à l'aide de Maven :

```sh
mvn clean install
```

Cela créera un fichier JAR dans le répertoire `target`.

## Utilisation

Pour exécuter l'application, utilisez la commande suivante à la racine du projet :

```sh
java -jar target/TreasureMap-0.0.1-SNAPSHOT.jar "input.txt"
```

## Sortie

Les résultats seront écrits dans un fichier nommé 'result.txt'.

## Tests

Pour exécuter les tests unitaires, utilisez la commande suivante :

```sh
mvn test
```
