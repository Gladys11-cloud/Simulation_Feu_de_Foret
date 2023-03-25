import java.awt.*;
import java.awt.event.*;

/**
 * Cette classe est une extension de la classe Cellule. Elle permet de modéliser le sol nu.
 */
public class SolNu extends Cellule {

    /** Représente la couleur du sol nu. */
    public static final String COULEUR = "#F6E5C6";

    /**
     * Constructeur de la classe qui crée une nouvelle instance de SolNu avec les paramètres donnés.
     *
     * @param x      Le numéro de ligne ou se trouve la cellule dans la grille.
     * @param y      Le numéro de colonne ou se trouve la cellule dans la grille.
     * @param grille La référence de l'objet grille dans lequel se trouve la cellule.
     */
    public SolNu(int x, int y, Grille grille){
        super(x, y, grille);
        this.etat = 0;
        this.setBackground(Color.decode(COULEUR));

        // définir l'action à effectuer après un click gauche de la souris
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Click gauche
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Créer un nouvel arbre vivant pour remplacer le sol nu.
                    Arbre arbre = new Arbre(SolNu.this.x, SolNu.this.y, SolNu.this.grille, false, false);
                    arbre.setVoisins(SolNu.this.voisins);
                    SolNu.this.grille.remplacerCellule(SolNu.this.x, SolNu.this.y, arbre);
                }
            }
        });
    }

    /**
     * Méthode permettant de calculer l’état futur d'un sol nu (un sol nu ne change jamais d'état)
     *
     * @param direction_vent La direction courante du vent (NORD, SUD, EST, OUEST).
     * @param saison La saison courante (HIVER, PRINTEMPS, ETE, AUTOMNE).
     */
    @Override
    public void calculeEtatFutur(String direction_vent, String saison){
        this.etat_futur = this.etat;
    }
}
