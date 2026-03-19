public abstract class Carta {
    protected String nome;
    protected String descricao;
    protected int custo;

    public Carta(String nome, String descricao,int custo) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo; 
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }    

    public int getCusto() {
        return custo;
    }

    public abstract void usar(Heroi jogador, Inimigo alvo);
}