// Classe para representar uma carta de dano


public class CartaDano extends Carta {
    private int dano;

    public CartaDano(String nome, int custo, int dano) {
        super(nome, custo);
        this.dano = dano;
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        if (jogador.getMana() >= getCusto()) {
            jogador.gastarMana(getCusto());
            alvo.receberDano(dano);
            System.out.println(jogador.getNome() + " usou " + getNome() + " e causou " + dano + " de dano em " + alvo.getNome());
        } else {
            System.out.println(jogador.getNome() + " não tem mana suficiente para usar " + getNome());
        }
    }

}
