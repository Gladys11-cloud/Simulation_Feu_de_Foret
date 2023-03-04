import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Cette classe permet de tester le bon fonctionnement du constructeur et des méthodes de la classe Grille.
 */
public class TestGrille {
    /**
     * Méthode de test qui s'assure que le constructeur de la Classe Grille fonctionne correctement.
     */
    @Test
    public void testConstructeur_CreationGrille() {
        System.out.println("test");
    }

    /**
     * Méthode de test qui s'assure que le getter Grille.getNbLignes() fonctionne correctement.
     */
    @Test
    public void testGetNbLignes_RecuperationNbLignes() {

    }

    /**
     * Méthode de test qui s'assure que le getter Grille.getNbColonnes() fonctionne correctement.
     */
    @Test
    public void testGetNbColonnes_RecuperationNbColonnes() {

    }

    /**
     * Méthode de test qui s'assure que le setter Grille.setNbLignes() fonctionne correctement.
     */
    @Test
    public void testSetNbLignes_ModificationNbLignes() {

    }

    /**
     * Méthode de test qui s'assure que le setter Grille.setNbColonnes() fonctionne correctement.
     */
    @Test
    public void testSetNbColonnes_ModificationNbColonnes() {

    }

    /**
     * Méthode de test qui s'assure qu'une cellule (i, j) appartient bien à une grille.
     */
    @Test
    public void testDansGrille_AppartenanceCelluleAGrille() {

    }

    /**
     * Méthode de test qui s'assure que l’insertion d'une cellule dans la grille fonctionne correctement.
     */
    @Test
    public void testSetCellule_InsertionCellule(){

    }

    /**
     * Méthode de test qui s'assure que la récupération d'une cellule de la grille fonctionne correctement.
     */
    @Test
    public void getSetCellule_RecuperationCellule(){

    }

    /**
     * Méthode de test qui s'assure que le remplacement d'une cellule dans la grille fonctionne correctement.
     */
    @Test
    public void testRemplacerCellule_RemplacementCellule(){

    }

    /**
     * Méthode de test qui vérifie que le calcul des 4 voisins d'une cellule fonctionne correctement.
     */
    @Test
    public void testCalculeVoisins_CalculVoisinsCellule(){

    }

    /**
     * Méthode de test qui vérifie que l'initialisation aléatoire de la grille fonctionne correctement.
     */
    @Test
    public void testConstruireGrille_ConstructionGrille(){

    }

    /**
     * Méthode de test qui vérifie que l'initialisation aléatoire de la grille fonctionne correctement.
     */
    @Test
    public void testConstruireGrille_AfficherGrille(){
        // Création de la grille
        Grille grille = new Grille();
        grille.construireGrille(50, 50, 80, 5, 1);

        // Affichage de la grille dans une fenêtre
        JFrame frame = new JFrame();
        frame.setTitle("Test de la classe Grille");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().add(grille);

        frame.setVisible(true);
    }

    public static void main(String[] args){
        // Création de la grille
        Grille grille = new Grille();
        grille.construireGrille(50, 50, 80, 5, 1);

        // Affichage de la grille dans une fenêtre
        JFrame frame = new JFrame();
        frame.setTitle("Test de la classe Grille");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().add(grille);

        frame.setVisible(true);
    }
}
