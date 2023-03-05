
public class SolNu extends Cellule {

    final static String COULEUR="#F6E5C6";
    SolNu( int x, int y, Grille grille){
        super(x, y, grille);
        etat=0;
    }
    void calculeEtatFutur(){
        etat_futur=0;
    }
    void basculer(){
        etat=etat_futur;
    }
}
