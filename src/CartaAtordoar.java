public class CartaAtordoar extends Carta {

    public CartaAtordoar(String nome, String descricao, int custo) {
        super(nome, descricao, custo);
    }

    @Override
    public void usar(Entidade jogador, Entidade alvo) {
        alvo.setAtordoado(true);

        System.out.println(jogador.getNome() + " atordoou " + alvo.getNome());
    }
}