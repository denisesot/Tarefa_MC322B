package game.echoes;

import java.util.List;

/**
 * Classe que representa o herói controlado pelo jogador.
 * 
 * O herói possui pontos de vida, mana e um nome, além de métodos para gerenciar a mana.
 * Ele pode usar cartas que consomem mana para realizar ações durante o jogo.
 * /modificacoes q fiz:coloquei o baralho aqui para guardar as cartas previas do jogo,  já q a cada luta o heroi recomeçava com o baralho padrão
 */

public class Heroi extends Entidade {
    private int mana;
    private int manaMax;
    private List<Carta> baralhoPrincipal; //novo baralho permanente
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
    }

    this.baralhoPrincipal = new ArrayList<>();
        inicializarBaralho(); 
    }
    private void inicializarBaralho() { //inicializador do baralho movido pra cara
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
    public List<Carta> getBaralhoPrincipal() { //metodos para adicionar e pegar cartas nas duas proximas funções
        return baralhoPrincipal;
    }

    public void adicionarCarta(Carta carta) {
        baralhoPrincipal.add(carta);
    }

    public int getMana() {
        return mana;
    }
    public void gastarMana(int custo) {
        mana -= custo;
        if (mana < 0) {
            mana = 0;
        }
    }

    public void ganharMana(int ganho) {
        this.mana += ganho;
        if (this.mana > this.manaMax) {
            this.mana = this.manaMax;
        }
    }

    public int getOuro() {
        return ouro;
    }

    public void ganharOuro(int quantidade) {
        this.ouro += quantidade;
    }

    public boolean gastarOuro(int quantidade) {
        if (this.ouro >= quantidade) {
            this.ouro -= quantidade;
            return true;
        }
        return false;
    }

    public void resetarMana() {
        mana = manaMax;
    }

    public int getMaxMana() {
        return manaMax;
    }
}

