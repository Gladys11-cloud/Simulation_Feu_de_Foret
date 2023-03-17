import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.swing.*;
import java.awt.*;


/**
 * Cette classe permet de tester le bon fonctionnement du constructeur et des méthodes de la classe Cellule.
 */
public class TestCellule {
    /**
     * Méthode de test qui s'assure que le constructeur de la Classe Cellule fonctionne correctement.
     */
    @Test
    public void testConstructeur_CreationCellule() {
        Grille grille = new Grille();
        // en testant le constructeur de TestCellule, on teste aussi celui de Cellule.
        Cellule cellule = new Cellule(0, 0, grille){
            @Override
            public void calculeEtatFutur (String direction_vent, String saison) {}
        };

        assertEquals(0, cellule.getEtat(), "L'état de la cellule tout juste après sa création devrait être 0 car un entier non initialisé vaut 0.");
        assertEquals(0, cellule.getEtatFutur(), "L'état futur de la cellule tout juste après sa création devrait être 0 car un entier non initialisé vaut 0.");
        assertNull(cellule.getVoisins(), "La liste des voisins de la cellule tout juste après sa création devrait être null.");
    }

    /**
     * Méthode de test qui s'assure que le getter de l'état d'une cellule fonctionne correctement.
     */
    @Test
    public void testGetEtat_RecuperationEtatCellule() {
        Grille grille = new Grille();
        // en testant le constructeur de TestCellule, on teste aussi celui de Cellule.
        Cellule cellule = new Cellule(0, 0, grille){
            @Override
            public void calculeEtatFutur (String direction_vent, String saison) {}
        };

        assertEquals(0, cellule.getEtat(), "L'état de la cellule devrait être 0 car un entier non initialisé vaut 0.");
    }

    /**
     * Méthode de test qui s'assure que le getter de l'état futur d'une cellule fonctionne correctement.
     */
    @Test
    public void testGetEtatFutur_RecuperationEtatFuturCellule() {
        Grille grille = new Grille();
        // en testant le constructeur de TestCellule, on teste aussi celui de Cellule.
        Cellule cellule = new Cellule(0, 0, grille){
            @Override
            public void calculeEtatFutur (String direction_vent, String saison) {}
        };

        assertEquals(0, cellule.getEtatFutur(), "L'état futur de la cellule devrait être 0 car un entier non initialisé vaut 0.");
    }

    /**
     * Méthode de test qui s'assure que le setter et le getter des voisins d'une cellule fonctionne correctement.
     */
    @Test
    public void testSetGetVoisins_AffectionRecuperationVoisinsCellule() {
        Grille grille = new Grille();
        Cellule cellule = new Cellule(0, 0, grille){
            @Override
            public void calculeEtatFutur (String direction_vent, String saison) {}
        };

        Cellule voisin_droite = new Cellule(0, 1, grille){
            @Override
            public void calculeEtatFutur (String direction_vent, String saison) {}
        };
        Cellule voisin_bas = new Cellule(1, 0, grille){
            @Override
            public void calculeEtatFutur (String direction_vent, String saison) {}
        };

        Cellule[] voisins = {null, null, voisin_droite, voisin_bas};
        cellule.setVoisins(voisins);
        assertArrayEquals(voisins, cellule.getVoisins(), "Les voisins de la cellule ne sont pas corrects.");
        assertEquals(4, cellule.getVoisins().length, "La cellule devrait avoir 4 voisins.");
    }

    /**
     * Méthode main qui s'assure que la cellule s'affiche correctement dans une fenêtre.
     */
    public static void main(String[] args){
        // Création de la grille
        Grille grille = new Grille();
        Cellule cellule = new Cellule(1, 0, grille){
            @Override
            public void calculeEtatFutur (String direction_vent, String saison) {}
        };
        cellule.setBackground(Color.BLUE);

        // Affichage de la grille dans une fenêtre
        JFrame frame = new JFrame();
        frame.setTitle("Test de la classe Cellule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().add(cellule, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}