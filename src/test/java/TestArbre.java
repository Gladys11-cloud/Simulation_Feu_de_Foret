
import org.junit.jupiter.api.Test ;
import static org.assertj.core.api.Assertions .*;
public class TestArbre {
    //test getters and setter

    @Test
    public void testSolEstHumide(){
        Arbre arbre = new Arbre(10, 10, 5, 5, new Grille(new App()), true, false);
        assertThat(arbre.solEstHumide()).isEqualTo(true);
        arbre = new Arbre(10, 10, 5, 5, new Grille(new App()), false, false);
        assertThat(arbre.solEstHumide()).isEqualTo(false);
    }
    @Test
    public void testSetSolHumide(){
        Arbre arbre = new Arbre(10, 10, 5, 5, new Grille(new App()), true, false);
        arbre.setSolHumide(true);
        assertThat(arbre.solEstHumide()).isEqualTo(true);
        arbre.setSolHumide(false);
        assertThat(arbre.solEstHumide()).isEqualTo(false);
    }
    @Test
    public void testArbreEstPeuInflammable(){
        Arbre arbre = new Arbre(10, 10, 5, 5, new Grille(new App()), false, true);
        assertThat(arbre.arbreEstPeuInflammable()).isEqualTo(true);
        arbre = new Arbre(10, 10, 5, 5, new Grille(new App()), false, false);
        assertThat(arbre.arbreEstPeuInflammable()).isEqualTo(false);
    }
    @Test
    public void testSetArbreEstPeuInflammable(){
        Arbre arbre = new Arbre(10, 10, 5, 5, new Grille(new App()), true, false);
        arbre.setArbrePeuInflammable(true);
        assertThat(arbre.arbreEstPeuInflammable()).isEqualTo(true);
        arbre.setArbrePeuInflammable(false);
        assertThat(arbre.arbreEstPeuInflammable()).isEqualTo(false);
    }

    @Test
    public void testBasculer(){
        Arbre arbre = new Arbre(10, 10, 5, 5, new Grille(new App()), true, false);
        arbre.etat=1;
        arbre.etat_futur=2;
        arbre.basculer();
        assertThat(arbre.etat).isEqualTo(2);
    }
    @Test
    public void testCalculeEtatFuture_arbreEnVie() {
        Grille grille = new Grille(new App());
        Arbre arbre1 = new Arbre(10, 10, 5, 5, grille, false, false);
        Arbre arbre2 = new Arbre(10, 10, 5, 5, grille, false, false);
        Arbre arbre3 = new Arbre(10, 10, 5, 5, grille, false, false);
        Arbre arbre4 = new Arbre(10, 10, 5, 5, grille, false, false);
        arbre1.etat=1;
        arbre2.etat=1;
        arbre3.etat=1;
        arbre4.etat=1;
        Cellule[] voisins=new Cellule[]{arbre1, arbre2, arbre3, arbre4};
        int nArbre=10000;
        testNombreArbreFeu(nArbre,voisins);
        testSaison(nArbre,voisins);
        testHumidite(nArbre,voisins);
        testArbrePeuInflammable(nArbre,voisins);
        testDirctionVent(nArbre,voisins);
         }

    @Test
    public void testCalculeEtatFuture_arbreEnFeu() {
        Grille grille = new Grille(new App());
        Arbre arbre = new Arbre(10, 10, 5, 5, grille, true, false);
        arbre.etat=2;
        arbre.calculeEtatFuture();
        assertThat(arbre.getEtatFutur()).isEqualTo(3);
    }

