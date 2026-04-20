package game.echoes;
/**
 * Classe que representa a carta de chama no jogo.
 * 
 * Ao ser utilizada, esta carta causa dano ao inimigo alvo e aplica um efeito de queimadura,
 * que causa dano adicional ao longo dos turnos seguintes.
 */
public class CartaChama extends Carta {

    private int dano;

    public CartaChama(String nome, String descricao, int custo, int dano) {
        super(nome, descricao, custo);
        this.dano = dano;
    }

    /**
     * Aplica dano e queimadura ao alvo.
     * 
     * @param jogador herói que usa a carta
     * @param alvo inimigo que receberá o efeito
     */
    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        alvo.receberDano(dano);
        alvo.adicionarEfeito(new EfeitoQueimadura(alvo));
        System.out.println("Queimadura aplicada!");
    }
}

