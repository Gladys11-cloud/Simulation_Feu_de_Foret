import java.util.Random;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Cette classe est une extension de la classe Cellule. Elle permet de modéliser un arbre.
 */
public class Arbre extends Cellule {

    /** Représente la couleur de la cellule si c’est un arbre vivant. */
    public static final String COULEUR_ARBRE_VIVANT = "#B6C772";
    /** Représente la couleur de la cellule si c’est un arbre en feu. */
    public static final String COULEUR_ARBRE_EN_FEU = "#F3770F";
    /** Représente la couleur de la cellule si c’est un arbre en cendre. */
    public static final String COULEUR_ARBRE_EN_CENDRE = "#D4D4D4";
    /** Représente la couleur de l’arbre lorsque celui-ci est placé sur un sol humide. */
    public static final String COULEUR_SOL_HUMIDE = "#7F8F57";
    /** Représente la couleur des bordures de la cellule lorsque celle-ci contient un arbre peu inflammable. */
    public static final String COULEUR_PEU_INFLAMMABLE = "#0474BC";
    /** Représente la couleur de fond des items actifs du menu pop up qui apparait après un click droit sur une cellule. */
    public static final String COULEUR_ITEM_POP_UP_MENU_ACTIF = "#A3B8CC";
    /** Indique si le sol de la cellule est humide ou pas. */
    private boolean sol_humide;
    /** Indique si l’arbre est peu inflammable ou pas. */
    private boolean arbre_peu_inflammable;

    /**
     * Constructeur de la classe qui crée une nouvelle instance de la classe Arbre avec les paramètres donnés.
     *
     * @param x      Le numéro de ligne ou se trouve la cellule dans la grille.
     * @param y      Le numéro de colonne ou se trouve la cellule dans la grille.
     * @param grille La référence de l'objet grille dans lequel se trouve la cellule.
     */
    public Arbre(int x, int y, Grille grille, Boolean sol_humide, Boolean arbre_peu_inflammable){
        super(x, y, grille);
        // L'arbre est vivant lors de sa création
        this.etat = 1;
        this.setBackground(Color.decode(COULEUR_ARBRE_VIVANT));

        this.setSolHumide(sol_humide);
        this.setArbrePeuInflammable(arbre_peu_inflammable);

        // Définir l'action à effectuer après un click gauche ou droit de la souris.
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                // Click gauche
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (Arbre.this.etat == 1) Arbre.this.setEtat(2);
                    else if (Arbre.this.etat == 2) Arbre.this.setEtat(3);
                    else if (Arbre.this.etat == 3) {
                        // Créer un nouveau sol nu pour remplacer l'arbre en cendre.
                        SolNu solNu = new SolNu(Arbre.this.x, Arbre.this.y, Arbre.this.grille);
                        solNu.setVoisins(Arbre.this.voisins);
                        Arbre.this.grille.remplacerCellule(Arbre.this.x, Arbre.this.y, solNu);
                    }
                }

                // Click droit
                else if (e.getButton() == MouseEvent.BUTTON3 && Arbre.this.etat == 1) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem solHumide, arbrePeuInflammable;

                    if(Arbre.this.sol_humide) {
                        solHumide = new JMenuItem("-> Sol humide");
                        solHumide.setBackground(Color.decode(COULEUR_ITEM_POP_UP_MENU_ACTIF));
                    }
                    else solHumide = new JMenuItem("  Sol humide");

                    // Action à effectuer lors du clic sur l'item sol humide
                    solHumide.addActionListener(e1 -> setSolHumide(!Arbre.this.sol_humide));

                    if(Arbre.this.arbre_peu_inflammable){
                        arbrePeuInflammable = new JMenuItem("-> Arbre peu inflammable");
                        arbrePeuInflammable.setBackground(Color.decode(COULEUR_ITEM_POP_UP_MENU_ACTIF));
                    }
                    else arbrePeuInflammable = new JMenuItem("  Arbre peu inflammable");

                    // Action à effectuer lors du clic sur l'item arbre peu inflammable
                    arbrePeuInflammable.addActionListener( e12 -> setArbrePeuInflammable(!Arbre.this.arbre_peu_inflammable));

                    popupMenu.add(solHumide);
                    popupMenu.add(arbrePeuInflammable);

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * Setter de l'attribut "etat". Met à jour l’état courant de la cellule.
     * Modifie également la couleur de la cellule en fonction de l'état qu'on veut lui attribuer.
     *
     * @param etat L'état de la cellule (0, 1, 2, 3)
     */
    @Override
    public void setEtat(int etat) {
        super.setEtat(etat);

        // Si l'arbre n'est pas vivant alors il n'est forcément ni sur un sol humide ni peu inflammable
        if(this.etat != 1){
            this.setSolHumide(false);
            this.setArbrePeuInflammable(false);
        }

        // Modifier la couleur de la cellule en fonction de son état futur.
        if(this.etat == 2) this.setBackground(Color.decode(COULEUR_ARBRE_EN_FEU));
        if(this.etat == 3) this.setBackground(Color.decode(COULEUR_ARBRE_EN_CENDRE));
    }

    /**
     * Getter de l’attribut sol_humide.
     *
     * @return true si le sol de l'arbre est humide et false sinon.
     */
    public boolean solEstHumide(){
        return this.sol_humide;
    }

    /**
     * Setter de l’attribut sol_humide. Il change aussi la couleur de l'arbre.
     *
     * @param sol_humide Valeur booléenne qui indique si le sol de l'arbre est humide ou pas.
     */
    public void setSolHumide(boolean sol_humide){
        if(!sol_humide){
            this.sol_humide = false;
            if(this.etat == 1) this.setBackground(Color.decode(COULEUR_ARBRE_VIVANT));
        }
        else if(this.etat == 1) {
            this.sol_humide = true;
            this.setBackground(Color.decode(COULEUR_SOL_HUMIDE));
        }
    }

