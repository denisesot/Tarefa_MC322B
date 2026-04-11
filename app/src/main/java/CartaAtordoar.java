public class CartaAtordoar extends Carta {

    private int duracao;

    public CartaAtordoar(String nome, String descricao, int custo, int duracao) {
        super(nome, descricao, custo);
        this.duracao = duracao;
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        alvo.adicionarEfeito(new EfeitoAtordoar(alvo, duracao));
        System.out.println("Inimigo atordoado!");
    }
}