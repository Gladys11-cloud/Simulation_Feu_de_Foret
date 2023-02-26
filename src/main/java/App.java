import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    int min_width ; //largeur minimale de la fenêtre
    int min_height ; // hauteur minimale de la fenêtre
    String title ; //titre de la fenêtre
    Grille grille ; //c’est la grille qui modélise la forêt
    Menu menu ; //c’est le panneau contenant le menu de l’application
    int densite; //densite des arbre dans la forêt en pourcentage
    String direction_vent ; //indique la direction du vent (NORD, SUD, EST, OUEST)
    String Saison ; //HIVER,PRINTEMPS,ETE,AUTOMNE
    int taux_arbres_peu_inflammable ; //taux en pourcentage d’arbre à faible inflammabilité
    int taux_cellules_humides ; //taux en pourcentage de cellules ayant un sol humide
    int taux_foret_deja_brulee ; //taux en pourcentage de forêt déjà brulée


    App (){
        fenetreApp();
    }

    /**
     * methode pour creer la fenetre graphique de l'application
     */
    void fenetreApp(){
        this.setMinimumSize(new Dimension(500, 300));
        this.setLocation(0, 0);
        this.setTitle("Simulation Feu de Foret");
        ImageIcon img = new ImageIcon("D:\\haddo\\Documents\\info\\JavaProjects\\Simulation_Feu_de_Foret\\design\\icone.png"); //Icone fenetre (corner gauche)
        this.setIconImage(img.getImage());
        this.setAlwaysOnTop(false);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args){
        new App();
    }
}
