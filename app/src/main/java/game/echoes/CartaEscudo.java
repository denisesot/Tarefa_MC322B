package game.echoes;
/**
 * Classe que representa a carta de escudo no jogo.
 * 
 * Esta carta, ao ser utilizada, concede um aumento temporário na defesa do herói que a usa.
 */
public class CartaEscudo extends Carta {

    private int defesa;

    public CartaEscudo(String nome, String descricao, int custo, int defesa) {
        super(nome, descricao, custo);
        this.defesa = defesa;    
    }

    /**
     * Aplica o efeito de escudo ao herói.
     * 
     * @param jogador herói que usa a carta
     * @param alvo inimigo
     */
    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        jogador.ganharEscudo(defesa);
        System.out.println(jogador.getNome() + " usou " + getNome() + " e aumentou seu escudo em " + defesa);
        System.out.println("Escudo total de " + jogador.getNome() + ": " + jogador.getEscudo());
    }
}

