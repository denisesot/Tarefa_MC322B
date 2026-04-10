public class CartaCura extends Carta {
    private int cura;

    public CartaCura(String nome, String descricao, int custo, int cura) {
        super(nome, descricao, custo);
        this.cura = cura;
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        jogador.curar(cura); 
        System.out.println("💚 " + jogador.getNome() + " usou " + getNome() + " e recuperou " + cura + " de vida!");
        System.out.println("Vida atual: " + jogador.getVida());
    }
}