package game.echoes;
/**
 * Classe que representa a carta de atordoamento no jogo.
 * 
 * Ao ser utilizada, esta carta aplica um efeito de atordoamento ao inimigo alvo,
 * impedindo-o de agir por um número determinado de turnos.
 */
public class CartaAtordoar extends Carta {

    private int duracao;

    public CartaAtordoar(String nome, String descricao, int custo, int duracao) {
        super(nome, descricao, custo);
        this.duracao = duracao;
    }

        /**
        * Aplica atordoamento ao alvo.
        * 
        * @param jogador herói que usa a carta
        * @param alvo inimigo que receberá o efeito
        */
    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        alvo.adicionarEfeito(new EfeitoAtordoar(alvo, duracao));
        System.out.println("Inimigo atordoado!");
    }
}