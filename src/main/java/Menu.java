import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    private App app;
    private final static String COULEUR_BOUTON_GENERER = "#47B9F3"; // couleur en hexadecimal du bouton « Générer ».
    private final static String COULEUR_BOUTON_LANCER = "#47F3A0"; // couleur en hexadecimal du bouton « Lancer ».
    private final static String COULEUR_BOUTON_ARRETER = "#FD0E0E"; // couleur en hexadecimal du bouton « Arrêter ».
    private final static String COULEUR_CHAMP_DE_TEXTE = "#E6E6E6"; // couleur en hexadecimal des champs de texte qui permettent
    // à l’utilisateur de faire des choix (JSpinner).
    private final static String COULEUR_SEPARATIONS = "#CBC4C4"; // couleur en hexadecimal des lignes de séparation.
    private final static String COULEUR_POURC_FORET_BRULEE = "#FF9620"; // couleur en hexadecimal d’affichage du pourcentage de la forêt brulée.
    private final static String COULEUR_BACKGROUND_ET_TEXT_BOUTTON = "#FCFCFC"; //Couleur fond menu et des textes dans les boutons
    private final static String COULEUR_SOL_NU = "#FFDCB5";
    private final static String COULEUR_ARBRE_EN_VIE = "#7CEA4F";
    private final static String COULEUR_ARBRE_EN_FEU = "#FFA703";
    private final static String COULEUR_ARBRE_EN_CENDRE = "#C3C3C3";
    private final static String COULEUR_ARBRE_SUR_SOL_HUMIDE= "#009719";
    private final static String COULEUR_ARBRE_PEU_INFLAMMABLE = "#FFFFFF";
    private int taux_foret_brulee;
    private Font police;
    private Font policeTaux;
    private Font policeSpinner;
    private Box menuComposantes;
    private JPanel configPanel; //Panel partie config
    private JLabel configTitre;//Le label "Configurer la fenetre"
    private SpinnerModel model_Spinner_Colonne;//Modele des files deroulantes pour colonne
    private SpinnerModel model_Spinner_Ligne;//Modele des files deroulantes pour lignes
    private SpinnerModel model_densite;//Modele file deroulante pourcentage densite
    private SpinnerModel model_humidite;//Modele file deroulante pourcentage humidite
    private SpinnerModel model_inflammable;//Modele file deroulante pourcentage inflammable
    private SpinnerModel model_Spinner_CDirVent;
    private String directions[];//les 4 directions
    private SpinnerModel model_Spinner_Saison;
    private String saisons[];//les 4 saisons
    private JPanel spinnerColonnePanel;//Panel qui contient la ligne grahpique du spinner colonne
    private JLabel colonneLabel;
    private JSpinner colonneSpinner;
    private JPanel spinnerLignePanel;//Panel qui contient la ligne graphique du spinner colonne
    private JLabel ligneLabel;
    private JSpinner ligneSpinner;
    private JPanel spinnerDensitePanel;//Panel qui contient la ligne graphique du spinner Densite
    private JLabel densiteLabel;
    private JSpinner densiteSpinner;
    private JPanel spinnerHumiditePanel;//Panel qui contient la ligne graphique du spinner humidite
    private JLabel humiditeLabel;
    private JSpinner humiditeSpinner;
    private JPanel spinnerInflammablePanel;//Panel qui contient la ligne graphique du spinner inflammable
    private JLabel inflammableLabel;
    private JSpinner inflammableSpinner;
    private JButton buttonGenerer;
    private JPanel facteursPanel; //Panel partie facteurs externes
    private JLabel facteursTitre;//Le label "Facteurs Externes"
    private JPanel spinnerDirVentPanel;//Panel qui contient la ligne graphique du spinner Direction vent
    private JLabel dirVentLabel;
    private JSpinner dirVentSpinner;
    private JPanel spinnerSaisonPanel;//Panel qui contient la ligne graphique du spinner saison
    private JLabel saisonLabel;
    private JSpinner saisonSpinner;
    private JPanel simulerPanel; //Panel partie Simuler
    private JLabel simulerTitre;//Le label "Simuler"
    private JButton buttonLancer;
    private JButton buttonArreter;
    private JPanel tauxPanel; //Panel partie suivi pourcentage foret brulee
    private JLabel tauxTitre;//Le label pour afficher le pourcentage
    private JLabel titreVide;//Le label pour afficher le pourcentage
    private JPanel legendePanel;
    private JPanel solNuPanel;
    private JLabel solNuCodeCouleur;
    private JLabel solNu;
    private JPanel arbreViePanel;
    private JLabel arbreVieCouleur;
    private JLabel arbreVie;
    private JPanel arbreFeuPanel;
    private JLabel arbreFeuCouleur;
    private JLabel arbreFeu;
    private JPanel arbreCendrePanel;
    private JLabel arbreCendreCouleur;
    private JLabel arbreCendre;
    private JPanel arbreSolHumidePanel;
    private JLabel arbreSolHumideCouleur;
    private JLabel arbreSolHumide;
    private JPanel arbreInflammablePanel;
    private JLabel arbreInflammableCouleur;
    private JLabel arbreInflammable;
    public Menu(App app) {
        //initiallisation
        this.app = app;
        this.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));
        this.setMaximumSize(new Dimension(250, 3000));
        //Seul la bordure gauche est visible :
        this.setBorder(new MatteBorder(0, 1, 0, 0, Color.decode(COULEUR_SEPARATIONS)));
        //Comportement des objects a l'interieur de Menu :
        menuComposantes = Box.createVerticalBox();
        this.add(menuComposantes);
        //les polices d'ecriture
        police = new Font("Arial", Font.BOLD, 10);
        policeSpinner = new Font("Verdana", Font.PLAIN, 10);
        policeTaux = new Font("Verdana", Font.PLAIN, 15);

        taux_foret_brulee = 0;
        //Les composantes de l'interface
        configPanel = new JPanel();
        configTitre = new JLabel("Configurer la foret");
        model_Spinner_Colonne = new SpinnerNumberModel(
                50, //valeur initiale
                20, //valeur minimum
                100, //valeur maximum
                10 //pas
        );
        spinnerColonnePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        colonneLabel = new JLabel();
        colonneSpinner = new JSpinner(model_Spinner_Colonne);
        model_Spinner_Ligne = new SpinnerNumberModel(
                50, //valeur initiale
                20, //valeur minimum
                100, //valeur maximum
                10 //pas
        );
        spinnerLignePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligneLabel = new JLabel();
        ligneSpinner = new JSpinner(model_Spinner_Ligne);
        model_densite= new SpinnerNumberModel(
                50, //valeur initiale
                10, //valeur minimum
                100, //valeur maximum
                5 //pas
        );
        spinnerDensitePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        densiteLabel = new JLabel();
        densiteSpinner = new JSpinner(model_densite);
        model_humidite= new SpinnerNumberModel(
                50, //valeur initiale
                10, //valeur minimum
                100, //valeur maximum
                5 //pas
        );
        spinnerHumiditePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        humiditeLabel = new JLabel();
        humiditeSpinner = new JSpinner(model_humidite);
        model_inflammable= new SpinnerNumberModel(
                50, //valeur initiale
                10, //valeur minimum
                100, //valeur maximum
                5 //pas
        );
        spinnerInflammablePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inflammableLabel = new JLabel();
        inflammableSpinner = new JSpinner(model_inflammable);
        buttonGenerer = new JButton("Generer");
        facteursPanel = new JPanel();
        facteursTitre = new JLabel("Facteurs externe");
        directions = new String[] {"NORD", "WEST", "EST", "SUD"};
        model_Spinner_CDirVent = new SpinnerListModel(directions);
        spinnerDirVentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dirVentLabel = new JLabel();
        dirVentSpinner = new JSpinner(model_Spinner_CDirVent);
        saisons = new String[] {"PRINTEMPS", "ETE", "AUTOMNE", "HIVER"};
        model_Spinner_Saison = new SpinnerListModel(saisons);
        spinnerSaisonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        saisonLabel = new JLabel();
        saisonSpinner = new JSpinner(model_Spinner_Saison);
        simulerPanel = new JPanel();
        simulerTitre = new JLabel("Simuler");
        buttonLancer = new JButton("Lancer");
        buttonArreter = new JButton("Arreter");
        tauxPanel = new JPanel();
        tauxTitre = new JLabel(taux_foret_brulee+" %");
        titreVide = new JLabel();
        legendePanel = new JPanel();
        solNuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        solNuCodeCouleur = new JLabel();
        solNu = new JLabel("Sol nu");
        arbreFeuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        arbreFeuCouleur = new JLabel();
        arbreFeu = new JLabel("Arbre en feu");
        arbreViePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        arbreVieCouleur = new JLabel();
        arbreVie = new JLabel("Arbre en vie");
        arbreCendrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        arbreCendreCouleur = new JLabel();
        arbreCendre = new JLabel("Arbre en cendre");
        arbreSolHumidePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        arbreSolHumideCouleur = new JLabel();
        arbreSolHumide = new JLabel("Arbre sur sol humide");
        arbreInflammablePanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
        arbreInflammableCouleur = new JLabel();
        arbreInflammable = new JLabel("Arbre peu inflammable");

        //Creation des parties de l'inteface Menu
        partieConfig();
        partieFacteurs();
        partieSimuler();
        partieTaux();
        partieLegende();

    }

    /**
     * methode qui gere la creation de la partie configuration du menu
     */
    private void partieConfig() {
        //Titre
        configTitre.setFont(police);
        configTitre.setPreferredSize(new Dimension(150, 10));
        configTitre.setHorizontalAlignment(JLabel.LEFT);
        configTitre.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));

        //Spinner Colonne et ligne
        colonneLabel.setFont(policeSpinner);
        colonneLabel.setText("colonnes");
        spinnerColonnePanel.add(colonneSpinner);
        spinnerColonnePanel.add(colonneLabel);
        spinnerColonnePanel.setBackground(Color.decode(COULEUR_CHAMP_DE_TEXTE));

        ligneLabel.setFont(policeSpinner);
        ligneLabel.setText("lignes");
        spinnerLignePanel.add(ligneSpinner);
        spinnerLignePanel.add(ligneLabel);
        spinnerLignePanel.setBackground(Color.decode(COULEUR_CHAMP_DE_TEXTE));

        densiteLabel.setFont(policeSpinner);
        densiteLabel.setText("Densite de la foret (%)");
        spinnerDensitePanel.add(densiteLabel);
        spinnerDensitePanel.add(densiteSpinner);
        spinnerDensitePanel.setBackground(Color.decode(COULEUR_CHAMP_DE_TEXTE));

        humiditeLabel.setFont(policeSpinner);
        humiditeLabel.setText("Arbre sur sol humide (%)");
        spinnerHumiditePanel.add(humiditeLabel);
        spinnerHumiditePanel.add(humiditeSpinner);
        spinnerHumiditePanel.setBackground(Color.decode(COULEUR_CHAMP_DE_TEXTE));

        inflammableLabel.setFont(policeSpinner);
        inflammableLabel.setText("Arbre peu inflammable (%)");
        spinnerInflammablePanel.add(inflammableLabel);
        spinnerInflammablePanel.add(inflammableSpinner);
        spinnerInflammablePanel.setBackground(Color.decode(COULEUR_CHAMP_DE_TEXTE));

        //Evenement lorsqu'on appuye sur generer
        buttonGenerer.setBackground(Color.decode(COULEUR_BOUTON_GENERER));
        buttonGenerer.setForeground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));
        buttonGenerer.setBorderPainted(false);
        buttonGenerer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                app.genererGrille(getLigne(), getColonne());
            }
        });

        configPanel.setLayout(new GridLayout(7, 1,0,4));
        configPanel.add(configTitre);
        configPanel.add(spinnerColonnePanel);
        configPanel.add(spinnerLignePanel);
        configPanel.add(spinnerDensitePanel);
        configPanel.add(spinnerHumiditePanel);
        configPanel.add(spinnerInflammablePanel);
        configPanel.add(buttonGenerer);
        configPanel.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));


        menuComposantes.add(configPanel);
    }

    /**
     * methode qui gere la mise en place partie Facteurs Externe du menu
     */
    private void partieFacteurs(){
        //Titre
        facteursTitre.setFont(police);
        facteursTitre.setPreferredSize(new Dimension(150, 10));
        facteursTitre.setHorizontalAlignment(JLabel.LEFT);
        facteursTitre.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));

        //Spinner Direction vent
        dirVentLabel.setFont(policeSpinner);
        dirVentLabel.setText("Direction du vent :");
        spinnerDirVentPanel.add(dirVentLabel);
        spinnerDirVentPanel.add(dirVentSpinner);
        spinnerDirVentPanel.setBackground(Color.decode(COULEUR_CHAMP_DE_TEXTE));

        //Spinner Saison
        saisonLabel.setFont(policeSpinner);
        saisonLabel.setText("Saison :");
        spinnerSaisonPanel.add(saisonLabel);
        spinnerSaisonPanel.add(saisonSpinner);
        spinnerSaisonPanel.setBackground(Color.decode(COULEUR_CHAMP_DE_TEXTE));

        facteursPanel.setLayout(new GridLayout(3, 1,0,4));
        facteursPanel.add(facteursTitre);
        facteursPanel.add(spinnerDirVentPanel);
        facteursPanel.add(spinnerSaisonPanel);
        facteursPanel.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));

        menuComposantes.add(facteursPanel);
    }

    /**
     * methode qui gere la mise en place partie Facteurs Externe du menu
     */
    private void partieSimuler(){
        //Titre
        simulerTitre.setFont(police);
        simulerTitre.setPreferredSize(new Dimension(150, 10));
        simulerTitre.setHorizontalAlignment(JLabel.LEFT);
        simulerTitre.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));

        //Evenement lorsqu'on appuye sur Lancer
        buttonLancer.setBackground(Color.decode(COULEUR_BOUTON_LANCER));
        buttonLancer.setForeground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));
        buttonLancer.setBorderPainted(false);
        buttonLancer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                app.lancerSimulation();
            }
        });

        //Evenement lorsqu'on appuye sur Arreter
        buttonArreter.setBackground(Color.decode(COULEUR_BOUTON_ARRETER));
        buttonArreter.setForeground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));
        buttonArreter.setBorderPainted(false);
        buttonArreter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                app.setStopSimulation(true);
            }
        });

        simulerPanel.setLayout(new GridLayout(3, 1,0,4));
        simulerPanel.add(simulerTitre);
        simulerPanel.add(buttonLancer);
        simulerPanel.add(buttonArreter);
        simulerPanel.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));


        menuComposantes.add(simulerPanel);
    }

    /**
     * methode qui gere la mise en place partie du taux d'arbre brulee
     */
    private void partieTaux(){
        //Titre
        tauxTitre.setFont(policeTaux);
        tauxTitre.setForeground(Color.decode(COULEUR_POURC_FORET_BRULEE));
        tauxTitre.setPreferredSize(new Dimension(150, 10));
        tauxTitre.setHorizontalAlignment(JLabel.CENTER);
        tauxTitre.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));

        //Titre vide
        titreVide.setPreferredSize(new Dimension(150, 40));
        titreVide.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));

        tauxPanel.setLayout(new GridLayout(2, 1,0,4));
        tauxPanel.add(titreVide);
        tauxPanel.add(tauxTitre);
        tauxPanel.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));

        menuComposantes.add(tauxPanel);
    }
    /**
     * methode qui gere la mise en place de la partie legende
     */
    private void partieLegende(){
        //Sol nu
        solNu.setFont(policeSpinner);
        solNu.setPreferredSize(new Dimension(135, 20));
        solNu.setHorizontalAlignment(JLabel.LEFT);
        solNuCodeCouleur.setBackground(Color.decode(COULEUR_SOL_NU));
        solNuCodeCouleur.setOpaque(true);
        solNuCodeCouleur.setPreferredSize(new Dimension(15, 15));
        solNuPanel.add(solNuCodeCouleur);
        solNuPanel.add(solNu);
        solNuPanel.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));
        //Arbre en vie
        arbreVie.setFont(policeSpinner);
        arbreVie.setPreferredSize(new Dimension(135, 10));
        arbreVie.setHorizontalAlignment(JLabel.LEFT);
        arbreVieCouleur.setBackground(Color.decode(COULEUR_ARBRE_EN_VIE));
        arbreVieCouleur.setOpaque(true);
        arbreVieCouleur.setPreferredSize(new Dimension(15, 15));
        arbreViePanel.add(arbreVieCouleur);
        arbreViePanel.add(arbreVie);
        arbreViePanel.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));
        //Arbre en cendre
        arbreCendre.setFont(policeSpinner);
        arbreCendre.setPreferredSize(new Dimension(135, 10));
        arbreCendre.setHorizontalAlignment(JLabel.LEFT);
        arbreCendreCouleur.setBackground(Color.decode(COULEUR_ARBRE_EN_CENDRE));
        arbreCendreCouleur.setOpaque(true);
        arbreCendreCouleur.setPreferredSize(new Dimension(15, 15));
        arbreCendrePanel.add(arbreCendreCouleur);
        arbreCendrePanel.add(arbreCendre);
        arbreCendrePanel.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));
        //Arbre en feu
        arbreFeu.setFont(policeSpinner);
        arbreFeu.setPreferredSize(new Dimension(135, 10));
        arbreFeu.setHorizontalAlignment(JLabel.LEFT);
        arbreFeuCouleur.setBackground(Color.decode(COULEUR_ARBRE_EN_FEU));
        arbreFeuCouleur.setOpaque(true);
        arbreFeuCouleur.setPreferredSize(new Dimension(15, 15));
        arbreFeuPanel.add(arbreFeuCouleur);
        arbreFeuPanel.add(arbreFeu);
        arbreFeuPanel.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));
        //Arbre sol humide
        arbreSolHumide.setFont(policeSpinner);
        arbreSolHumide.setPreferredSize(new Dimension(135, 10));
        arbreSolHumide.setHorizontalAlignment(JLabel.LEFT);
        arbreSolHumideCouleur.setBackground(Color.decode(COULEUR_ARBRE_SUR_SOL_HUMIDE));
        arbreSolHumideCouleur.setOpaque(true);
        arbreSolHumideCouleur.setPreferredSize(new Dimension(15, 15));
        arbreSolHumidePanel.add(arbreSolHumideCouleur);
        arbreSolHumidePanel.add(arbreSolHumide);
        arbreSolHumidePanel.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));
        //Arbre sol humide
        arbreInflammable.setFont(policeSpinner);
        arbreInflammable.setPreferredSize(new Dimension(135, 10));
        arbreInflammable.setHorizontalAlignment(JLabel.LEFT);
        arbreInflammableCouleur.setBackground(Color.decode(COULEUR_ARBRE_PEU_INFLAMMABLE));
        arbreInflammableCouleur.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        arbreInflammableCouleur.setOpaque(true);
        arbreInflammableCouleur.setPreferredSize(new Dimension(15, 15));
        arbreInflammablePanel.add(arbreInflammableCouleur);
        arbreInflammablePanel.add(arbreInflammable);
        arbreInflammablePanel.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));




        legendePanel.setLayout(new GridLayout(6, 1,0,4));
        legendePanel.add(solNuPanel);
        legendePanel.add(arbreViePanel);
        legendePanel.add(arbreCendrePanel);
        legendePanel.add(arbreFeuPanel);
        legendePanel.add(arbreSolHumidePanel);
        legendePanel.add(arbreInflammablePanel);

        legendePanel.setBackground(Color.decode(COULEUR_BACKGROUND_ET_TEXT_BOUTTON));

        menuComposantes.add(legendePanel);
    }
    /**
     * les getter pour initialliser/mettre a jour les attributs de App
     * @return
     */
    public int getLigne(){
        return (int) ligneSpinner.getValue();
    }
    public int getColonne(){
        return (int) colonneSpinner.getValue();
    }
    public int getDensite(){
        return (int) densiteSpinner.getValue();
    }
    public int getHumidite(){
        return (int) humiditeSpinner.getValue();
    }
    public int getTauxInflammable(){
        return (int) inflammableSpinner.getValue();
    }
    public String getDirectionVent(){
        return (String) dirVentSpinner.getValue();
    }
    public String getSaison(){
        return (String) saisonSpinner.getValue();
    }

    /**
     * Methode pour mettre a jour le taux de foret brulee.
     * Quand l'utilisateur appuie sur Lancer, le programme va lancer App.LancerSimultaion() et cette methode.
     * Tant que le programme n'est pas fini, on continuera a mettre a jour.
     */
    public void upDate_Taux_foret_brulee() {
        tauxTitre.setText(app.getTaux_foret_deja_brulee()+" %");
    }
}
