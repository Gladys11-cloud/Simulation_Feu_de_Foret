import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestApp {
    /**
     * Méthode de test qui s'assure que les méthodes App.setTauxForetDejaBrulee() et App.getTauxForetDejaBrulee() fonctionnent correctement.
     */
    @Test
    public void testTauxforetbrulee() {
        App app = new App();
        app.setTauxForetDejaBrulee(50.5688);
        assertThat(app.getTauxForetDejaBrulee()).isEqualTo(50.5688);
    }
}
