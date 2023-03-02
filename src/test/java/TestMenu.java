import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


public class TestMenu {

    @Test
    public void testGetDensiteDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getDensite()).isEqualTo(50);
    }
    @Test
    public void testGetColonneDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getColonne()).isEqualTo(50);
    }
    @Test
    public void testGetLigneDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getLigne()).isEqualTo(50);
    }
    @Test
    public void testGetHumiditeDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getHumidite()).isEqualTo(50);
    }
    @Test
    public void testDirectionVentDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getDirectionVent()).isEqualTo("NORD");
    }
    @Test
    public void testSaisonDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getSaison()).isEqualTo("PRINTEMPS");
    }
    @Test
    public void testTauxInflammableDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getTauxInflammable()).isEqualTo(50);
    }

}
