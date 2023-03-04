import javax.swing.*;
import java.awt.*;
import java.util.Random;


/**
 * Cette classe est une extension de la classe JPannel de Java. Elle permet d’implémenter la grille sur laquelle se déroulera la simulation.
 */
public class Grille extends JPanel {
    /** Représente le nombre de lignes de la grille */
    private int nb_lignes;
    /** Représente le nombre de colonnes de la grille. */
    private int nb_colonnes;
    /** Tableau à deux dimensions contenant toutes les cellules de la grille. */
    private Cellule[][] cellules;

    /**
     * Constructeur de la classe qui crée une nouvelle instance de Grille.
     */
    public Grille(){
        // Appeler le constructeur de JPannel
        super();
    }

    /**
     * Méthode qui renvoie le nombre de lignes de la grille.
     */
    public int getNbLignes() {
        return nb_lignes;
    }

    /**
     * Méthode qui renvoie le nombre de colonnes de la grille.
     */
    public int getNbColonnes() {
        return nb_colonnes;
    }

    /**
     * Méthode qui permet de modifier le nombre de lignes de la grille.
     *
     * @param nbLignes Le nombre de lignes de la grille.
     */
    public void setNbLignes(int nbLignes) {
        this.nb_lignes = nbLignes;
    }

    /**
     * Méthode qui permet de modifier le nombre de colonnes de la grille.
     *
     * @param nbColonnes Le nombre de colonnes de la grille.
     */
    public void setNbColonnes(int nbColonnes) {
        this.nb_colonnes = nbColonnes;
    }

    /**
     * Méthode qui permet de vérifier si la cellule de coordonnées (i, j) fait partie la grille.
     *
     * @param i La ligne de la cellule.
     * @param j La colonne de la cellule.
     * @return True si la cellule (i, j) est dans la grille et False sinon
     */
    private boolean dansGrille(int i, int j){
        return i < this.nb_lignes && i >= 0 && j < this.nb_colonnes && j >= 0;
    }

    /**
     * Méthode qui permet d’insérer une cellule dans la case (i, j) de la grille.
     *
     * @param i Ligne de la grille où la cellule sera insérée.
     * @param j Colonne de la grille où la cellule sera insérée.
     * @param cellule La cellule à insérer dans la grille.
     */
    public void setCellule(int i, int j, Cellule cellule){
        this.cellules[i][j] = cellule;
        // Ajouter l'arbre dans la grille
        this.add(cellule);
    }

    /**
     * Méthode qui permet de remplacer la cellule dans la case (i, j) de la grille par une autre.
     *
     * @param i Ligne de la grille où se trouve la cellule à remplacer.
     * @param j Colonne de la grille où se trouve la cellule à remplacer.
     * @param cellule La cellule à insérer dans la grille.
     */
    public void remplacerCellule(int i, int j, Cellule cellule){
        // Retirer la cellule actuelle de la grille.
        Cellule celluleActuelle = this.cellules[i][j];
        this.remove(celluleActuelle);
        this.cellules[i][j] = cellule;

        // Ajouter la nouvelle cellule à la bonne position.
        this.add(cellule, i * this.nb_colonnes + j);
        this.revalidate();
        this.repaint();
    }

    /**
     * Méthode qui renvoie la cellule située dans la case (i, j) de la grille.
     *
     * @param i Ligne de la grille d'où la cellule sera récupérée.
     * @param j Colonne de la grille d'où la cellule sera récupérée.
     * @return La cellule située dans la case (i, j) de la grille.
     */
    public Cellule getCellule(int i, int j){
        return this.cellules[i][j];
    }

    /**
     * Renvoie la liste des voisins de la cellule (i, j). Si un des 4 voisins manque, le remplacer par null.
     *
     * @param i Ligne de la cellule dont les voisins doivent être calculés
     * @param j Colonne de la cellule dont les voisins doivent être calculés
     * @return La liste des voisins de la cellule sous la forme : [voisin de gauche, voisin du haut, voisin de droite, voisin du bas]
     */
    private Cellule[] calculeVoisins(int i, int j){
        Cellule[] voisins = new Cellule[4];
        // Voisinage de la cellule [voisin de gauche, voisin du haut, voisin de droite, voisin du bas].
        int[][] voisinages = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        // Indice pour insérer les voisins dans la liste des voisins.
        int indice = 0;
        for(int[] voisinage: voisinages){
            // Calculer la ligne et la colonne du voisin
            int ligne_voisin = i + voisinage[0];
            int colonne_voisin = j + voisinage[1];

            // Si les coordonnées du voisin existent dans la grille, alors insérer la cellule à cette position dans la liste des voisins.
            if(this.dansGrille(ligne_voisin, colonne_voisin)) voisins[indice] = this.getCellule(ligne_voisin, colonne_voisin);
            // Sinon insérer null pour le voisin en question.
            else voisins[indice] = null;

            indice = indice + 1;
        }

        return voisins;
    }

    /**
     * Méthode qui permet d'initialiser de façon aléatoire la grille avec un nombre de colonnes et de lignes, une densité,
     * un pourcentage de cellules humides et un pourcentage d’arbres peu inflammable fournis en paramètre.
     *
     * @param nbLignes Le nombre de lignes de la grille.
     * @param nbColonnes Le nombre de colonnes de la grille.
     * @param densite La densité d'arbres dans la grille.
     * @param tauxCellulesHumides Le pourcentage de cellules humides de la grille.
     * @param tauxArbresPeuInflammables Le pourcentage d'arbres peu inflammables de la grille.
     */
    public void construireGrille (int nbLignes, int nbColonnes, int densite, int tauxCellulesHumides, int tauxArbresPeuInflammables){
        // Initialisation du nombre de lignes et de colonnes de la grille
        this.nb_lignes = nbLignes;
        this.nb_colonnes = nbColonnes;

        // Définir la disposition de la grille
        GridLayout layout = new GridLayout(nbLignes, nbColonnes);
        this.setLayout(layout);

        // Variable aléatoire utilisé pour respecter les proportions (densité, taux cellules humides, taux d'arbres peu inflammables)
        Random random = new Random();

        // Initialisation et remplissage du tableau des cellules
        this.cellules = new Cellule[nbLignes][nbColonnes];

        for(int i = 0; i < nbLignes; i++){
            for(int j = 0; j < nbColonnes; j++){
                // Créer un arbre ou un sol nu en fonction de la densité de la grille
                if(random.nextDouble() <= (double) densite/100){
                    // Créer un arbre, affecter ses voisins et insérer l'arbre dans la grille.
                    Arbre arbre = new Arbre(i, j, this);
                    arbre.setVoisins(this.calculeVoisins(i, j));
                    this.setCellule(i, j, arbre);

                    // Arbre sur sol humide
                    if(random.nextDouble() <= (double) tauxCellulesHumides/100) arbre.setSolHumide(true);

                    // Arbre peu inflammable
                    if(random.nextDouble() <= (double) tauxArbresPeuInflammables/100) arbre.setArbrePeuInflammable(true);
                }
                else{
                    // créer sol nu, affecter ses voisins et l'insérer dans la liste des cellules de la grille.
                    SolNu solNu = new SolNu(i, j, this);
                    solNu.setVoisins(this.calculeVoisins(i, j));
                    this.setCellule(i, j, solNu);
                }
            }
        }
    }
}
