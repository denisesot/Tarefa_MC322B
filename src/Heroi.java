public class Heroi {

    private String nome;
    private int escudo;
    private int vida;
    private int mana;
    private int manaMax;

    public Heroi(int vida, String nome) {
        this.escudo =0;
        this.nome=nome;
        this.vida=vida;
        this.manaMax = 3; 
        this.mana = 3;
    }
    public void receberDano(int dano) {
    if (dano>=escudo) {
        dano -=escudo;
        escudo=0;
        vida -= dano;
        if (vida < 0) {
            vida = 0;
        }
    } else {
        escudo -= dano;
    }
    System.out.println(nome + " recebeu dano.");
}
    public void ganharEscudo(int ganho) {
        escudo += ganho;
    }
    public void resetaEscudo() {
        escudo = 0;
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
    public void resetarMana() {
        mana = manaMax;
    }
    public int getMaxMana() {
        return manaMax;
    }
    public String getNome() {
        return nome;
    }
    public int getVida() {
        return vida;
    }
    public int getEscudo() {
        return escudo;
    }
    public boolean estaVivo() { // Corrigido: Método exigido pelo PDF
        if (vida > 0) {
            return true;
        } else {
            return false;
     }
  }
}