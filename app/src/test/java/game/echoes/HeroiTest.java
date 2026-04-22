package game.echoes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeroiTest {

    private Heroi heroi;

    @BeforeEach
    public void setUp() {
        heroi = new Heroi(40, "Silas Vane");
    }

    @Test
    public void testReceberDanoSemEscudo() {
        heroi.receberDano(10);
        assertEquals(30, heroi.getVida());
        assertEquals(0, heroi.getEscudo());
    }

    @Test
    public void testReceberDanoComEscudoAbsorvendoTudo() {
        heroi.ganharEscudo(15);
        heroi.receberDano(10);
        assertEquals(40, heroi.getVida());
        assertEquals(5, heroi.getEscudo());
    }

    @Test
    public void testReceberDanoComEscudoParcial() {
        heroi.ganharEscudo(5);
        heroi.receberDano(15);
        assertEquals(30, heroi.getVida());
        assertEquals(0, heroi.getEscudo());
    }

    @Test
    public void testVidaNaoFicaNegativa() {
        heroi.receberDano(100);
        assertEquals(0, heroi.getVida());
        assertFalse(heroi.estaVivo());
    }

    @Test
    public void testCuraNaoUltrapassaVidaMax() {
        heroi.receberDano(10); 
        heroi.curar(50); 
        assertEquals(40, heroi.getVida()); 
    }

    @Test
    public void testGastarEGanharMana() {
        heroi.gastarMana(2);
        assertEquals(1, heroi.getMana());
        
        heroi.gastarMana(5); 
        assertEquals(0, heroi.getMana()); 
        
        heroi.ganharMana(2);
        assertEquals(2, heroi.getMana());
        
        heroi.ganharMana(10); 
        assertEquals(3, heroi.getMana()); 
    }
}