    /**
     * Getter de l’attribut arbre_peu_inflammable.
     *
     * @return true si l'arbre est peu inflammable et false sinon.
     */
    public boolean arbreEstPeuInflammable(){
        return this.arbre_peu_inflammable;
    }

    /**
     * Setter de l’attribut arbre_peu_inflammable. Il change aussi la couleur des bordures de l'arbre.
     *
     * @param arbre_peu_inflammable Valeur booléenne qui indique si le l'arbre est peu inflammable ou pas.
     */
    public void setArbrePeuInflammable(boolean arbre_peu_inflammable){
        if(!arbre_peu_inflammable){
            this.arbre_peu_inflammable = false;
            this.setBorder(null);
        }
        else if(this.etat == 1){
            this.arbre_peu_inflammable = true;
            this.setBorder(BorderFactory.createLineBorder(Color.decode(COULEUR_PEU_INFLAMMABLE), 1));
        }
    }

    /**
     * Méthode abstraite permettant de calculer l’état futur d’une cellule en se basant sur plusieurs critères à savoir :
     * le voisinage, la direction du vent, la saison, l’humidité du sol et le type de végétation.
     *
     * @param direction_vent La direction courante du vent (NORD, SUD, EST, OUEST).
     * @param saison La saison courante (HIVER, PRINTEMPS, ETE, AUTOMNE).
     */
    @Override
    public void calculeEtatFutur(String direction_vent, String saison){
        // p : probabilité de propagation du feu vers la cellule
        double p;

        if(this.etat == 3) this.etat_futur = etat;
        else if(this.etat == 2) this.etat_futur = 3;
        else if(this.etat == 1){
            if(this.nombreDeVoisinsEnFeu() == 0) this.etat_futur = this.etat;
            else{
                p = this.calculeP(direction_vent, saison);
                if(this.feuVaSePropager(p)) this.etat_futur = 2;
                else this.etat_futur = etat;
            }
        }
    }

    /**
     * Méthode qui bascule la cellule de son état courant vers son état futur et qui modifie en conséquence la couleur de la cellule.
     */
    public void basculer(){
        super.basculer();

        // Si l'arbre n'est pas vivant alors il n'est forcément ni sur un sol humide ni peu inflammable
        if(this.etat != 1){
            this.setSolHumide(false);
            this.setArbrePeuInflammable(false);
        }

        // Modifier la couleur de la cellule en fonction de son état futur.
        if(this.etat == 2) this.setBackground(Color.decode(COULEUR_ARBRE_EN_FEU));
        if(this.etat == 3) this.setBackground(Color.decode(COULEUR_ARBRE_EN_CENDRE));
    }

    /**
     * Méthode qui calcule p, la probabilité que l'arbre prenne feu.
     *
     * @param direction_vent La direction courante du vent (NORD, SUD, EST, OUEST).
     * @param saison La saison courante (HIVER, PRINTEMPS, ETE, AUTOMNE).
     */
    private double calculeP(String direction_vent, String saison){
        // Impact du voisinage sur p.
        double p = 0.8 + this.nombreDeVoisinsEnFeu() * 0.05;

        // Impact de la direction du vent sur p.
        if(!this.feuVersCellule(direction_vent) && direction_vent != null) p -= 0.1;
        if(this.feuVersCellule(direction_vent)) p += 0.5;

        // Impact de la saison sur p
        if(saison.equals("HIVER")) p -= 0.1;
        else if(saison.equals("ETE")) p += 0.1;

        // Impact de l'humidité du sol sur p.
        if(this.solEstHumide()) p -= 0.1;

        // Impact du type de végétation sur p.
        if(this.arbreEstPeuInflammable()) p -= 0.1;

        // La probabilité doit être comprise en 0 et 1.
        if(p < 0) p = 0;
        if(p > 1) p = 1;

        return p;
    }

    /**
     * Méthode qui permet d'effectuer la realisation de la probabilité p.
     *
     * @param p La probabilité que l'arbre prenne feu.
     * @return true si l'arbre va prendre feu et false sinon.
     */
    private boolean feuVaSePropager(double p){
        Random rand= new Random();
        double randomNum = rand.nextDouble();
        return randomNum <= p;
    }

    /**
     * Méthode qui permet de déterminer si le vent pousse le feu vers l'arbre ou pas.
     *
     * @param direction_vent La direction du vent (NORD, SUD, EST, OUEST).
     * @return true si le vent pousse le feu vers l'arbre et false sinon.
     */
    private boolean feuVersCellule(String direction_vent){
        if (voisins[0] != null) {
            if(voisins[0].etat == 2 && direction_vent.equals("EST")) return true;
        }
        if (voisins[1] != null) {
            if(voisins[1].etat == 2 && direction_vent.equals("SUD")) return true;
        }
        if (voisins[2] != null) {
            if(voisins[2].etat == 2 && direction_vent.equals("OUEST")) return true;
        }
        if (voisins[3] != null) {
            if (voisins[3].etat == 2 && direction_vent.equals("NORD")) return true;
        }
        return false;
    }

    /**
     * Méthode que permet de déterminer le nombre de voisins en feu de l'arbre.
     *
     * @return Le nombre de voisins en feu de l'arbre.
     */
    private int nombreDeVoisinsEnFeu(){
        int compteur = 0;
        for(Cellule voisin : voisins){
            try {
                if (voisin.getEtat() == 2) compteur++;
            } catch (NullPointerException ignored){}
        }
        return compteur;
    }
}