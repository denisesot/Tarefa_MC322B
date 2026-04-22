package game.echoes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EfeitoTest {

    @Test
    public void testEfeitoAtordoar() {
        Inimigo inimigo = new Cultista();
        Efeito atordoar = new EfeitoAtordoar(inimigo, 2);
        
        inimigo.adicionarEfeito(atordoar);
        
        // Turno 1
        inimigo.aplicarEfeitos();
        assertTrue(inimigo.estaAtordoado());
        assertFalse(atordoar.expirou());
        
        // Turno 2
        inimigo.aplicarEfeitos();
        assertFalse(inimigo.estaAtordoado()); 
        assertTrue(atordoar.expirou());
    }
    
    @Test
    public void testEfeitoVeneno() {
        Inimigo inimigo = new Cultista(); // Vida 20
        Efeito veneno = new EfeitoVeneno(inimigo, 3, 2);
        
        inimigo.adicionarEfeito(veneno);
        inimigo.aplicarEfeitos();
        
        assertEquals(17, inimigo.getVida()); // 20 - 3
    }
}