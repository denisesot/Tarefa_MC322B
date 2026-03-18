// Classe para representar uma carta de escudo

public class CartaEscudo extends Carta {

    private int defesa;

    public CartaEscudo(String nome, int custo, int defesa) {
        super(nome, custo);
        this.defesa = defesa;    
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        if (jogador.getMana() >= getCusto()) {
            jogador.gastarMana(getCusto());
            jogador.ganharEscudo(defesa);
            System.out.println(jogador.getNome() + " usou " + getNome() + " e aumentou seu escudo em " + defesa);
            System.out.println("Escudo total de " + jogador.getNome() + ": " + (jogador.getEscudo() + defesa));
        } else {
            System.out.println(jogador.getNome() + " não tem mana suficiente para usar " + getNome());
        }
    }
}
