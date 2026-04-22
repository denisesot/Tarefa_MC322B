package game.echoes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapaTest {

    @Test
    public void testEstruturaDoMapa() {
        Mapa mapa = new Mapa();
        NoMapa raiz = mapa.getRaiz();

        assertNotNull(raiz);
        assertEquals("Cultista", raiz.getInimigo().getNome());
        
        // A raiz tem 2 caminhos
        assertEquals(2, raiz.getProximos().size());

        NoMapa caminho1 = raiz.getProximos().get(0);
        assertEquals("Aberração", caminho1.getInimigo().getNome());
        
        // O próximo depois do caminho 1 deve ser o Boss
        NoMapa boss = caminho1.getProximos().get(0);
        assertEquals("Cthulhu", boss.getInimigo().getNome());
    }
}
