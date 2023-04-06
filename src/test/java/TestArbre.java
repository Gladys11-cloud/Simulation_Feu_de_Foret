import org.junit.jupiter.api.Test ;
import static org.assertj.core.api.Assertions.*;

/**
 * Cette classe permet de tester le bon fonctionnement du constructeur et des méthodes de la classe Arbre.
 */
public class TestArbre {
    /**
     * Méthode de test qui s'assure que le getter Arbre.solEstHumide() fonctionne correctement.
     */
    @Test
    public void testSolEstHumide(){
        Arbre arbre = new Arbre(10, 10, new Grille(), true, false);
        assertThat(arbre.solEstHumide()).isEqualTo(true);
        arbre = new Arbre(10, 10, new Grille(), false, false);
        assertThat(arbre.solEstHumide()).isEqualTo(false);
    }

    /**
     * Méthode de test qui s'assure que le setter Arbre.setSolHumide() fonctionne correctement.
     */
    @Test
    public void testSetSolHumide(){
        Arbre arbre = new Arbre(10, 10,  new Grille(), true, false);
        arbre.setSolHumide(false);
        assertThat(arbre.solEstHumide()).isEqualTo(false);
        arbre.setSolHumide(true);
        assertThat(arbre.solEstHumide()).isEqualTo(true);
    }

    /**
     * Méthode de test qui s'assure que le getter Arbre.arbreEstPeuInflammable() fonctionne correctement.
     */
    @Test
    public void testArbreEstPeuInflammable(){
        Arbre arbre = new Arbre(10, 10,  new Grille(), false, true);
        assertThat(arbre.arbreEstPeuInflammable()).isEqualTo(true);
        arbre = new Arbre(10, 10,  new Grille(), false, false);
        assertThat(arbre.arbreEstPeuInflammable()).isEqualTo(false);
    }

    /**
     * Méthode de test qui s'assure que le setter Arbre.setArbrePeuInflammable() fonctionne correctement.
     */
    @Test
    public void testSetArbrePeuInflammable(){
        Arbre arbre = new Arbre(10, 10,  new Grille(), true, false);
        arbre.setArbrePeuInflammable(true);
        assertThat(arbre.arbreEstPeuInflammable()).isEqualTo(true);
        arbre.setArbrePeuInflammable(false);
        assertThat(arbre.arbreEstPeuInflammable()).isEqualTo(false);
    }

    /**
     * Méthode de test qui s'assure que le basculement de l'état courant vers l'état futur fonctionne correctement.
     */
    @Test
    public void testBasculer(){
        Arbre arbre = new Arbre(10, 10,  new Grille(), true, false);
        arbre.setEtat(1);

        Arbre vg = new Arbre(10, 10,  new Grille(), true, false);
        vg.setEtat(2);
        Arbre vh = new Arbre(10, 10,  new Grille(), true, false);
        vh.setEtat(2);
        Arbre vd = new Arbre(10, 10,  new Grille(), true, false);
        vd.setEtat(2);
        Arbre vb = new Arbre(10, 10,  new Grille(), true, false);
        vb.setEtat(2);

        Cellule[] voisins = {vg, vh, vd, vb};
        arbre.setVoisins(voisins);

        arbre.calculeEtatFutur("SUD", "ETE");

        arbre.basculer();
        assertThat(arbre.getEtat()).isEqualTo(2);
    }

    /**
     * Méthode de test qui s'assure que l'influence du voisinage sur le calcul de l'état futur d'un arbre en vie fonctionne correctement.
     */
    @Test
    public void testCalculeEtatFutur_ImpactVoisinageSurArbreEnVie() {
        Arbre voisin_gauche = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_haut = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_droite = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_bas = new Arbre(10, 10,  new Grille(), false, false);
        Cellule[] voisins= {voisin_gauche, voisin_haut, voisin_droite, voisin_bas};

        int compteurArbreEnfeu = 0;
        // 1 seul voisin en feu : environ 85% (8500) des 10000 arbres devraient prendre feu.
        voisins[0].setEtat(2);

        for(int i = 1; i <= 10000; i++){
            Arbre arbre = new Arbre(10, 10,  new Grille(), false, false);
            arbre.setVoisins(voisins);
            arbre.calculeEtatFutur(null, null);
            if(arbre.getEtatFutur() == 2) compteurArbreEnfeu++;
        }

        assertThat(compteurArbreEnfeu).isCloseTo(8500,within(85));

        // Tous les 4 voisins en feu : 100% (10000) des 10000 arbres devraient prendre feu.
        voisins[1].setEtat(2); voisins[2].setEtat(2); voisins[3].setEtat(2);
        compteurArbreEnfeu = 0;

        for(int i = 1; i <= 10000; i++){
            Arbre arbre = new Arbre(10, 10,  new Grille(), false, false);
            arbre.setVoisins(voisins);
            arbre.calculeEtatFutur(null, null);
            if(arbre.getEtatFutur() == 2) compteurArbreEnfeu++;
        }

        assertThat(compteurArbreEnfeu).isEqualTo(10000);
    }

