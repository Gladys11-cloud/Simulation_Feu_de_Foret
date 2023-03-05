import java.util.Random;
public class Arbre extends Cellule {

    private final static String COULEUR_ARBRE_VIVANT="#B6C772";
    private final static String COULEUR_ARBRE_EN_FEU="#F3770F";
    private final static String COULEUR_ARBRE_EN_CENDRE="#D4D4D4";
    private final static String COULEUR_SOL_HUMIDE="#7F8F57";
    private final static String COULEUR_PEU_INFLAMMABLE="#0474BC";
    private boolean sol_humide;
    private boolean arbre_peu_inflammable;
    public Arbre(  int x, int y, Grille grille, Boolean sol_humide, Boolean arbre_peu_inflammable){
        super(x, y, grille);
        this.setSolHumide(sol_humide);
        this.setArbrePeuInflammable(arbre_peu_inflammable);
        this.etat=1;
    }

    public void calculeEtatFuture(){
        double p;//p:probabilité de propagation du feu vers la cellule
        if( etat==3) etat_futur=etat;
        else if(etat==2)    etat_futur=3;
        else if(etat==1){
            if (nombreDeVoisinsEnFeu()==0)
                etat_futur=etat;
            else{
                p=calculeP(); //p= propability of spreading fire
                if (feuVaSePropager(p)) etat_futur=2;
                else etat_futur=etat;}
        }
    }
    public void basculer(){
        etat=super.etat_futur;
    }

//Setters,getters
    public boolean solEstHumide(){
        return this.sol_humide;
    }
    public void setSolHumide(boolean sol_humide){
        this.sol_humide=sol_humide;
    }
    public boolean arbreEstPeuInflammable(){
        return arbre_peu_inflammable;
    }
    public void setArbrePeuInflammable(boolean arbre_peu_inflammable){
        this.arbre_peu_inflammable=arbre_peu_inflammable;
    }



    //helpers
    private double calculeP(){//calcul p la probabilité de propagation de feu
        double p=0.8+nombreDeVoisinsEnFeu()*0.05;
        boolean feu_vers_cellule=feuVersCellule();
        if(feu_vers_cellule==false && grille.app.direction_vent!=null) p-=0.1;
        if(feu_vers_cellule) p+=0.5;
        //saison
        String saison= grille.app.saison;;
        if(saison=="HIVER") p-=0.1;
        else if(saison=="ETE") p+=0.1;
        //humidité
        if(solEstHumide()) p-=0.1;
        if(arbreEstPeuInflammable()) p-=0.1;
        if(p<0) p=0;
        if(p>1) p=1;
        return p;
    }
    private boolean feuVaSePropager(double p){//effectue la realisation de la probabilité p
        Random rand= new Random();
        double randomNum= rand.nextDouble();
        if(randomNum<=p) return true;
        return false;
    }
    private boolean feuVersCellule(){
        String direction_vent=grille.app.direction_vent;
        if(voisins[0].etat==2 && direction_vent=="EST" ||
        voisins[1].etat==2 && direction_vent=="SUD"||
        voisins[2].etat==2 && direction_vent=="OUEST"||
        voisins[3].etat==2 && direction_vent=="NORD") return true;
        return false;

    }
    private int nombreDeVoisinsEnFeu(){
        int compteur=0;
        for(Cellule voisin : voisins){
            if(voisin.etat==2) compteur++;
        }
        return compteur;
    }
}