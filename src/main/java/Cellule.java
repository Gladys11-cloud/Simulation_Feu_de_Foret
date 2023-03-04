import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Cette classe abstraite est une extension de la classe JPannel de Java. Elle permet de modéliser les composantes de la forêt.
 */
public abstract class Cellule extends JPanel {

    /** Représente la couleur générale des bordures d'une cellule. */
    public static final String COULEUR_BORDURE_CELLULE = "#EACB9A";
    /** Représente l’état de la cellule (0 => sol nu, 1 => vivant, 2 => en feu, 3 => en cendre). */
    protected int etat;
    /** Représente l’état futur de la cellule à la prochaine itération. */
    protected int etat_futur;
    /** Numéro de ligne ou se trouve la cellule dans la grille. */
    protected int x;
    /** Numéro de colonne ou se trouve la cellule dans la grille. */
    protected int y;
    /** Représente la liste des voisins de la cellule dans l’ordre suivant: [voisin gauche, voisin du haut, voisin de droite, voisin du bas]. */
    protected Cellule[] voisins;
    /** Représente la référence de l'objet grille dans lequel se trouve la cellule. */
    protected Grille grille;


    // @param largeur La largeur initiale de la cellule lors de sa création.
    // @param hauteur La hauteur initiale de la cellule lors de sa création.
    /**
     * Constructeur de la classe qui crée une nouvelle instance de Cellule avec les paramètres donnés.
     *
     * @param x Le numéro de ligne ou se trouve la cellule dans la grille.
     * @param y Le numéro de colonne ou se trouve la cellule dans la grille.
     * @param grille La référence de l'objet grille dans lequel se trouve la cellule.
     */
    public Cellule(int x, int y, Grille grille) {
        // Appeler le constructeur de JPannel
        super();

        // Initialiser les attributs de la classe
        this.x = x;
        this.y = y;
        this.grille = grille;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Pour tracer une bordure fine autour de la cellule
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.decode(COULEUR_BORDURE_CELLULE));
        g2d.setStroke(new BasicStroke(0.5f));
        g2d.drawRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    /**
     * Getter de l'attribut "etat". Renvoie l’état courant de la cellule (0, 1, 2, 3).
     */
    public int getEtat () {
        return etat;
    }

    /**
     * Setter de l'attribut "etat". Met à jour l’état courant de la cellule.
     *
     * @param etat L'état de la cellule (0, 1, 2, 3)
     */
    public void setEtat(int etat) {
        this.etat = etat;
    }

    /**
     * Getter de l'attribut "etatFutur". Renvoie l'état futur de la cellule.
     */
    public  int getEtatFutur () {
        return etat_futur;
    }

    /**
     * Getter de l'attribut "voisins". Retourne un tableau contenant la liste des voisins de la cellule dans l’ordre suivant :
     * [voisin de gauche, voisin du haut, voisin de droite, voisin du bas]
     */
    public Cellule[] getVoisins () {
        return voisins;
    }

    /**
     * Setter de l'attribut "voisins". Permet d’affecter des voisins à la cellule.
     *
     * @param voisins la liste des voisins de la cellule dans l’ordre suivant : [voisin de gauche, voisin du haut, voisin de droite, voisin du bas].
     */
    public void setVoisins (Cellule[] voisins) {
        this.voisins = voisins;
    }

    /**
     * Méthode abstraite permettant de calculer l’état futur d’une cellule en se basant sur plusieurs critères à savoir :
     * le voisinage, la direction du vent, la saison, l’humidité du sol et le type de végétation.
     *
     * @param direction_vent La direction courante du vent (NORD, SUD, EST, OUEST).
     * @param saison La saison courante (HIVER, PRINTEMPS, ETE, AUTOMNE).
     */
    public abstract void calculeEtatFutur (String direction_vent, String saison);

    /**
     * Méthode qui bascule la cellule de son état courant vers son état futur
     */
    public void basculer() {
        this.etat = this.etat_futur;
    }
}
