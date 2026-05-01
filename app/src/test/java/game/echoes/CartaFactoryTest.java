package game.echoes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CartaFactoryTest {

    @Test
    void criarCartaPorTipoRetornaTipoEsperado() {
        assertInstanceOf(CartaDano.class, CartaFactory.criarCartaPorTipo(0));
        assertInstanceOf(CartaEscudo.class, CartaFactory.criarCartaPorTipo(1));
        assertInstanceOf(CartaVeneno.class, CartaFactory.criarCartaPorTipo(2));
        assertInstanceOf(CartaChama.class, CartaFactory.criarCartaPorTipo(3));
        assertInstanceOf(CartaCura.class, CartaFactory.criarCartaPorTipo(4));
        assertInstanceOf(CartaEnergetica.class, CartaFactory.criarCartaPorTipo(5));
        assertInstanceOf(CartaMagica.class, CartaFactory.criarCartaPorTipo(6));
        assertInstanceOf(CartaSacrificio.class, CartaFactory.criarCartaPorTipo(7));
        assertInstanceOf(CartaAtordoar.class, CartaFactory.criarCartaPorTipo(8));
    }

    @Test
    void criarCartaAleatoriaSempreRetornaCarta() {
        for (int i = 0; i < 20; i++) {
            assertNotNull(CartaFactory.criarCartaAleatoria());
        }
    }
}
