import org.junit.jupiter.api.Test ;
import static org.assertj.core.api.Assertions.*;

public class TestSolNu {
    /**
     * Méthode de test qui s'assure que l'initialisation de l'état du sol nu fonctionne correctement.
     */
    @Test
    public void testEtat_initialisation(){
        SolNu solNu = new SolNu(5, 5, new Grille());
        assertThat(solNu.getEtat()).isEqualTo(0);
    }

    /**
     * Méthode de test qui s'assure que le calcul de l'état futur du sol nu fonctionne correctement.
     */
    @Test
    public void testCalculEtatFutur(){
        SolNu solNu = new SolNu(5, 5, new Grille());
        solNu.calculeEtatFutur("SUD", "ETE");
        assertThat(solNu.getEtatFutur()).isEqualTo(0);
    }

    /**
     * Méthode de test qui s'assure que le basculement de l'état courant vers l'état futur du sol nu fonctionne correctement.
     */
    @Test
    public void testBasculer(){
        SolNu solNu = new SolNu(10, 10,  new Grille());
        solNu.calculeEtatFutur("NORD", "ETE");
        solNu.basculer();
        assertThat(solNu.getEtat()).isEqualTo(0);
    }
}
