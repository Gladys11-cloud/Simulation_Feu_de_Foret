import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Menu extends JPanel {
    private int largeur; // largeur minimale du panneau
    private int hauteur; // hauteur minimale du panneau
    private final static String COULEUR_BOUTON_GENERER = "#47B9F3"; // couleur en hexadecimal du bouton « Générer ».
    private final static String COULEUR_BOUTON_LANCER = "#47F3A0"; // couleur en hexadecimal du bouton « Lancer ».
    private final static String COULEUR_BOUTON_ARRETER = "#FD0E0E"; // couleur en hexadecimal du bouton « Arrêter ».
    private final static String COULEUR_CHAMP_DE_TEXTE = "#E6E6E6"; // couleur en hexadecimal des champs de texte qui permettent
    // à l’utilisateur de faire des choix (JSpinner).
    private final static String COULEUR_SEPARATIONS = "#CBC4C4"; // couleur en hexadecimal des lignes de séparation.
    private final static String COULEUR_POURC_FORET_BRULEE = "FE9E00"; // couleur en hexadecimal d’affichage du pourcentage de la forêt brulée.
    private final static String COULEUR_BACKGROUND = "#FCFCFC"; //Couleur fond menu
    private Font police;

    private Font policeSpinner;
    private JPanel configPanel; //Panel partie config
    private JLabel configTitre;//Le label "Configurer la fenetre"
    private SpinnerModel model_Spinner_Colonne;//Modele des files deroulantes pour colonne
    private SpinnerModel model_Spinner_Ligne;//Modele des files deroulantes pour lignes
    private SpinnerModel model_densite;//Modele file deroulante pourcentage densite
    private SpinnerModel model_humidite;//Modele file deroulante pourcentage humidite
    private SpinnerModel model_inflammable;//Modele file deroulante pourcentage inflammable
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
    public Menu() {
        //initiallisation
        police = new Font("Arial", Font.BOLD, 10);
        policeSpinner = new Font("Verdana", Font.PLAIN, 10);
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

        this.setBackground(Color.decode(COULEUR_BACKGROUND));
        this.setMaximumSize(new Dimension(250, 3000));
        this.setBorder(new MatteBorder(0, 1, 0, 0, Color.decode(COULEUR_SEPARATIONS)));
        //Creation des parties de l'inteface Menu
        partieConfig();


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

        configPanel.setLayout(new GridLayout(7, 1,0,4));
        configPanel.add(configTitre);
        configPanel.add(spinnerColonnePanel);
        configPanel.add(spinnerLignePanel);
        configPanel.add(spinnerDensitePanel);
        configPanel.add(spinnerHumiditePanel);
        configPanel.add(spinnerInflammablePanel);
        configPanel.setBackground(Color.decode(COULEUR_BACKGROUND));

        this.add(configPanel);
    }




}
