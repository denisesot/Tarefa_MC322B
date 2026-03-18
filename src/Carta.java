public abstract class Carta {
    protected String nome;
    protected int custo;

    public Carta(String nome, int custo) {
        this.nome = nome;
        this.custo = custo; 
    }

    public String getNome() {
        return nome;
    }

    public int getCusto() {
        return custo;
    }

    public abstract void usar(Heroi jogador, Inimigo alvo);
}