    /**
     * Méthode de test qui s'assure que l'influence de la saison sur le calcul de l'état futur d'un arbre en vie fonctionne correctement.
     */
    @Test
    public void testCalculeEtatFutur_ImpactSaisonSurArbreEnVie_Printemps() {
        Arbre voisin_gauche = new Arbre(10, 10, new Grille(), false, false);
        Arbre voisin_haut = new Arbre(10, 10, new Grille(), false, false);
        Arbre voisin_droite = new Arbre(10, 10, new Grille(), false, false);
        Arbre voisin_bas = new Arbre(10, 10, new Grille(), false, false);
        Cellule[] voisins = {voisin_gauche, voisin_haut, voisin_droite, voisin_bas};

        int compteurArbreEnfeu = 0;
        // 1 voisin en feu et saison PRINTEMPS : le printemps n'a aucun effet.
        // Donc environ 85% (8500) des 10000 arbres devraient prendre feu.
        voisins[0].setEtat(2);

        for (int i = 1; i <= 10000; i++) {
            Arbre arbre = new Arbre(10, 10, new Grille(), false, false);
            arbre.setVoisins(voisins);
            arbre.calculeEtatFutur(null, "PRINTEMPS");
            if (arbre.getEtatFutur() == 2) compteurArbreEnfeu++;
        }

        assertThat(compteurArbreEnfeu).isCloseTo(8500, within(85));
    }
    @Test
    public void testCalculeEtatFutur_ImpactSaisonSurArbreEnVie_Hiver() {
        Arbre voisin_gauche = new Arbre(10, 10, new Grille(), false, false);
        Arbre voisin_haut = new Arbre(10, 10, new Grille(), false, false);
        Arbre voisin_droite = new Arbre(10, 10, new Grille(), false, false);
        Arbre voisin_bas = new Arbre(10, 10, new Grille(), false, false);
        Cellule[] voisins = {voisin_gauche, voisin_haut, voisin_droite, voisin_bas};

        int compteurArbreEnfeu = 0;
        // 1 voisin en feu et saison HIVER : l'hiver réduit la propagation du feu.
        // Donc environ 75% (7500) des 10000 arbres devraient prendre feu.
        voisins[0].setEtat(2);

        for (int i = 1; i <= 10000; i++) {
            Arbre arbre = new Arbre(10, 10, new Grille(), false, false);
            arbre.setVoisins(voisins);
            arbre.calculeEtatFutur(null, "HIVER");
            if (arbre.getEtatFutur() == 2) compteurArbreEnfeu++;
        }

        assertThat(compteurArbreEnfeu).isCloseTo(7500, within(75));
    }
    @Test
    public void testCalculeEtatFutur_ImpactSaisonSurArbreEnVie_Ete() {
        Arbre voisin_gauche = new Arbre(10, 10, new Grille(), false, false);
        Arbre voisin_haut = new Arbre(10, 10, new Grille(), false, false);
        Arbre voisin_droite = new Arbre(10, 10, new Grille(), false, false);
        Arbre voisin_bas = new Arbre(10, 10, new Grille(), false, false);
        Cellule[] voisins = {voisin_gauche, voisin_haut, voisin_droite, voisin_bas};
        voisins[0].setEtat(2);
        int compteurArbreEnfeu = 0;
        // 1 voisin en feu et saison ETE : l'été augmente la propagation du feu.
        // Donc environ 95% (9500) des 10000 arbres devraient prendre feu.


        for(int i = 1; i <= 10000; i++){
            Arbre arbre = new Arbre(10, 10,  new Grille(), false, false);
            arbre.setVoisins(voisins);
            arbre.calculeEtatFutur(null, "ETE");
            if(arbre.getEtatFutur() == 2) compteurArbreEnfeu++;
        }

        assertThat(compteurArbreEnfeu).isCloseTo(9500,within(95));
    }

    /**
     * Méthode de test qui s'assure que l'influence de l'humidité du sol sur le calcul de l'état futur d'un arbre en vie fonctionne correctement.
     */
    @Test
    public void testCalculeEtatFutur_ImpactHumiditeSolSurArbreEnVie() {
        Arbre voisin_gauche = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_haut = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_droite = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_bas = new Arbre(10, 10,  new Grille(), false, false);
        Cellule[] voisins= {voisin_gauche, voisin_haut, voisin_droite, voisin_bas};

        int compteurArbreEnfeu = 0;
        // 1 voisin en feu et sol humide : l'humidité du sol réduit la propagation du feu.
        // Donc environ 75% (7500) des 10000 arbres devraient prendre feu.
        voisins[0].setEtat(2);

        for(int i = 1; i <= 10000; i++){
            Arbre arbre = new Arbre(10, 10,  new Grille(), true, false);
            arbre.setVoisins(voisins);
            arbre.calculeEtatFutur(null, null);
            if(arbre.getEtatFutur() == 2) compteurArbreEnfeu++;
        }

        assertThat(compteurArbreEnfeu).isCloseTo(7500,within(75));
    }