    @Test
    public void testCalculeEtatFuture_arbreEnCendre() {
        Grille grille = new Grille(new App());
        Arbre arbre = new Arbre(10, 10, 5, 5, grille, true, false);
        arbre.etat=3;
        arbre.calculeEtatFuture();
        assertThat(arbre.getEtatFutur()).isEqualTo(3);
    }
    //helpers
    private int simulerPropagationFeu(int nArbre,boolean sol_humide,boolean arbre_peu_inflammable, Cellule [] voisins,String direction_vent,String saison){
        int compteurArbreEnfeu=0;
        for(int i=1; i<=nArbre;i++){
            Arbre arbre = new Arbre(10, 10, 5, 5, new Grille(new App()), sol_humide, arbre_peu_inflammable);
            arbre.grille.app.saison=saison;
            arbre.grille.app.direction_vent=direction_vent;
            arbre.setVoisins(voisins);
            arbre.calculeEtatFuture();
            if(arbre.etat_futur==2) compteurArbreEnfeu ++;
        }
        return compteurArbreEnfeu;
    }
    private void testNombreArbreFeu(int nArbre, Cellule [] voisins){
        int compteurArbreEnfeu;
        voisins[0].etat=2;
        compteurArbreEnfeu=simulerPropagationFeu(nArbre,false,false,
                voisins,null,null); //expected p=0.85
        assertThat(compteurArbreEnfeu).isCloseTo(8500,within(85));
        voisins[1].etat=2;
        voisins[2].etat=2;
        voisins[3].etat=2;
        compteurArbreEnfeu=simulerPropagationFeu(nArbre,false,false,
                voisins,null,null); //expected p=1
        assertThat(compteurArbreEnfeu).isEqualTo(10000);
        renitialiseEtat(voisins);
    }
    private void testSaison(int nArbre, Cellule [] voisins){
        int compteurArbreEnfeu;
        voisins[0].etat=2;
        compteurArbreEnfeu=simulerPropagationFeu(nArbre,false,false,
                voisins,null,"PRINTEMPS"); //expected p=0.85
        assertThat(compteurArbreEnfeu).isCloseTo(8500,within(85));
        compteurArbreEnfeu=simulerPropagationFeu(nArbre,false,false,
                voisins,null,"HIVER"); //expected p=0.75
        assertThat(compteurArbreEnfeu).isCloseTo(7500,within(75));
        compteurArbreEnfeu=simulerPropagationFeu(nArbre,false,false,
                voisins,null,"ETE"); //expected p=0.95
        assertThat(compteurArbreEnfeu).isCloseTo(9500,within(95));
        renitialiseEtat(voisins);
    }
    private void testHumidite(int nArbre, Cellule [] voisins){
        int compteurArbreEnfeu;
        voisins[0].etat=2;
        compteurArbreEnfeu=simulerPropagationFeu(nArbre,true,false,
                voisins,null,null); //expected p=0.75
        assertThat(compteurArbreEnfeu).isCloseTo(7500,within(75));
        renitialiseEtat(voisins);
    }
    private void testArbrePeuInflammable(int nArbre, Cellule [] voisins){
        int compteurArbreEnfeu;
        voisins[0].etat=2;
        compteurArbreEnfeu=simulerPropagationFeu(nArbre,false,true,
                voisins,null,null); //expected p=0.75
        assertThat(compteurArbreEnfeu).isCloseTo(7500,within(75));
        renitialiseEtat(voisins);
    }
    private void testDirctionVent(int nArbre, Cellule [] voisins){
        int compteurArbreEnfeu;
        voisins[0].etat=2;
        compteurArbreEnfeu=simulerPropagationFeu(nArbre,false,false,
                voisins,"NORTH",null); //expected p=0.75
        assertThat(compteurArbreEnfeu).isCloseTo(7500,within(75));
        compteurArbreEnfeu=simulerPropagationFeu(nArbre,false,false,
                voisins,"EST",null); //expected p=1
        assertThat(compteurArbreEnfeu).isEqualTo(10000);
        renitialiseEtat(voisins);

    }

    private void renitialiseEtat(Cellule[] voisins){
        voisins[0].etat=1;
        voisins[1].etat=1;
        voisins[2].etat=1;
        voisins[3].etat=1;

    }
}
