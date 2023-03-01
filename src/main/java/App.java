import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private int min_width ; //largeur minimale de la fenêtre
    private int min_height ; // hauteur minimale de la fenêtre
    private Grille grille ; //c’est la grille qui modélise la forêt
    private Menu menu ; //c’est le panneau contenant le menu de l’application
    private int densite; //densite des arbre dans la forêt en pourcentage
    private String direction_vent ; //indique la direction du vent (NORD, SUD, EST, OUEST)
    private String Saison ; //HIVER,PRINTEMPS,ETE,AUTOMNE
    private int taux_arbres_peu_inflammable ; //taux en pourcentage d’arbre à faible inflammabilité
    private int taux_cellules_humides ; //taux en pourcentage de cellules ayant un sol humide
    private int taux_foret_deja_brulee ; //taux en pourcentage de forêt déjà brulée
    boolean stopSimulation;

    /**
     * Cette classe est une extension (« extend ») de la classe JFrame de Java
     * Elle permet de creer la fenetre et puis d'y ajouter les Panel Menu et Grille
     * Puis de lancer la simulation
     */
    public App (){
        min_width = 800;
        min_height = 750;
        menu = new Menu(this);
        grille = new Grille();
        stopSimulation = false;
        //On recupere les valeurs des variables dans les spinner de Menu
        densite = menu.getDensite();
        taux_cellules_humides = menu.getHumidite();
        taux_arbres_peu_inflammable = menu.getTauxInflammable();
        Saison = menu.getSaison();
        direction_vent = menu.getDirectionVent();

        fenetreApp();
        panelsApp();
    }

    /**
     * methode pour creer la fenetre graphique de l'application
     */
    private void fenetreApp(){
        this.setMinimumSize(new Dimension(min_width, min_height));
        this.setLocation(0, 0);
        this.setTitle("Simulation Feu de Foret");
        ImageIcon img = new ImageIcon("D:\\haddo\\Documents\\info\\JavaProjects\\Simulation_Feu_de_Foret\\design\\icone.png"); //Icone fenetre (corner gauche)
        this.setIconImage(img.getImage());
        this.setAlwaysOnTop(false);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Methode pour creer les Panels et les ajouters dans un seul panel. On ajoutera ce panel a la fenetre.
     */
    private void panelsApp(){
        this.add(grille);
        this.add(menu);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
    }
    protected void genererGrille (int nbLignes, int nbColonnes){

    }
    protected void lancerSimulation(){

    }
    public void setStopSimulation(boolean var){
        stopSimulation = var;
    }
    public int getTaux_foret_deja_brulee() {
        return taux_foret_deja_brulee;
    }
    public static void main(String[] args){
        new App();
    }
}