    /**
     * Méthode de test qui s'assure que l'influence de la nature de l'arbre sur le calcul de l'état futur d'un arbre en vie fonctionne correctement.
     */
    @Test
    public void testCalculeEtatFutur_ImpactArbrePeuInflammableSurArbreEnVie() {
        Arbre voisin_gauche = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_haut = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_droite = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_bas = new Arbre(10, 10,  new Grille(), false, false);
        Cellule[] voisins= {voisin_gauche, voisin_haut, voisin_droite, voisin_bas};

        int compteurArbreEnfeu = 0;
        // 1 voisin en feu et arbre peu inflammable : Si l'arbre est peu inflammable, cela réduit la propagation du feu.
        // Donc environ 75% (7500) des 10000 arbres devraient prendre feu.
        voisins[0].setEtat(2);

        for(int i = 1; i <= 10000; i++){
            Arbre arbre = new Arbre(10, 10,  new Grille(), false, true);
            arbre.setVoisins(voisins);
            arbre.calculeEtatFutur(null, null);
            if(arbre.getEtatFutur() == 2) compteurArbreEnfeu++;
        }

        assertThat(compteurArbreEnfeu).isCloseTo(7500,within(75));
    }

    /**
     * Méthode de test qui s'assure que l'influence de la direction du vent sur le calcul de l'état futur d'un arbre
     * en vie fonctionne correctement.
     */
    @Test
    public void testCalculeEtatFutur_ImpactDirectionVentSurArbreEnVie() {
        Arbre voisin_gauche = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_haut = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_droite = new Arbre(10, 10,  new Grille(), false, false);
        Arbre voisin_bas = new Arbre(10, 10,  new Grille(), false, false);
        Cellule[] voisins= {voisin_gauche, voisin_haut, voisin_droite, voisin_bas};

        int compteurArbreEnfeu = 0;
        // 1 voisin en feu et le vent pousse le feu ailleurs que vers l'arbre : Si le vent pousse le feu ailleurs que vers l'arbre, cela réduit la propagation du feu.
        // Donc environ 75% (7500) des 10000 arbres devraient prendre feu.
        voisins[0].setEtat(2);

        for(int i = 1; i <= 10000; i++){
            Arbre arbre = new Arbre(10, 10,  new Grille(), false, false);
            arbre.setVoisins(voisins);
            arbre.calculeEtatFutur("NORD", null);
            if(arbre.getEtatFutur() == 2) compteurArbreEnfeu++;
        }

        assertThat(compteurArbreEnfeu).isCloseTo(4500,within(75));

        compteurArbreEnfeu = 0;
        // 1 voisin en feu et le vent pousse le feu vers l'arbre : Si le vent pousse le feu vers l'arbre, cela augmente la propagation du feu.
        // Donc environ 100% (10000) des 10000 arbres devraient prendre feu.

        for(int i = 1; i <= 10000; i++){
            Arbre arbre = new Arbre(10, 10,  new Grille(), false, false);
            arbre.setVoisins(voisins);
            arbre.calculeEtatFutur("EST", null);
            if(arbre.getEtatFutur() == 2) compteurArbreEnfeu++;
        }

        assertThat(compteurArbreEnfeu).isEqualTo(10000);
    }

    /**
     * Méthode de test qui s'assure que le calcul de l'état futur d'un arbre en feu fonctionne correctement.
     */
    @Test
    public void testCalculeEtatFutur_arbreEnFeu() {
        Grille grille = new Grille();
        Arbre arbre = new Arbre(10, 10,  grille, true, false);
        arbre.setEtat(2);
        arbre.calculeEtatFutur("SUD", "ETE");
        assertThat(arbre.getEtatFutur()).isEqualTo(3);
    }

    /**
     * Méthode de test qui s'assure que le calcul de l'état futur d'un arbre en cendre fonctionne correctement.
     */
    @Test
    public void testCalculeEtatFuture_arbreEnCendre() {
        Grille grille = new Grille();
        Arbre arbre = new Arbre(10, 10,  grille, true, false);
        arbre.setEtat(3);
        arbre.calculeEtatFutur("SUD", "ETE");
        assertThat(arbre.getEtatFutur()).isEqualTo(3);
    }
}
