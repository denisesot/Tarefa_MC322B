package game.echoes;
/**
 * Classe que representa o herói controlado pelo jogador.
 * 
 * O herói possui pontos de vida, mana e um nome, além de métodos para gerenciar a mana.
 * Ele pode usar cartas que consomem mana para realizar ações durante o jogo.
 */
public class Heroi extends Entidade {
    private int mana;
    private int manaMax;

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

    public void resetarMana() {
        mana = manaMax;
    }

    public int getMaxMana() {
        return manaMax;
    }
}

