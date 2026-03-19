public abstract class Entidade {
    protected String nome;
    protected int escudo;
    protected int vida;

    public Entidade(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = 0;
    }

    public void receberDano(int dano) {
        if (dano >= escudo) {
            dano -= escudo;
            escudo = 0;
            vida -= dano;
            if (vida < 0) {
                vida = 0;
            }
        } else {
            escudo -= dano;
        }
    }
    public void ganharEscudo(int ganho) {
        escudo += ganho;
    }

    public void resetaEscudo() {
        escudo = 0;
    }

    public boolean estaVivo() { 
        return vida > 0;
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
}