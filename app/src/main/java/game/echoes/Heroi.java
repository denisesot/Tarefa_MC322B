package game.echoes;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o herói controlado pelo jogador.
 * 
 * O herói possui pontos de vida, mana, ouro e dois baralhos (principal e reserva).
 * Ele pode usar cartas que consomem mana para realizar ações durante o combate.
 * Mantendo persistência entre batalhas, o herói acumula cartas e melhorias ao longo do jogo.
 */

public class Heroi extends Entidade {
    private int mana;
    private int manaMax;
    private List<Carta> baralhoPrincipal;
    private List<Carta> baralhoReserva;
    private int ouro = 50;

    /**
     * Construtor para criar um herói com vida e nome específicos.
     * A mana é inicializada com o valor máximo de 3.
     * 
     * @param vida Vida máxima do herói
     * @param nome Nome do herói
     */
    public Heroi(int vida, String nome) {
        super(nome, vida); 
        this.manaMax = 3; 
        this.mana = 3;
        this.baralhoPrincipal = new ArrayList<>();
        this.baralhoReserva = new ArrayList<>();
        inicializarBaralho(); 
    }

    /**
     * Inicializa o baralho principal do herói com as cartas base do jogo.
     */
    private void inicializarBaralho() {
        for(int i=0; i < 2; i++){
            baralhoPrincipal.add(new CartaChama("Hell's Breath", "Causa 3 de dano e aplica queimadura.", 1, 3));
            baralhoPrincipal.add(new CartaCura("Divine Bless", "Cura 10 de vida.", 1, 10));
            baralhoPrincipal.add(new CartaEnergetica("Energy Potion", "Recupera 2 de mana.", 0, 2));
            baralhoPrincipal.add(new CartaMagica("Chaos", "Aplica um efeito aleatório no alvo.", 2));
            baralhoPrincipal.add(new CartaSacrificio("Blood Pact", "Perde 6 de vida, causa 12 de dano.", 1, 12, 6));
            baralhoPrincipal.add(new CartaDano("Forbidden Blade", "Causa 6 de dano.", 1, 6));
            baralhoPrincipal.add(new CartaEscudo("Ward of Protection", "Ganha 5 de escudo.", 1, 5));
            baralhoPrincipal.add(new CartaCorneta("Corneta de Guerra", "Deixa o inimigo vulnerável.", 1, 2));
            baralhoPrincipal.add(new CartaVeneno("Veneno Ancestral", "Dano por turno", 1, 3, 2));
            baralhoPrincipal.add(new CartaAtordoar("Ritual Sombrio", "Impede ação", 2, 1));
        }
    }

    /**
     * Retorna o baralho principal (em uso) do herói.
     * 
     * @return A lista de cartas do baralho principal
     */
    public List<Carta> getBaralhoPrincipal() {
        return baralhoPrincipal;
    }

    /**
     * Retorna o baralho reserva do herói.
     * 
     * @return A lista de cartas do baralho reserva
     */
    public List<Carta> getBaralhoReserva() {
        return baralhoReserva;
    }

    /**
     * Adiciona uma carta ao baralho principal do herói.
     * 
     * @param carta A carta a ser adicionada
     */
    public void adicionarCarta(Carta carta) {
        baralhoPrincipal.add(carta);
    }

    /**
     * Adiciona uma carta ao baralho reserva do herói.
     * 
     * @param carta A carta a ser adicionada à reserva
     */
    public void adicionarCartaReserva(Carta carta) {
        baralhoReserva.add(carta);
    }

    /**
     * Remove uma carta do baralho principal pelo índice.
     * 
     * @param indice Índice da carta a ser removida
     */
    public void removerCarta(int indice) {
        baralhoPrincipal.remove(indice);
    }

    /**
     * Troca uma carta do baralho principal por uma do baralho reserva.
     * 
     * @param indiceAtual Índice da carta a sair do baralho principal
     * @param indiceReserva Índice da carta a entrar do baralho reserva
     */
    public void trocarCartaComReserva(int indiceAtual, int indiceReserva) {
        Carta cartaSaiu = baralhoPrincipal.remove(indiceAtual);
        Carta cartaEntrou = baralhoReserva.remove(indiceReserva);

        baralhoPrincipal.add(indiceAtual, cartaEntrou);
        baralhoReserva.add(indiceReserva, cartaSaiu);
    }

    /**
     * Retorna a mana atual do herói.
     * 
     * @return A quantidade de mana disponível
     */
    public int getMana() {
        return mana;
    }

    /**
     * Gasta uma quantidade de mana.
     * 
     * @param custo A quantidade de mana a gastar
     */
    public void gastarMana(int custo) {
        mana -= custo;
        if (mana < 0) {
            mana = 0;
        }
    }

    /**
     * Recupera uma quantidade de mana, não ultrapassando o máximo.
     * 
     * @param ganho A quantidade de mana a recuperar
     */
    public void ganharMana(int ganho) {
        this.mana += ganho;
        if (this.mana > this.manaMax) {
            this.mana = this.manaMax;
        }
    }

    /**
     * Retorna a quantidade de ouro do herói.
     * 
     * @return O ouro disponível
     */
    public int getOuro() {
        return ouro;
    }

    /**
     * Adiciona ouro ao herói.
     * 
     * @param quantidade A quantidade de ouro a adicionar
     */
    public void ganharOuro(int quantidade) {
        this.ouro += quantidade;
    }

    /**
     * Tenta gastar uma quantidade de ouro.
     * 
     * @param quantidade A quantidade de ouro a gastar
     * @return true se havia ouro suficiente, false caso contrário
     */
    public boolean gastarOuro(int quantidade) {
        if (this.ouro >= quantidade) {
            this.ouro -= quantidade;
            return true;
        }
        return false;
    }

    /**
     * Restaura a mana para o valor máximo.
     */
    public void resetarMana() {
        mana = manaMax;
    }

    /**
     * Retorna a mana máxima do herói.
     * 
     * @return O valor máximo de mana
     */
    public int getMaxMana() {
        return manaMax;
    }
}
