import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * Cette classe est une extension de la classe JFrame.
 * Elle permet de créer la fenêtre, d'y ajouter les Panels Menu et Grille, puis de lancer la simulation.
 */
public class App extends JFrame {


    private Grille grille ;

    private final JScrollPane grilleScrollPane;

    private final Menu menu ;

    private int densite;

    private String direction_vent;

    private String saison;

    private int tauxArbresPeuInflammables;

    private int tauxCellulesHumides;

    private double tauxForetDejaBrulee;

    private boolean stopSimulation;

    private boolean simulationEnCours;
    /**
     * Constructeur de la classe App qui crée une nouvelle instance d'App.
     */
    public App (){
        // Initialisation des attributs.
        grilleScrollPane = new JScrollPane();
        this.menu = new Menu(this);

        this.setStopSimulation(false);
        this.setDensite(this.menu.getDensite());
        this.setTauxCellulesHumides(this.menu.getTauxCellulesHumides());
        this.setTauxArbresPeuInflammables(this.menu.getTauxArbresPeuInflammable());
        this.setSaison(this.menu.getSaison());
        this.setDirectionVent(this.menu.getDirectionVent());

        this.genererGrille(this.menu.getLignes(), this.menu.getColonnes());

        // Configuration de la fenêtre et disposition des panneaux de l'application.
        this.configFenetreApp();
        this.addPanelsApp();
    }

    /**
     * Méthode qui permet de configurer la fenêtre de l'application.
     */
    private void configFenetreApp(){
        this.setSize(960, 710);
        this.setMinimumSize(new Dimension(700, 500));
        this.setLocationRelativeTo(null);
        this.setTitle("Simulation Feu de Foret");
        ImageIcon img = new ImageIcon("design/icone.png");
        this.setIconImage(img.getImage());
        this.setAlwaysOnTop(false);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Methode qui permet de disposer le panneau de la grille et celui du menu dans la fenêtre de l'application.
     */
    private void addPanelsApp(){
        // Creation de la grille et du menu
        grilleScrollPane.setViewportView(this.grille);
        grilleScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        grilleScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Personnalisation des barres de défilement de la grille.
        grilleScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        grilleScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        grilleScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        grilleScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        grilleScrollPane.getVerticalScrollBar().setUnitIncrement(10);

        // Ajout de la grille à gauche et du menu à droite de la fenêtre.
        this.add(grilleScrollPane, BorderLayout.CENTER);
        this.add(this.menu, BorderLayout.EAST);
    }

    /**
     * Méthode qui permet de créer une instance de la grille et qui appelle la méthode
     * Grille.construireGrille() pour l’initialiser de façon aléatoire.
     * Cette méthode est appelée après un click de l'utilisateur sur le bouton "Générer".
     *
     * @param nbLignes Le nombre de lignes de la grille à générer.
     * @param nbColonnes Le nombre de colonnes de la grille à générer.
     */
    public void genererGrille (int nbLignes, int nbColonnes){
        Grille grille = new Grille();
        grille.construireGrille(nbLignes, nbColonnes, this.densite, this.tauxCellulesHumides, this.tauxArbresPeuInflammables);
        this.grille = grille;
        grilleScrollPane.setViewportView(this.grille);
        this.revalidate();
        this.repaint();
    }

    /**
     * Méthode qui appelle en boucle Grille.simulation() pour mettre la grille à jour de façon itérative
     * jusqu’à ce qu’il n’y ait plus de changement dans la grille ou que l’utilisateur clique sur le bouton Arrêter.
     */
    public void lancerSimulation(){
        Thread simulationThread = new Thread(new Runnable() {
            public void run() {
                App.this.simulationEnCours = true;
                String direction_vent = App.this.direction_vent;
                String saison = App.this.saison;
                double arbreEnFeuExisteDansGrille = 1;

                while(arbreEnFeuExisteDansGrille == 1 && !App.this.stopSimulation) {
                    // Calcul de l'état futur de toutes les cellules
                    double[] outputSimulation;
                    outputSimulation = App.this.grille.simulation(direction_vent, saison);
                    arbreEnFeuExisteDansGrille = outputSimulation[0];
                    // Actualiser la grille en basculant toutes les cellules vers leur état futur
                    App.this.grille.actualiser();
                    // Mettre à jour le décompte du taux de forêt déjà brulée
                    App.this.tauxForetDejaBrulee = outputSimulation[1];
                    App.this.menu.updateTauxforetbrulee();

                    App.this.revalidate();
                    App.this.repaint();

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                App.this.simulationEnCours = false;
                App.this.stopSimulation = false;
                App.this.menu.activerBoutonGenerer(true);
                App.this.menu.activerBoutonArreter(false);
                App.this.menu.activerBoutonLancer(true);
        }});
        simulationThread.start();
    }

    /**
     * Setter de l'attribut "densite".
     *
     * @param densite La densité en arbre de la forêt.
     */
    public void setDensite(int densite) {
        this.densite = densite;
    }

    /**
     * Setter de l'attribut "saison".
     *
     * @param saison La saison à considérer pour la simulation.
     */
    public void setSaison(String saison) {
        this.saison = saison;
    }

    /**
     * Setter de l'attribut "direction_vent".
     *
     * @param direction_vent La direction du vent à considérer pour la simulation.
     */
    public void setDirectionVent(String direction_vent) {
        this.direction_vent = direction_vent;
    }

    /**
     * Setter de l'attribut "tauxArbresPeuInflammables".
     *
     * @param tauxArbresPeuInflammables Le pourcentage d'arbres à faible inflammabilité que l'on souhaite avoir dans la forêt.
     */
    public void setTauxArbresPeuInflammables(int tauxArbresPeuInflammables) {
        this.tauxArbresPeuInflammables = tauxArbresPeuInflammables;
    }
    /**
     * Methode pour determiner si des cellules de la grille sont en feu dans la grille
     * @return True si au moins un arbre est en feu. Retourne Faux autrement.
     */
    public boolean cellulesEnFeu(){
        return grille.celluleEnFeu();
    }
    /**
     * Setter de l'attribut "tauxCellulesHumides".
     *
     * @param tauxCellulesHumides Le pourcentage d'arbre sur sol humide que l'on souhaite avoir dans la forêt.
     */
    public void setTauxCellulesHumides(int tauxCellulesHumides) {
        this.tauxCellulesHumides = tauxCellulesHumides;
    }

    /**
     * Setter de l'attribut "tauxForetDejaBrulee".
     *
     * @param tauxForetDejaBrulee Le pourcentage d'arbres déjà brulés dans la forêt.
     */
    public void setTauxForetDejaBrulee(double tauxForetDejaBrulee) {
        this.tauxForetDejaBrulee = tauxForetDejaBrulee;
    }

    /**
     * Getter de l'attribut "tauxForetDejaBrulee".
     *
     * @return Le pourcentage d'arbres déjà brulés dans la forêt.
     */
    public double getTauxForetDejaBrulee() {
        return this.tauxForetDejaBrulee;
    }

    /**
     * Setter de l'attribut "stopSimulation".
     *
     * @param stopSimulation Valeur booléenne qui indique s'il faut mettre une pause à la simulation ou pas.
     */
    public void setStopSimulation(boolean stopSimulation){
        this.stopSimulation = stopSimulation;
    }

    /**
     * Getter de l'attribut "simulationEnCours".
     *
     * @return True si une simulation est déjà en cours et false sinon.
     */
    public boolean getSimulationEnCours(){
        return this.simulationEnCours;
    }

    /**
     * Méthode main de l'application. C'est le point d'entré de l'application qui créé une instance de la classe App.
     *
     * @param args Représente les paramètres pouvant être passés à travers le terminal.
     */
    public static void main(String[] args){
        // invokeLater est utilisé pour exécuter le code Swing dans le thread de l'interface utilisateur.
        SwingUtilities.invokeLater(App::new);
    }
}

/**
 * Sous-classe de BasicScrollBarUI pour personnaliser l'apparence des barres de défilement.
 */
class CustomScrollBarUI extends BasicScrollBarUI {
    /** Couleur de la piste de la barre de défilement */
    private Color trackColor = new Color(0, 0, 0, 20);
    /** Couleur du curseur de la barre de défilement */
    private Color thumbColor = new Color(0, 0, 0, 70);
    /** Largeur du curseur de la barre de défilement */
    private int thumbThickness = 6;
    /** Largeur de la piste de la barre de défilement */
    private int trackThickness = 6;

    /**
     * Permet de créer un bouton invisible en tant que bouton decrease de la barre de défilement.
     * */
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createButton();
    }

    /**
     * Permet de créer un bouton invisible en tant que bouton increase de la barre de défilement.
     * */
    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createButton();
    }

