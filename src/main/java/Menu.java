import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Menu extends JPanel {
    private int largeur ; // largeur minimale du panneau
    private int hauteur ; // hauteur minimale du panneau
    private final static String COULEUR_BOUTON_GENERER = "#47B9F3"; // couleur en hexadecimal du bouton « Générer ».
    private final static String COULEUR_BOUTON_LANCER = "#47F3A0"; // couleur en hexadecimal du bouton « Lancer ».
    private final static String COULEUR_BOUTON_ARRETER = "#FD0E0E"; // couleur en hexadecimal du bouton « Arrêter ».
    final static String COULEUR_CHAMP_DE_TEXTE ="#E6E6E6"; // couleur en hexadecimal des champs de texte qui permettent
    // à l’utilisateur de faire des choix (JSpinner).
    private final static String COULEUR_SEPARATIONS="#303030"; // couleur en hexadecimal des lignes de séparation.
    private final static String COULEUR_POURC_FORET_BRULEE ="FE9E00"; // couleur en hexadecimal d’affichage du pourcentage de la forêt brulée.
    private final static String COULEUR_BACKGROUND = "#F2F2F2"; //Couleur fond menu
    private Font police = new Font("Verdana", Font.PLAIN, 10);
    private JLabel configLabel = new JLabel("Configurer la foret");//Le label "Configurer la fenetre"
    public Menu(){
        largeur = 100;
        this.setBackground( Color.decode(COULEUR_BACKGROUND) );
        this.setMaximumSize(new Dimension(250, 3000));
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        //Partie Config
        configLabel.setFont(police);
        configLabel.setPreferredSize(new Dimension(150, 30));
        configLabel.setHorizontalAlignment(JLabel.LEFT);
        configLabel.setBackground(Color.BLUE);
        configLabel.setForeground(Color.black);
        configLabel.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(COULEUR_SEPARATIONS)));
        this.add(configLabel);
    }


}
