import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


public class TestMenu {

    @Test
    public void testGetDensiteDefault() {
        Menu menu = new Menu(new App());
        assertThat(menu.getDensite()).isEqualTo(50);
    }

}
