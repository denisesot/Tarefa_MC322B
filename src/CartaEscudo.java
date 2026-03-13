// Classe para representar uma carta de escudo

public class CartaEscudo {
    private String nome;
    private int custo;
    private int defesa;

    public CartaEscudo(String nome, int custo, int defesa) {
        this.nome = nome;
        this.custo = custo;
        this.defesa = defesa;    
    }

    public void usar(Heroi jogador) {
        if (jogador.getMana() >= custo) {
            jogador.gastarMana(custo);
            jogador.ganharEscudo(defesa);
            System.out.println(jogador.getNome() + " usou " + nome + " e aumentou seu escudo em " + defesa);

        } else {
            System.out.println(jogador.getNome() + " não tem mana suficiente para usar " + nome);
        }
    }
}
