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

        NoMapa caminho2 = raiz.getProximos().get(1);
        assertEquals("Altar Profano", caminho2.getEvento().getNomeEvento());
        
        NoMapa loja = caminho1.getProximos().get(0);
        assertEquals("Loja do Arquivista", loja.getEvento().getNomeEvento());

        NoMapa fogueira = caminho2.getProximos().get(0);
        assertEquals("Fogueira", fogueira.getEvento().getNomeEvento());

        NoMapa bossDepoisDaLoja = loja.getProximos().get(0);
        assertEquals("Cthulhu", bossDepoisDaLoja.getInimigo().getNome());

        NoMapa bossDepoisDaFogueira = fogueira.getProximos().get(0);
        assertEquals("Cthulhu", bossDepoisDaFogueira.getInimigo().getNome());
    }

    @Test
    public void testCamadasDoMapa() {
        Mapa mapa = new Mapa();

        assertEquals(4, mapa.getCamadas().size());
        assertEquals(1, mapa.getCamadas().get(0).size());
        assertEquals(2, mapa.getCamadas().get(1).size());
        assertEquals(2, mapa.getCamadas().get(2).size());
        assertEquals(1, mapa.getCamadas().get(3).size());
    }

    @Test
    public void testNoMapaVisitado() {
        NoMapa no = new NoMapa(new Escolha());

        assertFalse(no.foiVisitado());

        no.marcarVisitado();

        assertTrue(no.foiVisitado());
    }
}
