import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Cette classe est une extension de JPanel. Elle contient tous les composants graphiques
 * (Boutons, etc.) permettant de configurer la grille et de lancer la simulation.
 */
public class Menu extends JPanel {
    /** Contient la référence de l’instance d'App dans laquelle se trouve le menu. */
    private final App app;
    /** Couleur en hexadecimal du bouton « Générer » lorsqu'il est actif. */
    private final static String COULEUR_BOUTON_GENERER_ACTIF = "#007FFF";
    /** Couleur en hexadecimal du bouton « Générer » lorsqu'il n'est pas actif. */
    private final static String COULEUR_BOUTON_GENERER_NON_ACTIF = "#7AB9FA";
    /** Couleur en hexadecimal du bouton « Lancer » lorsqu'il est actif. */
    private final static String COULEUR_BOUTON_LANCER_ACTIF = "#1DA15D";
    /** Couleur en hexadecimal du bouton « Lancer » lorsqu'il n'est pas actif. */
    private final static String COULEUR_BOUTON_LANCER_NON_ACTIF = "#88CAA8";
    /** Couleur en hexadecimal du bouton « Arrêter » lorsqu'il est actif. */
    private final static String COULEUR_BOUTON_ARRETER_ACTIF = "#FC1C2C";
    /** Couleur en hexadecimal du bouton « Arrêter » lorsqu'il n'est pas actif. */
    private final static String COULEUR_BOUTON_ARRETER_NON_ACTIF = "#F88890";
    /** Couleur en hexadecimal des champs de texte qui permettent à l’utilisateur de faire des choix (JSpinner). */
    private final static String COULEUR_BACKGROUND_CHAMP_DE_TEXTE = "#EEECEC";
    /** Couleur en hexadecimal des champs de texte qui permettent à l’utilisateur de faire des choix (JSpinner). */
    private final static String COULEUR_CHAMP_DE_TEXTE = "#606060";
    /** Couleur en hexadecimal des lignes de séparation. */
    private final static String COULEUR_SEPARATIONS = "#D9D9D9";
    /** Couleur en hexadecimal d’affichage du pourcentage de forêt brulée. */
    private final static String COULEUR_POURC_FORET_BRULEE = "#F3770F";
    /** Couleur de fond du menu */
    private final static String COULEUR_BACKGROUND_MENU = "#F5F5F5";
    /** Couleur du texte des boutons */
    private final static String COULEUR_TEXTE_BOUTONS = "#FFFFFF";
    /** Police utilisée pour les titres dans le menu. */
    private final static Font policeTitres = new Font("Arial", Font.PLAIN, 15);
    /** Police utilisée dans les champs de texte et la légende. */
    private final static Font policeSpinnerEtLegende = new Font("Verdana", Font.PLAIN, 10);
    /** Police utilisée pour le texte des boutons. */
    private final static Font policeButton = new Font("Verdana", Font.PLAIN, 15);
    /** Police utilisée pour le taux de forêt déjà brulée. */
    private final static Font policeTauxForetDejaBrulee = new Font("Verdana", Font.BOLD, 12);
    /** Panneau utilisé dans le menu pour configurer et lancer la simulation. */
    private final JPanel northAreaMenu;
    /** Panneau utilisé dans le menu pour contenir le taux de forêt déjà brulée et la légende de la grille. */
    private final JPanel southAreaMenu;
    /** Champs de texte permettant à l'utilisateur de configurer le nombre de colonnes de la grille. */
    private JSpinner colonnesSpinner;
    /** Champs de texte permettant à l'utilisateur de configurer le nombre de lignes de la grille. */
    private JSpinner lignesSpinner;
    /** Champs de texte permettant à l'utilisateur de configurer la densité en arbres de la grille. */
    private JSpinner densiteSpinner;
    /** Champs de texte permettant à l'utilisateur de configurer la quantité d'arbres sur sol humide de la grille. */
    private JSpinner tauxHumiditeSpinner;
    /** Champs de texte permettant à l'utilisateur de configurer la quantité d'arbres à faible inflammabilité de la grille. */
    private JSpinner tauxArbresPeuInflammableSpinner;
    /** Bouton qui permet de générer une nouvelle grille en prenant en compte les paramètres saisis par l'utilisateur. */
    private JButton buttonGenerer;
    /** Champs de texte permettant à l'utilisateur de choisir la direction du vent à considérer lors de la simulation. */
    private JSpinner directionVentSpinner;
    /** Champs de texte permettant à l'utilisateur de choisir la saison à considérer lors de la simulation. */
    private JSpinner saisonSpinner;
    /** Bouton qui permet de lancer la simulation du feu de forêt. */
    private JButton buttonLancer;
    /** Bouton qui permet d'arrêter ou mettre en pause la simulation du feu de forêt. */
    private JButton buttonArreter;
    JLabel tauxTitre;