    /**
     * Permet de créer un bouton invisible pour la barre de défilement.
     * */
    private JButton createButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }

    /**
     * Dessine la piste de la barre de défilement.
     */
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(trackColor);
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            // Style for vertical scrollbar
            int trackOffset = (trackBounds.width - trackThickness) / 2;
            Rectangle trackRect = new Rectangle(trackBounds.x + trackOffset, trackBounds.y, trackThickness, trackBounds.height);
            g.fillRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height);
        } else {
            // Style for horizontal scrollbar
            int trackOffset = (trackBounds.height - trackThickness) / 2;
            Rectangle trackRect = new Rectangle(trackBounds.x, trackBounds.y + trackOffset, trackBounds.width, trackThickness);
            g.fillRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height);
        }
    }

    /**
     * Dessine le curseur de la barre de défilement.
     */
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(thumbColor);

        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            // Style for vertical scrollbar
            int thumbOffset = (thumbBounds.width - thumbThickness) / 2;
            Rectangle thumbRect = new Rectangle(thumbBounds.x + thumbOffset, thumbBounds.y, thumbThickness, thumbBounds.height);
            g2.fill(thumbRect);
        } else {
            // Style for horizontal scrollbar
            int thumbOffset = (thumbBounds.height - thumbThickness) / 2;
            Rectangle thumbRect = new Rectangle(thumbBounds.x, thumbBounds.y + thumbOffset, thumbBounds.width, thumbThickness);
            g2.fill(thumbRect);
        }

        g2.dispose();
    }
}
