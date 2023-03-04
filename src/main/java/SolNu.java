public class SolNu extends Cellule {

    final static String COULEUR="#F6E5C6";
    SolNu(int largeur, int hauteur, int x, int y, Grille grille){
        super(largeur, hauteur,x, y, grille);
        etat=0;
    }
    void calculeEtatFutur(){
        etat_futur=0;
    }
    void basculer(){
        etat=etat_futur;
    }
}
