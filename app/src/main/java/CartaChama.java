public class CartaChama extends Carta {

    private int dano;

    public CartaChama(String nome, String descricao, int custo, int dano) {
        super(nome, descricao, custo);
        this.dano = dano;
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        alvo.receberDano(dano);
        alvo.adicionarEfeito(new EfeitoQueimadura(alvo));
        System.out.println("Queimadura aplicada!");
    }
}