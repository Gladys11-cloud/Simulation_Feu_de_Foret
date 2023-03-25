import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TestMenu {
    /**
     * Méthode de test qui s'assure que la méthode Menu.getDensite() fonctionne correctement.
     */
    @Test
    public void testGetDensiteDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getDensite()).isEqualTo(95);
    }

    /**
     * Méthode de test qui s'assure que la méthode Menu.getColonnes() fonctionne correctement.
     */
    @Test
    public void testGetColonneDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getColonnes()).isEqualTo(50);
    }

    /**
     * Méthode de test qui s'assure que la méthode Menu.getLignes() fonctionne correctement.
     */
    @Test
    public void testGetLigneDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getLignes()).isEqualTo(50);
    }

    /**
     * Méthode de test qui s'assure que la méthode Menu.getTauxCellulesHumides() fonctionne correctement.
     */
    @Test
    public void testGetHumiditeDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getTauxCellulesHumides()).isEqualTo(5);
    }

    /**
     * Méthode de test qui s'assure que la méthode Menu.getDirectionVent() fonctionne correctement.
     */
    @Test
    public void testDirectionVentDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getDirectionVent()).isEqualTo("INDIFFERENT");
    }

    /**
     * Méthode de test qui s'assure que la méthode Menu.getSaison() fonctionne correctement.
     */
    @Test
    public void testSaisonDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getSaison()).isEqualTo("INDIFFERENT");
    }

    /**
     * Méthode de test qui s'assure que la méthode Menu.getTauxArbresPeuInflammable() fonctionne correctement.
     */
    @Test
    public void testTauxInflammableDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getTauxArbresPeuInflammable()).isEqualTo(1);
    }
}
