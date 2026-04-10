public class Heroi extends Entidade {
    private int mana;
    private int manaMax;

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