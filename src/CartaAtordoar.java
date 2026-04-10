public class CartaAtordoar extends Carta {

    public CartaAtordoar(String nome, String descricao, int custo) {
        super(nome, descricao, custo);
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        alvo.adicionarEfeito(new EfeitoAtordoar(alvo));
        System.out.println(jogador.getNome() + " atordoou " + alvo.getNome());
    }
}