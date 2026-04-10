public class CartaVeneno extends Carta {

    private int dano;

    public CartaVeneno(String nome, String descricao, int custo, int dano) {
        super(nome, descricao, custo);
        this.dano = dano;
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        alvo.setVeneno(true);
        System.out.println(jogador.getNome() + " aplicou veneno em " + alvo.getNome());
    }
}