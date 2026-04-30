package game.echoes;

import main.java.game.echoes.Escolha;

public class Mapa {
    
    private NoMapa raiz;

    public Mapa() {
        gerarMapa();
    }

    /**
     * Monta a estrutura de "árvore" do mapa, criando os nós e ligando-os.
     */
    private void gerarMapa() {//  Criamos os Eventos 
        Evento batalhaInicial = new Batalha(new Cultista());
        Evento eventoAltar = new Escolha();
        Evento batalhaAberracao = new Batalha(new Aberracao());
        Evento batalhaBoss = new Batalha(new CthulhuBoss()); // Colocamos os eventos dentro dos Nos do Mapa
        NoMapa noInicio = new NoMapa(batalhaInicial);
        NoMapa noCaminhoEsquerdo = new NoMapa(eventoAltar);
        NoMapa noCaminhoDireito = new NoMapa(batalhaAberracao);
        NoMapa noBossFinal = new NoMapa(batalhaBoss);//  Conectamos os nós para criar a navegação (adicionarCaminho)
        // A partir do início, abrimos dois caminhos para o jogador escolher:
        noInicio.adicionarCaminho(noCaminhoEsquerdo); // Opção 1: Ir para o Altar
        noInicio.adicionarCaminho(noCaminhoDireito);  // Opção 2: Lutar contra Aberração
        noCaminhoEsquerdo.adicionarCaminho(noBossFinal);// Independentemente do caminho escolhido, ambos levam ao Boss Final:
        noCaminhoDireito.adicionarCaminho(noBossFinal);
        this.raiz = noInicio; // 4. Definimos onde o mapa começa
    }

    /**
     * Retorna o primeiro nó do mapa para o Jogo começar a navegar.
     */
    public NoMapa getRaiz() {
        return raiz;
    }
}

