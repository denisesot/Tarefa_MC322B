public class CartaSacrificio extends Carta {
    private int dano;
    private int perdeVida;

    public CartaSacrificio(String nome, String descricao, int custo, int dano, int perdeVida) {
        super(nome, descricao, custo);
        this.dano = dano;
        this.perdeVida = perdeVida;
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        jogador.receberDano(perdeVida);
        alvo.receberDano(dano);
        
        System.out.println("🩸 " + jogador.getNome() + " pagou com o próprio sangue (" + perdeVida + " de vida)!");
        System.out.println("O ataque brutal causou " + dano + " de dano em " + alvo.getNome() + "!");
    }
}