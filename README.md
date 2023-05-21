# Simulation feu de forêt
Ce projet a été réalisé en groupe dans le cadre de l’unité d’enseignement
intitulée Projet mathématique-informatique de l’université d’Aix-Marseille.

## Auteurs
* Gladys DJOUFACK TADONKA 
* Maeva DHAYNAUT
* Amine HADDOU
* Nassim amar ROUAG
* Franck SOULON

## Roadmap
Le planning que nous avons suivi au cours de la réalisation de ce projet
est contenu dans le répertoire [```gantt/Gantt_simulation_feu_de_foret.pdf```](gantt/Gantt_simulation_feu_de_foret.pdf)

## Code:
Le répertoire [```src/main/java/```](src/main/java/) contient les fichiers source du projet.
Le répertoire [```src/test/java/```](src/test/java/) contient les classes de test de l'application.
Les dépendances du projet sont listées dans le fichier [```build.gradle```](build.gradle)
Le fichier [```App.java```](src/main/java/App.java) est le fichier à lancer pour exécuter le projet en mode graphique.
Il contient la méthode main du projet.

**Mode d'emploi rapide :**

* On peut à tout moment modifier directement l'état d'une cellule de la grille en cliquant dessus.
* La partie "Configurer la forêt" permet de définir les caractéristiques d'initialiser la grille de façon aléatoire. Cette action est exécutée après un click sur le bouton "Générer".
* La partie "Facteurs externes" permet de définir la direction du vent ainsi que la saison souhaitée pour pouvoir observer leur impact sur la propagation du feu.
* La partie "Simuler" contient deux boutons : le bouton "Lancer" qui permet de lancer la simulation et le bouton "Arrêter" qui met en pause la simulation.
* Au pied du menu, il est possible de voir le pourcentage d'arbres déjà brulés ainsi qu'une légende expliquant le code couleur utilisé dans l'application.

## Documentation
Le rapport en format pdf de ce projet est accessible à l'adresse [```docs/rapport/Simulation_feu_de_foret_Automates_cellulaires.pdf```](docs/rapport/Simulation_feu_de_foret_Automates_cellulaires.pdf).

Tandis que La documentation technique en format `html` est accessible à l'adresse [```docs/doc_technique/package-summary.html```](docs/doc_technique/package-summary.html).