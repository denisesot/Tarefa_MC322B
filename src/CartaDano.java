// Classe para representar uma carta de dano


public class CartaDano extends Carta {
    private int dano;

    public CartaDano(String nome, String descricao,int custo, int dano) {
        super(nome, descricao, custo);
        this.dano = dano;
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        alvo.receberDano(dano);
        System.out.println(jogador.getNome() + " usou " + getNome() + " e causou " + dano + " de dano em " + alvo.getNome());
    }
}

