import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Cette classe permet de tester le bon fonctionnement du constructeur et des méthodes de la classe Grille.
 */
public class TestGrille {
    /**
     * Méthode de test qui s'assure que le getter Grille.getNbLignes() fonctionne correctement.
     */
    @Test
    public void testGetNbLignes_RecuperationNbLignes() {
        Grille grille = new Grille();
        grille.construireGrille(50, 100, 80, 5, 1);
        assertEquals(50, grille.getNbLignes());
    }

    /**
     * Méthode de test qui s'assure que le getter Grille.getNbColonnes() fonctionne correctement.
     */
    @Test
    public void testGetNbColonnes_RecuperationNbColonnes() {
        Grille grille = new Grille();
        grille.construireGrille(50, 100, 80, 5, 1);
        assertEquals(100, grille.getNbColonnes());
    }

    /**
     * Méthode de test qui s'assure que le setter Grille.setNbLignes() fonctionne correctement.
     */
    @Test
    public void testSetNbLignes_ModificationNbLignes() {
        Grille grille = new Grille();
        grille.construireGrille(50, 100, 80, 5, 1);
        grille.setNbLignes(150);
        assertEquals(150, grille.getNbLignes());
    }

    /**
     * Méthode de test qui s'assure que le setter Grille.setNbColonnes() fonctionne correctement.
     */
    @Test
    public void testSetNbColonnes_ModificationNbColonnes() {
        Grille grille = new Grille();
        grille.construireGrille(50, 100, 80, 5, 1);
        grille.setNbColonnes(150);
        assertEquals(150, grille.getNbColonnes());
    }

    /**
     * Méthode de test qui s'assure qu'une cellule (i, j) appartient bien à une grille.
     */
    @Test
    public void testDansGrille_AppartenanceCelluleAGrille() {
        Grille grille = new Grille();
        grille.construireGrille(50, 100, 80, 5, 1);
        assertTrue(grille.dansGrille(10, 10));
        assertFalse(grille.dansGrille(55, 110));
    }

    /**
     * Méthode de test qui s'assure que l’insertion d'une cellule dans la grille fonctionne correctement.
     */
    @Test
    public void testSetGetCellule_InsertionRecuperationCellule(){
        Grille grille = new Grille();
        grille.construireGrille(10, 10, 80, 5, 1);
        Arbre arbre = new Arbre(0, 0, grille, false, false);
        grille.setCellule(0, 0, arbre);
        assertEquals(arbre, grille.getCellule(0, 0));
    }

    /**
     * Méthode de test qui s'assure que le remplacement d'une cellule dans la grille fonctionne correctement.
     */
    @Test
    public void testRemplacerCellule_RemplacementCellule(){
        Grille grille = new Grille();
        grille.construireGrille(10, 10, 80, 5, 1);
        Arbre arbre1 = new Arbre(0, 0, grille, false, false);
        grille.setCellule(0, 0, arbre1);
        Arbre arbre2 = new Arbre(0, 0, grille, true, false);
        grille.remplacerCellule(0, 0, arbre2);
        assertEquals(arbre2, grille.getCellule(0, 0));
    }

    /**
     * Méthode de test qui vérifie que l'initialisation aléatoire de la grille fonctionne correctement.
     */
    @Test
    public void testConstruireGrille_ConstructionGrille(){
        Grille grille = new Grille();
        grille.construireGrille(100, 100, 80, 5, 1);

        int nombreArbres = 0, nombresArbresSurSolHumide = 0, nombresArbresPeuInflammable = 0;

        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                Cellule cellule = grille.getCellule(i, j);
                if(cellule.getEtat() == 1 || cellule.getEtat() == 2 || cellule.getEtat() == 3) {
                    nombreArbres += 1;
                    Arbre arbre = (Arbre) cellule;
                    if(arbre.solEstHumide()) nombresArbresSurSolHumide += 1;
                    if(arbre.arbreEstPeuInflammable()) nombresArbresPeuInflammable += 1;
                }
            }
        }

        assertThat(nombreArbres).isCloseTo(8000,within(80));
        assertThat(nombresArbresSurSolHumide).isCloseTo(400,within(100));
        assertThat(nombresArbresPeuInflammable).isCloseTo(80,within(50));
    }

    /**
     * Méthode main qui s'assure que la grille s'affiche correctement dans une fenêtre.
     */
    public static void main(String[] args){
        // Création de la grille
        Grille grille = new Grille();
        grille.construireGrille(100, 100, 80, 5, 1);
        //grille.setBackground(Color.BLUE);

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