    /**
     * Constructeur de la classe Menu qui crée une nouvelle instance de Menu
     *
     * @param app La référence de l’instance d'App dans laquelle se trouve le menu.
     */
    public Menu(App app) {
        this.setLayout(new BorderLayout(10, 0));
        this.app = app;
        this.setBackground(Color.decode(COULEUR_BACKGROUND_MENU));
        this.setPreferredSize(new Dimension(250, app.getHeight()));
        this.tauxTitre = new JLabel();
        // Seul la bordure gauche du menu est visible
        this.setBorder(new MatteBorder(0, 1, 0, 0, Color.decode(COULEUR_SEPARATIONS)));

        // Configuration des conteneurs du menu
        northAreaMenu = new JPanel();
        northAreaMenu.setOpaque(false);
        northAreaMenu.setPreferredSize(new Dimension(this.getWidth(), 450));
        JScrollPane northAreaMenuScrollPane = new JScrollPane(northAreaMenu);
        northAreaMenuScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        northAreaMenuScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        northAreaMenuScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        northAreaMenuScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        northAreaMenuScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        northAreaMenuScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        northAreaMenuScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        northAreaMenuScrollPane.setOpaque(false);
        northAreaMenuScrollPane.getViewport().setOpaque(false);

        southAreaMenu = new JPanel();
        southAreaMenu.setOpaque(false);

        // Ajout des composantes du menu
        partieConfig();
        partieFacteursExternes();
        partieSimuler();
        partieTaux();
        partieLegende();

        // Ajout des conteneurs du menu
        this.add(northAreaMenuScrollPane, BorderLayout.CENTER);
        this.add(southAreaMenu, BorderLayout.SOUTH);
    }

