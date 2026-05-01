package game.echoes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BaralhoHeroiTest {

    private Heroi heroi;

    @BeforeEach
    void setUp() {
        heroi = new Heroi(40, "Silas Vane");
    }

    @Test
    void heroiComecaComBaralhoPrincipalEReservaVazia() {
        assertEquals(20, heroi.getBaralhoPrincipal().size());
        assertTrue(heroi.getBaralhoReserva().isEmpty());
    }

    @Test
    void adicionarCartaReservaGuardaCartaSemAlterarBaralhoPrincipal() {
        Carta carta = new CartaDano("Teste", "Causa dano", 1, 4);

        heroi.adicionarCartaReserva(carta);

        assertEquals(20, heroi.getBaralhoPrincipal().size());
        assertEquals(1, heroi.getBaralhoReserva().size());
        assertSame(carta, heroi.getBaralhoReserva().get(0));
    }

    @Test
    void trocarCartaComReservaMantemTamanhoDosBaralhos() {
        Carta reserva = new CartaCura("Reserva", "Cura", 1, 5);
        Carta primeiraCartaPrincipal = heroi.getBaralhoPrincipal().get(0);
        heroi.adicionarCartaReserva(reserva);

        heroi.trocarCartaComReserva(0, 0);

        assertEquals(20, heroi.getBaralhoPrincipal().size());
        assertEquals(1, heroi.getBaralhoReserva().size());
        assertSame(reserva, heroi.getBaralhoPrincipal().get(0));
        assertSame(primeiraCartaPrincipal, heroi.getBaralhoReserva().get(0));
    }

    @Test
    void removerCartaDiminuiBaralhoPrincipal() {
        Carta removida = heroi.getBaralhoPrincipal().get(0);

        heroi.removerCarta(0);

        assertEquals(19, heroi.getBaralhoPrincipal().size());
        assertTrue(!heroi.getBaralhoPrincipal().contains(removida));
    }
}
