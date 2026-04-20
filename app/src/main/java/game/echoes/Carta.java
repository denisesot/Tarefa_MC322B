package game.echoes;
/**
 * Classe abstrata que representa uma carta do jogo.
 * 
 * Todas as cartas possuem nome, descrição e custo de energia,
 * e definem um comportamento específico ao serem utilizadas
 * durante o turno do jogador.
 */

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

    /**
     * Executa o efeito da carta sobre o alvo.
     * 
     * @param jogador O herói que utiliza a carta
     * @param alvo O inimigo alvo da carta
     */
    public abstract void usar(Heroi jogador, Inimigo alvo);
}


