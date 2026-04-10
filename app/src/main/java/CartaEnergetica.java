public class CartaEnergetica extends Carta {
    private int energiaExtra;

    public CartaEnergetica(String nome, String descricao, int custo, int energiaExtra) {
        super(nome, descricao, custo);
        this.energiaExtra = energiaExtra;
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        jogador.ganharMana(energiaExtra);
        System.out.println("⚡ " + jogador.getNome() + " sentiu um surto de adrenalina!");
        System.out.println("Recuperou +" + energiaExtra + " de Mana! (Mana atual: " + jogador.getMana() + ")");
    }
}
