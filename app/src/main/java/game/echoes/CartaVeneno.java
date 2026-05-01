package game.echoes;
/**
 * Classe que representa a carta de veneno no jogo.
 * 
 * Ao ser utilizada, esta carta aplica um efeito de veneno ao inimigo alvo,
 * causando dano contínuo por um número determinado de turnos.
 */
public class CartaVeneno extends Carta {

    private int dano;
    private int duracao;

    public CartaVeneno(String nome, String descricao, int custo, int dano, int duracao) {
        super(nome, descricao, custo);
        this.dano = dano;
        this.duracao = duracao;
    }


    /**
     * Aplica veneno ao alvo.
     * 
     * @param jogador herói que usa a carta
     * @param alvo inimigo que receberá o efeito
     */
    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        int danoAplicado = dano;
        int duracaoAplicada = duracao;
        String bono = "";
        if (isMelhorada()) {
            danoAplicado = dano + (dano / 2);
            duracaoAplicada = duracao + 1;
            bono = " [+50% dano, +1 turno aprimorada!]";
        }
        alvo.adicionarEfeito(new EfeitoVeneno(alvo, danoAplicado, duracaoAplicada));
        System.out.println("🤬 Veneno aplicado ao " + alvo.getNome() + "!" + bono);
    }
}

