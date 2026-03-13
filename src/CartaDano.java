// Classe para representar uma carta de dano


public class CartaDano {
    private String nome;
    private int custo;
    private int dano;

    public CartaDano(String nome, int custo, int dano) {
        this.nome = nome;
        this.custo = custo;
        this.dano = dano;    
    }

    public void usar(Heroi jogador, Inimigo alvo) {
        if (jogador.getMana() >= custo) {
            jogador.gastarMana(custo);
            alvo.receberDano(dano);
            System.out.println(jogador.getNome() + " usou " + nome + " e causou " + dano + " de dano em " + alvo.getNome());
        } else {
            System.out.println(jogador.getNome() + " não tem mana suficiente para usar " + nome);
        }
    }
}
