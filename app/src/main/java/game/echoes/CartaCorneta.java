package game.echoes;
/**
 * Classe que representa a carta "Corneta de Guerra" no jogo.
 * 
 * Esta carta aplica o efeito de vulnerabilidade ao inimigo alvo, aumentando o dano que ele recebe nos próximos ataques.
 */
public class CartaCorneta extends Carta {

    private int vulneravel;

    public CartaCorneta(String nome, String descricao,int custo, int vulneravel) {
        super(nome, descricao, custo);
        this.vulneravel = vulneravel;
    }

    /**
     * Aplica vulnerabilidade ao alvo.
     * 
     * @param heroi O herói que usa a carta
     * @param inimigo O inimigo que receberá o efeito
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        int vulnAplicada = vulneravel;
        String bono = "";
        if (isMelhorada()) {
            vulnAplicada = vulneravel + (vulneravel / 2);
            bono = " [+50% aprimorada!]";
        }
        System.out.println("\n📢 Silas Vane ergue a Corneta de Guerra...");
        System.out.println("Um som antigo ecoa pelos abismos do oceano.");
        System.out.println("As páginas proibidas do tomo vibram com poder." + bono);
        System.out.println("O som reverbera contra a mente de " + inimigo.getNome() + "!");
        inimigo.aplicarVulneravel(vulnAplicada);
        System.out.println(inimigo.getNome() + " está vulnerável!");
        System.out.println("Ele receberá +" + vulnAplicada + " de dano nos próximos ataques.");
    }
}

