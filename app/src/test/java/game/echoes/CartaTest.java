package game.echoes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartaTest {

    private Heroi heroi;
    private Inimigo inimigo;

    @BeforeEach
    public void setUp() {
        heroi = new Heroi(40, "Silas");
        inimigo = new Cultista(); // Cultista tem 20 de vida
    }

    @Test
    public void testCartaCura() {
        heroi.receberDano(15); // Fica com 25
        CartaCura cura = new CartaCura("Cura", "Cura 10", 1, 10);
        cura.usar(heroi, inimigo);
        assertEquals(35, heroi.getVida());
    }

    @Test
    public void testCartaDano() {
        CartaDano dano = new CartaDano("Dano", "Causa 5", 1, 5);
        dano.usar(heroi, inimigo);
        assertEquals(15, inimigo.getVida());
    }

    @Test
    public void testCartaSacrificio() {
        CartaSacrificio sac = new CartaSacrificio("Pacto", "Pacto", 1, 12, 6);
        sac.usar(heroi, inimigo);
        
        assertEquals(34, heroi.getVida()); // 40 - 6
        assertEquals(8, inimigo.getVida()); // 20 - 12
    }

    @Test
    public void testCartaEscudo() {
        CartaEscudo escudo = new CartaEscudo("Defesa", "Ganha 5", 1, 5);
        escudo.usar(heroi, inimigo);
        assertEquals(5, heroi.getEscudo());
    }
}