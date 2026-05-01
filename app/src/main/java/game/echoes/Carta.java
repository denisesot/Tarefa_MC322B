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
    protected boolean melhorada = false;

    public Carta(String nome, String descricao,int custo) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo; 
    }

    /**
     * Retorna o nome da carta.
     * 
     * @return O nome desta carta
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a descrição da carta.
     * 
     * @return A descrição desta carta
     */
    public String getDescricao(){
        return descricao;
    }    

    /**
     * Retorna o custo de mana para usar esta carta.
     * 
     * @return O custo em mana desta carta
     */
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

    /**
     * Aprimora esta carta, aumentando seu desempenho.
     * 
     * @return true se a carta foi aprimorada com sucesso, false se já estava aprimorada
     */
    public boolean aprimorar() {
        if (melhorada) {
            return false;
        }
        this.melhorada = true;
        return true;
    }

    /**
     * Verifica se esta carta foi aprimorada.
     * 
     * @return true se a carta está aprimorada, false caso contrário
     */
    public boolean isMelhorada() {
        return melhorada;
    }
}