    /**
     * methode qui gere la creation de la partie configuration du menu
     */
    private void partieConfig() {
        JPanel configPanel = new JPanel(new BorderLayout(0,6));
        configPanel.setOpaque(false);

        // Titre de la partie configurer la forêt
        JLabel configTitre = new JLabel("Configurer la foret");
        configTitre.setFont(policeTitres);
        configTitre.setPreferredSize(new Dimension(220, 25));
        configTitre.setHorizontalAlignment(JLabel.LEFT);
        configTitre.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(COULEUR_SEPARATIONS)));
        configPanel.add(configTitre, BorderLayout.NORTH);

        // Spinners pour récupérer des valeurs à l'utilisateur
        JPanel spinnersPanel = new JPanel(new GridLayout(4, 1,0,3));
        spinnersPanel.setOpaque(false);

        // Lignes et colonnes
        this.lignesSpinner = new JSpinner(new SpinnerNumberModel(50, 1, 100, 1));
        ((JSpinner.DefaultEditor) lignesSpinner.getEditor()).getTextField().setFormatterFactory(new MyIntegerFormatterFactory("", " lignes", 1, 100));
        this.colonnesSpinner = new JSpinner(new SpinnerNumberModel(50, 1, 100, 1));
        ((JSpinner.DefaultEditor) colonnesSpinner.getEditor()).getTextField().setFormatterFactory(new MyIntegerFormatterFactory("", " colonnes", 1, 100));

        JPanel lignesColonnesPanel = new JPanel(new GridLayout(1, 2, 4, 0));
        lignesColonnesPanel.setOpaque(false);
        lignesColonnesPanel.add(this.lignesSpinner);
        lignesColonnesPanel.add(this.colonnesSpinner);
        spinnersPanel.add(lignesColonnesPanel);

        // Densité de la forêt
        this.densiteSpinner = new JSpinner(new SpinnerNumberModel(95, 0, 100, 1));
        ((JSpinner.DefaultEditor) densiteSpinner.getEditor()).getTextField().setFormatterFactory(new MyIntegerFormatterFactory("Densite de la foret: "," %", 0, 100));
        spinnersPanel.add(densiteSpinner);

        // Taux d'arbres sur sol humide
        this.tauxHumiditeSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 100, 1));
        ((JSpinner.DefaultEditor) tauxHumiditeSpinner.getEditor()).getTextField().setFormatterFactory(new MyIntegerFormatterFactory("Arbres sur sol humide: "," %", 0, 100));
        spinnersPanel.add(tauxHumiditeSpinner);

        // Taux d'arbres peu inflammable
        this.tauxArbresPeuInflammableSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 100, 1));
        ((JSpinner.DefaultEditor) tauxArbresPeuInflammableSpinner.getEditor()).getTextField().setFormatterFactory(new MyIntegerFormatterFactory("Arbres peu inflammables: "," %", 0, 100));
        spinnersPanel.add(tauxArbresPeuInflammableSpinner);

        // Configuration des spinners
        for (JSpinner spinner : new JSpinner[]{lignesSpinner, colonnesSpinner, densiteSpinner, tauxHumiditeSpinner, tauxArbresPeuInflammableSpinner}) {
            if (spinner != lignesSpinner && spinner != colonnesSpinner)
                spinner.setPreferredSize(new Dimension(220, 30));

            // Centrer le texte
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) spinner.getEditor();
            spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);

            // Couleur de fond et police de caractères
            spinner.setBackground(Color.decode(COULEUR_BACKGROUND_CHAMP_DE_TEXTE));
            spinnerEditor.getTextField().setBackground(Color.decode(COULEUR_BACKGROUND_CHAMP_DE_TEXTE));
            spinnerEditor.getTextField().setForeground(Color.decode(COULEUR_CHAMP_DE_TEXTE));
            spinnerEditor.getTextField().setFont(policeSpinnerEtLegende);

            // Aucune bordure
            spinner.setBorder(BorderFactory.createEmptyBorder());

            // Ajout du listener pour le scroll de la souris
            spinner.addMouseWheelListener(e -> {
                if (e.getWheelRotation() < 0) {
                    if (spinner.getModel().getPreviousValue() != null) spinner.setValue(spinner.getModel().getPreviousValue());
                } else {
                    if (spinner.getModel().getNextValue() != null) spinner.setValue(spinner.getModel().getNextValue());
                }
            });

            // Ajout du listener pour le focus
            spinnerEditor.getTextField().addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    spinner.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(COULEUR_BOUTON_GENERER_ACTIF)));
                    spinnerEditor.getTextField().setForeground(Color.decode(COULEUR_CHAMP_DE_TEXTE).darker().darker().darker());
                }

                @Override
                public void focusLost(FocusEvent e) {
                    spinner.setBorder(BorderFactory.createEmptyBorder());
                    spinnerEditor.setBorder(BorderFactory.createEmptyBorder());
                    spinnerEditor.getTextField().setForeground(Color.decode(COULEUR_CHAMP_DE_TEXTE));
                }
            });

            // Récupération des boutons individuels du JSpinner
            Component[] components = spinner.getComponents();
            for (Component component : components) {
                if (component instanceof JButton button) {
                    // Bordure et couleur de fond.
                    button.setBorder(BorderFactory.createLineBorder(Color.decode(COULEUR_BACKGROUND_CHAMP_DE_TEXTE), 1));
                    button.setBackground(Color.decode(COULEUR_BACKGROUND_CHAMP_DE_TEXTE));
                }
            }
        }
        configPanel.add(spinnersPanel, BorderLayout.CENTER);

        // Bouton "Générer"
        this.buttonGenerer = new JButton("Generer");
        this.buttonGenerer.setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor() {
                return Color.decode(COULEUR_TEXTE_BOUTONS);
            }
        });
        buttonGenerer.setBackground(Color.decode(COULEUR_BOUTON_GENERER_ACTIF));
        buttonGenerer.setForeground(Color.decode(COULEUR_TEXTE_BOUTONS));
        buttonGenerer.setFont(policeButton);
        buttonGenerer.setPreferredSize(new Dimension(220, 35));
        buttonGenerer.setFocusPainted(false);
        buttonGenerer.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        buttonGenerer.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                // Click gauche
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if(buttonGenerer.isEnabled()) {
                        app.setDensite(Menu.this.getDensite());
                        app.setTauxCellulesHumides(Menu.this.getTauxCellulesHumides());
                        app.setTauxArbresPeuInflammables(Menu.this.getTauxArbresPeuInflammable());
                        app.genererGrille(Menu.this.getLignes(), Menu.this.getColonnes());
                        app.setTauxForetDejaBrulee(0);
                        Menu.this.updateTauxforetbrulee();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(buttonGenerer.isEnabled()) buttonGenerer.setBackground(Color.decode(COULEUR_BOUTON_GENERER_ACTIF).darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(buttonGenerer.isEnabled()) buttonGenerer.setBackground(Color.decode(COULEUR_BOUTON_GENERER_ACTIF));
            }
        });
        configPanel.add(buttonGenerer, BorderLayout.SOUTH);

        northAreaMenu.add(configPanel);
    }

    /**
     * methode qui gere la mise en place de la partie Facteurs Externes du menu
     */
    private void partieFacteursExternes(){
        JPanel facteursPanel = new JPanel(new BorderLayout(0,6));
        facteursPanel.setOpaque(false);

        // Titre de la partie facteurs externes
        JLabel facteursTitre = new JLabel("Facteurs externes");
        facteursTitre.setFont(policeTitres);
        facteursTitre.setPreferredSize(new Dimension(220, 25));
        facteursTitre.setHorizontalAlignment(JLabel.LEFT);
        facteursTitre.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(COULEUR_SEPARATIONS)));
        facteursPanel.add(facteursTitre, BorderLayout.NORTH);

        // Spinners pour récupérer des valeurs à l'utilisateur
        JPanel spinnersPanel = new JPanel(new GridLayout(2, 1,0,3));
        spinnersPanel.setOpaque(false);

        // Direction du vent
        String[] directions = new String[] {"Direction du vent: INDIFFERENT", "Direction du vent: WEST", "Direction du vent: NORD",
                "Direction du vent: EST", "Direction du vent: SUD"};
        this.directionVentSpinner = new JSpinner(new SpinnerListModel(directions));
        spinnersPanel.add(directionVentSpinner);

        // Saison
        String[] saisons = new String[] {"Saison: INDIFFERENT", "Saison: PRINTEMPS", "Saison: ETE", "Saison: AUTOMNE", "Saison: HIVER"};
        this.saisonSpinner = new JSpinner(new SpinnerListModel(saisons));
        spinnersPanel.add(saisonSpinner);

        // Configuration des spinners
        for (JSpinner spinner : new JSpinner[]{directionVentSpinner, saisonSpinner}) {
            spinner.setPreferredSize(new Dimension(220, 30));

            // Centrer le texte
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) spinner.getEditor();
            spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
            spinnerEditor.getTextField().setEditable(false);

            // Couleur de fond et police de caractères
            spinner.setBackground(Color.decode(COULEUR_BACKGROUND_CHAMP_DE_TEXTE));
            spinnerEditor.getTextField().setBackground(Color.decode(COULEUR_BACKGROUND_CHAMP_DE_TEXTE));
            spinnerEditor.getTextField().setForeground(Color.decode(COULEUR_CHAMP_DE_TEXTE));
            spinnerEditor.getTextField().setFont(policeSpinnerEtLegende);

            // Aucune bordure
            spinner.setBorder(BorderFactory.createEmptyBorder());

            // Ajout du listener pour le scroll de la souris
            spinner.addMouseWheelListener(e -> {
                if (e.getWheelRotation() < 0) {
                    if (spinner.getModel().getPreviousValue() != null) spinner.setValue(spinner.getModel().getPreviousValue());
                } else {
                    if (spinner.getModel().getNextValue() != null) spinner.setValue(spinner.getModel().getNextValue());
                }
            });

            // Ajout du listener pour le focus
            spinnerEditor.getTextField().addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    spinner.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(COULEUR_BOUTON_GENERER_ACTIF)));
                    spinnerEditor.getTextField().setForeground(Color.decode(COULEUR_CHAMP_DE_TEXTE).darker().darker().darker());
                }

                @Override
                public void focusLost(FocusEvent e) {
                    spinner.setBorder(BorderFactory.createEmptyBorder());
                    spinnerEditor.setBorder(BorderFactory.createEmptyBorder());
                    spinnerEditor.getTextField().setForeground(Color.decode(COULEUR_CHAMP_DE_TEXTE));
                }
            });

            // Récupération des boutons individuels du JSpinner
            Component[] components = spinner.getComponents();
            for (Component component : components) {
                if (component instanceof JButton button) {
                    // Bordure et couleur de fond.
                    button.setBorder(BorderFactory.createLineBorder(Color.decode(COULEUR_BACKGROUND_CHAMP_DE_TEXTE), 1));
                    button.setBackground(Color.decode(COULEUR_BACKGROUND_CHAMP_DE_TEXTE));
                }
            }
        }
        facteursPanel.add(spinnersPanel, BorderLayout.CENTER);

        // Adding vertical space before adding the new menu section
        JPanel verticalSpace = new JPanel();
        verticalSpace.setPreferredSize(new Dimension(220, 10));
        verticalSpace.setOpaque(false);
        northAreaMenu.add(verticalSpace);

        northAreaMenu.add(facteursPanel);
    }

    /**
     * methode qui gere la mise en place de la partie Simuler du menu
     */
    private void partieSimuler(){
        JPanel simulerPanel = new JPanel(new BorderLayout(0,6));
        simulerPanel.setOpaque(false);

        // Titre de la partie facteurs externes
        JLabel simulerTitre = new JLabel("Simuler");
        simulerTitre.setFont(policeTitres);
        simulerTitre.setPreferredSize(new Dimension(220, 25));
        simulerTitre.setHorizontalAlignment(JLabel.LEFT);
        simulerTitre.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(COULEUR_SEPARATIONS)));
        simulerPanel.add(simulerTitre, BorderLayout.NORTH);

        // Boutons pour lancer et stopper la simulation
        JPanel boutonsPanel = new JPanel(new GridLayout(1, 2,5,0));
        boutonsPanel.setOpaque(false);

        // Bouton "Lancer"
        this.buttonLancer = new JButton("Lancer");
        this.buttonLancer.setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor() {
                return Color.decode(COULEUR_TEXTE_BOUTONS);
            }
        });
        buttonLancer.setBackground(Color.decode(COULEUR_BOUTON_LANCER_ACTIF));
        buttonLancer.setForeground(Color.decode(COULEUR_TEXTE_BOUTONS));
        buttonLancer.setFont(policeButton);
        buttonLancer.setPreferredSize(new Dimension(buttonLancer.getWidth(), 35));
        buttonLancer.setFocusPainted(false);
        buttonLancer.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        buttonLancer.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                // Click gauche
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (!app.getSimulationEnCours()) {
                        app.setDirectionVent(Menu.this.getDirectionVent());
                        app.setSaison(Menu.this.getSaison());
                        activerBoutonGenerer(false);
                        activerBoutonLancer(false);
                        activerBoutonArreter(true);
                        app.lancerSimulation();
                    }
                    else
                        activerBoutonLancer(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(buttonLancer.isEnabled()) buttonLancer.setBackground(Color.decode(COULEUR_BOUTON_LANCER_ACTIF).darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(buttonLancer.isEnabled()) buttonLancer.setBackground(Color.decode(COULEUR_BOUTON_LANCER_ACTIF));
            }
        });
        boutonsPanel.add(buttonLancer);

        // Bouton "Arrêter"
        this.buttonArreter = new JButton("Arreter");
        this.buttonArreter.setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor() {
                return Color.decode(COULEUR_TEXTE_BOUTONS);
            }
        });
        this.activerBoutonArreter(false);
        buttonArreter.setBackground(Color.decode(COULEUR_BOUTON_ARRETER_NON_ACTIF));
        buttonArreter.setForeground(Color.decode(COULEUR_TEXTE_BOUTONS));
        buttonArreter.setFont(policeButton);
        buttonArreter.setPreferredSize(new Dimension(buttonArreter.getWidth(), 35));
        buttonArreter.setFocusPainted(false);
        buttonArreter.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        buttonArreter.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                // Click gauche
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if(app.getSimulationEnCours()) {
                        app.setStopSimulation(true);
                        activerBoutonGenerer(true);
                        activerBoutonLancer(true);
                        activerBoutonArreter(false);
                    }
                    else
                        activerBoutonArreter(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (buttonArreter.isEnabled()) buttonArreter.setBackground(Color.decode(COULEUR_BOUTON_ARRETER_ACTIF).darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (buttonArreter.isEnabled()) buttonArreter.setBackground(Color.decode(COULEUR_BOUTON_ARRETER_ACTIF));
            }
        });
        boutonsPanel.add(buttonArreter);

        simulerPanel.add(boutonsPanel, BorderLayout.CENTER);

        // Adding vertical space before adding the new menu section
        JPanel verticalSpace = new JPanel();
        verticalSpace.setPreferredSize(new Dimension(220, 9));
        verticalSpace.setOpaque(false);
        northAreaMenu.add(verticalSpace);

        northAreaMenu.add(simulerPanel);
    }

    /**
     * methode qui gere la mise en place de la partie pourcentage de forêt déjà brulée
     */
    private void partieTaux(){
        tauxTitre.setText(app.getTauxForetDejaBrulee() + " %");
        tauxTitre.setFont(policeTauxForetDejaBrulee);
        tauxTitre.setPreferredSize(new Dimension(250, 30));
        tauxTitre.setHorizontalAlignment(JLabel.CENTER);
        tauxTitre.setForeground(Color.decode(COULEUR_POURC_FORET_BRULEE));
        tauxTitre.setBorder(new MatteBorder(1, 0, 1, 0, Color.decode(COULEUR_SEPARATIONS)));

        southAreaMenu.add(tauxTitre);
    }

    /**
     * methode qui gère la mise en place de la partie légende
     */
    private void partieLegende(){
        JPanel legendePanel = new JPanel(new GridLayout(8, 1,2,4));
        legendePanel.setOpaque(false);

        // Sol nu
        JPanel solNuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,3));
        solNuPanel.setPreferredSize(new Dimension(220, 20));
        solNuPanel.setOpaque(false);
        JLabel solNuCouleur = new JLabel();
        solNuCouleur.setBackground(Color.decode(SolNu.COULEUR));
        solNuCouleur.setOpaque(true);
        solNuCouleur.setPreferredSize(new Dimension(14, 14));
        solNuPanel.add(solNuCouleur);
        JLabel solNu = new JLabel("  Sol nu");
        solNu.setFont(policeSpinnerEtLegende);
        solNu.setHorizontalAlignment(JLabel.LEFT);
        solNuPanel.add(solNu);
        legendePanel.add(solNuPanel);

        // Arbre en vie
        JPanel arbreViePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3));
        arbreViePanel.setPreferredSize(new Dimension(220, 20));
        arbreViePanel.setOpaque(false);
        JLabel arbreVieCouleur = new JLabel();
        arbreVieCouleur.setBackground(Color.decode(Arbre.COULEUR_ARBRE_VIVANT));
        arbreVieCouleur.setOpaque(true);
        arbreVieCouleur.setPreferredSize(new Dimension(14, 14));
        arbreViePanel.add(arbreVieCouleur);
        JLabel arbreVie = new JLabel("  Arbre en vie");
        arbreVie.setFont(policeSpinnerEtLegende);
        arbreVie.setHorizontalAlignment(JLabel.LEFT);
        arbreViePanel.add(arbreVie);
        legendePanel.add(arbreViePanel);

        // Arbre en feu
        JPanel arbreFeuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3));
        arbreFeuPanel.setPreferredSize(new Dimension(220, 20));
        arbreFeuPanel.setOpaque(false);
        JLabel arbreFeuCouleur = new JLabel();
        arbreFeuCouleur.setBackground(Color.decode(Arbre.COULEUR_ARBRE_EN_FEU));
        arbreFeuCouleur.setOpaque(true);
        arbreFeuCouleur.setPreferredSize(new Dimension(14, 14));
        JLabel arbreFeu = new JLabel("  Arbre en feu");
        arbreFeu.setFont(policeSpinnerEtLegende);
        arbreFeu.setHorizontalAlignment(JLabel.LEFT);
        arbreFeuPanel.add(arbreFeuCouleur);
        arbreFeuPanel.add(arbreFeu);
        legendePanel.add(arbreFeuPanel);

        // Arbre en cendre
        JPanel arbreCendrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3));
        arbreCendrePanel.setPreferredSize(new Dimension(220, 20));
        arbreCendrePanel.setOpaque(false);
        JLabel arbreCendreCouleur = new JLabel();
        arbreCendreCouleur.setBackground(Color.decode(Arbre.COULEUR_ARBRE_EN_CENDRE));
        arbreCendreCouleur.setOpaque(true);
        arbreCendreCouleur.setPreferredSize(new Dimension(14, 14));
        arbreCendrePanel.add(arbreCendreCouleur);
        JLabel arbreCendre = new JLabel("  Arbre en cendre");
        arbreCendre.setFont(policeSpinnerEtLegende);
        arbreCendre.setHorizontalAlignment(JLabel.LEFT);
        arbreCendrePanel.add(arbreCendre);
        legendePanel.add(arbreCendrePanel);

        // Arbre sol humide
        JPanel arbreSolHumidePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3));
        arbreSolHumidePanel.setPreferredSize(new Dimension(220, 20));
        arbreSolHumidePanel.setOpaque(false);
        JLabel arbreSolHumideCouleur = new JLabel();
        arbreSolHumideCouleur.setBackground(Color.decode(Arbre.COULEUR_SOL_HUMIDE));
        arbreSolHumideCouleur.setOpaque(true);
        arbreSolHumideCouleur.setPreferredSize(new Dimension(14, 14));
        arbreSolHumidePanel.add(arbreSolHumideCouleur);
        JLabel arbreSolHumide = new JLabel("  Arbre sur sol humide");
        arbreSolHumide.setFont(policeSpinnerEtLegende);
        arbreSolHumide.setHorizontalAlignment(JLabel.LEFT);
        arbreSolHumidePanel.add(arbreSolHumide);
        legendePanel.add(arbreSolHumidePanel);

        // Arbre peu inflammable
        JPanel arbreInflammablePanel= new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3));
        arbreInflammablePanel.setPreferredSize(new Dimension(220, 20));
        arbreInflammablePanel.setOpaque(false);
        JLabel arbreInflammableCouleur = new JLabel();
        arbreInflammableCouleur.setBackground(Color.decode(COULEUR_BACKGROUND_MENU));
        arbreInflammableCouleur.setBorder(BorderFactory.createLineBorder(Color.decode(Arbre.COULEUR_PEU_INFLAMMABLE), 1));
        arbreInflammableCouleur.setOpaque(true);
        arbreInflammableCouleur.setPreferredSize(new Dimension(14, 14));
        arbreInflammablePanel.add(arbreInflammableCouleur);
        JLabel arbreInflammable = new JLabel("  Arbre peu inflammable");
        arbreInflammable.setFont(policeSpinnerEtLegende);
        arbreInflammable.setHorizontalAlignment(JLabel.LEFT);
        arbreInflammablePanel.add(arbreInflammable);
        legendePanel.add(arbreInflammablePanel);

        // Adding vertical
        JPanel verticalSpace = new JPanel();
        verticalSpace.setPreferredSize(new Dimension(220, 5));
        verticalSpace.setOpaque(false);
        southAreaMenu.add(verticalSpace);

        southAreaMenu.add(legendePanel);
    }

    /**
     * Getter pour récupérer le nombre de lignes de la grille choisi par l'utilisateur.
     * @return Le nombre de lignes de la grille choisi par l'utilisateur.
     */
    public int getLignes(){
        return (int) this.lignesSpinner.getValue();
    }

    /**
     * Getter pour récupérer le nombre de colonnes de la grille choisi par l'utilisateur.
     * @return Le nombre de colonnes de la grille choisi par l'utilisateur.
     */
    public int getColonnes(){
        return (int) this.colonnesSpinner.getValue();
    }

    /**
     * Getter pour récupérer la densité de la grille choisi par l'utilisateur.
     * @return Le pourcentage d'arbres vivants de la grille choisi par l'utilisateur.
     */
    public int getDensite(){
        return (int) densiteSpinner.getValue();
    }

    /**
     * Getter pour récupérer le taux d'arbres sur sol humide que l'utilisateur souhaite avoir dans la grille.
     * @return Le taux d'arbres sur sol humide que l'utilisateur souhaite avoir dans la grille.
     */
    public int getTauxCellulesHumides(){
        return (int) tauxHumiditeSpinner.getValue();
    }

    /**
     * Getter pour récupérer le taux d'arbres à faible inflammabilité que l'utilisateur souhaite avoir dans la grille.
     * @return Le taux d'arbres à faible inflammabilité que l'utilisateur souhaite avoir dans la grille.
     */
    public int getTauxArbresPeuInflammable(){
        return (int) tauxArbresPeuInflammableSpinner.getValue();
    }

    /**
     * Getter pour récupérer la direction du vent choisie par l'utilisateur.
     * @return La direction du vent choisie par l'utilisateur.
     */
    public String getDirectionVent(){
        return String.valueOf(this.directionVentSpinner.getValue()).replace("Direction du vent: ", "");
    }

    /**
     * Getter pour récupérer la saison choisie par l'utilisateur.
     * @return La saison choisie par l'utilisateur.
     */
    public String getSaison(){
        return String.valueOf(saisonSpinner.getValue()).replace("Saison: ", "");
    }

    /**
     * Méthode permettant de mettre a jour le taux de forêt brulée.
     */
    public void updateTauxforetbrulee() {
        DecimalFormat decimalFormater = new DecimalFormat("#.##");
        this.tauxTitre.setText(decimalFormater.format(app.getTauxForetDejaBrulee()) + " %");
    }

    /**
     * Méthode permettant d'activer ou de désactiver le bouton Générer lorsque nécessaire.
     */
    public void activerBoutonGenerer(boolean activer){
        this.buttonGenerer.setEnabled(activer);
        if(activer)
            this.buttonGenerer.setBackground(Color.decode(COULEUR_BOUTON_GENERER_ACTIF));
        else
            this.buttonGenerer.setBackground(Color.decode(COULEUR_BOUTON_GENERER_NON_ACTIF));
    }

    /**
     * Méthode permettant d'activer ou de désactiver le bouton Lancer lorsque nécessaire.
     */
    public void activerBoutonLancer(boolean activer){
        this.buttonLancer.setEnabled(activer);
        if(activer)
            this.buttonLancer.setBackground(Color.decode(COULEUR_BOUTON_LANCER_ACTIF));
        else
            this.buttonLancer.setBackground(Color.decode(COULEUR_BOUTON_LANCER_NON_ACTIF));
    }

    /**
     * Méthode permettant d'activer ou de désactiver le bouton Arrêter lorsque nécessaire.
     */
    public void activerBoutonArreter(boolean activer){
        this.buttonArreter.setEnabled(activer);
        if(activer)
            this.buttonArreter.setBackground(Color.decode(COULEUR_BOUTON_ARRETER_ACTIF));
        else
            this.buttonArreter.setBackground(Color.decode(COULEUR_BOUTON_ARRETER_NON_ACTIF));
    }
}

/**
 * Cette classe est utilisée pour créer un format entier personnalisé pour les champs de texte de type JSpinner.
 * Le but est d'ajouter du texte en préfixe ou en suffixe dans le champ tout en le maintenant en tant qu'entier.
 */
class MyIntegerFormatterFactory extends JFormattedTextField.AbstractFormatterFactory {
    String prefix;
    String suffix;
    int min;
    int max;

    public MyIntegerFormatterFactory(String prefix, String suffix, int min, int max) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.min = min;
        this.max = max;
    }

    @Override
    public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
        return new JFormattedTextField.AbstractFormatter() {
            @Override
            public Object stringToValue(String text) throws ParseException {
                text = text.replace(prefix, "").replace(suffix, "");
                String[] words = text.split(" ");
                for (String word: words) {
                    try {
                        int parse = Integer.parseInt(word);
                        if (parse >= min && parse <= max) return parse;
                    } catch (Exception ignored) {}
                }
                throw new ParseException("", 0);
            }

            @Override
            public String valueToString(Object value) {
                return prefix + value + suffix;
            }
        };
    }
}