public class CartaVeneno extends Carta {

    private int dano;
    private int duracao;

    public CartaVeneno(String nome, String descricao, int custo, int dano, int duracao) {
        super(nome, descricao, custo);
        this.dano = dano;
        this.duracao = duracao;
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        alvo.adicionarEfeito(new EfeitoVeneno(alvo, dano, duracao));
        System.out.println("Veneno aplicado!");
    }
}