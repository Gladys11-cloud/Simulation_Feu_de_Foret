
import org.junit.jupiter.api.Test ;
import static org.assertj.core.api.Assertions .*;
public class TestSolNu {
    @Test
    public void testEtat_initialisation(){
        SolNu solNu = new SolNu(10, 10, 5, 5, new Grille(new App()));
        assertThat(solNu.etat).isEqualTo(0);
    }
    @Test
    public void testCalculEtatFutur(){
        SolNu solNu = new SolNu(10, 10, 5, 5, new Grille(new App()));
        solNu.calculeEtatFutur();
        assertThat(solNu.etat_futur).isEqualTo(0);
    }
    @Test
    public void testBasculer(){
        SolNu solNu = new SolNu(10, 10, 5, 5, new Grille(new App()));
        solNu.etat=0;
        solNu.basculer();
        assertThat(solNu.etat_futur).isEqualTo(0);
    }
}
