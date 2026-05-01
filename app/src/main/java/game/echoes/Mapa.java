package game.echoes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Estrutura de navegação da jornada do jogo.
 * 
 * O mapa organiza os eventos em nós conectados, formando caminhos que o jogador
 * percorre após cada batalha, escolha, loja ou fogueira. A raiz define o ponto
 * inicial da aventura.
 */
public class Mapa {
    
    private NoMapa raiz;

    /**
     * Construtor que inicializa e gera o mapa do jogo.
     */
    public Mapa() {
        gerarMapa();
    }

    /**
     * Monta a estrutura de "árvore" do mapa, criando os nós e ligando-os.
     */
    private void gerarMapa() {//  Criamos os Eventos 
        Evento batalhaInicial = new Batalha(new Cultista());
        Evento eventoAltar = new Escolha();
        Evento loja = new Loja();
        Evento fogueira = new Fogueira();
        Evento batalhaAberracao = new Batalha(new Aberracao());
        Evento batalhaBoss = new Batalha(new CthulhuBoss()); // Colocamos os eventos dentro dos Nos do Mapa
        NoMapa noInicio = new NoMapa(batalhaInicial);
        NoMapa noAltar = new NoMapa(eventoAltar);
        NoMapa noLoja = new NoMapa(loja);
        NoMapa noFogueira = new NoMapa(fogueira);
        NoMapa noAberracao = new NoMapa(batalhaAberracao);
        NoMapa noBossFinal = new NoMapa(batalhaBoss);//  Conectamos os nós para criar a navegação (adicionarCaminho)
        noInicio.adicionarCaminho(noAberracao);
        noInicio.adicionarCaminho(noAltar);
        noAberracao.adicionarCaminho(noLoja);
        noAltar.adicionarCaminho(noFogueira);
        noLoja.adicionarCaminho(noBossFinal);
        noFogueira.adicionarCaminho(noBossFinal);
        this.raiz = noInicio; // 4. Definimos onde o mapa começa
    }

    /**
     * Retorna o primeiro nó do mapa para o Jogo começar a navegar.
     * 
     * @return O nó inicial do mapa
     */
    public NoMapa getRaiz() {
        return raiz;
    }

    /**
     * Retorna os nós organizados por distância a partir da raiz.
     * 
     * @return As camadas do mapa, da origem ao destino final
     */
    public List<List<NoMapa>> getCamadas() {
        List<List<NoMapa>> camadas = new ArrayList<>();
        Set<NoMapa> vistos = new HashSet<>();
        List<NoMapa> camadaAtual = new ArrayList<>();
        camadaAtual.add(raiz);
        vistos.add(raiz);

        while (!camadaAtual.isEmpty()) {
            camadas.add(camadaAtual);
            List<NoMapa> proximaCamada = new ArrayList<>();
            for (NoMapa no : camadaAtual) {
                for (NoMapa proximo : no.getProximos()) {
                    if (vistos.add(proximo)) {
                        proximaCamada.add(proximo);
                    }
                }
            }
            camadaAtual = proximaCamada;
        }
        return camadas;
    }
}
