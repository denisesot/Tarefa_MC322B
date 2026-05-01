package game.echoes;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GerenciadorDeCartasTest {

    @Test
    void prepararNovoTurnoCompraAteSeisCartas() {
        Heroi heroi = new Heroi(40, "Silas");
        GerenciadorDeCartas gerenciador = new GerenciadorDeCartas(heroi.getBaralhoPrincipal());

        gerenciador.prepararNovoTurno();

        assertEquals(6, gerenciador.getMao().size());
    }

    @Test
    void maoRetornadaNaoPodeSerModificadaPorFora() {
        Heroi heroi = new Heroi(40, "Silas");
        GerenciadorDeCartas gerenciador = new GerenciadorDeCartas(heroi.getBaralhoPrincipal());
        gerenciador.prepararNovoTurno();

        List<Carta> mao = gerenciador.getMao();

        assertThrows(UnsupportedOperationException.class, () -> mao.clear());
    }

    @Test
    void jogarCartaRemoveDaMaoEGastaMana() {
        CartaDano carta = new CartaDano("Golpe", "Causa 4", 1, 4);
        GerenciadorDeCartas gerenciador = new GerenciadorDeCartas(List.of(carta));
        Heroi heroi = new Heroi(40, "Silas");
        Inimigo inimigo = new Cultista();
        gerenciador.prepararNovoTurno();

        gerenciador.jogarCarta(0, heroi, inimigo);

        assertTrue(gerenciador.getMao().isEmpty());
        assertEquals(2, heroi.getMana());
        assertEquals(16, inimigo.getVida());
    }
}
