public class CartaChama extends Carta {
    private int danoInicial;

    public CartaChama(String nome, String descricao, int custo, int danoInicial) {
        super(nome, descricao, custo);
        this.danoInicial = danoInicial;
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        alvo.receberDano(danoInicial);
        System.out.println(jogador.getNome() + " usou " + getNome() + " e causou " + danoInicial + " de dano!");
        alvo.setQueimadura(true);
    }
}
