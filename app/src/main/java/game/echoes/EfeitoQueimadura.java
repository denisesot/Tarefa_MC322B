package game.echoes;
/**
 * Classe que representa o efeito de queimadura no jogo.
 * 
 * Este efeito, quando aplicado a uma entidade, causa dano ao longo de vários turnos.
 */
public class EfeitoQueimadura implements Efeito {

    private Entidade alvo;
    private int duracao = 2;

    public EfeitoQueimadura(Entidade alvo) {
        this.alvo = alvo;
    }
    /**
    * Aplica o efeito de queimadura, causando dano ao alvo.
    */
    @Override
    public void aplicar() {
        alvo.receberDano(1);
        System.out.println("🔥 Queimadura causou dano!");
    }
    // Reduz a duração do efeito e remove a queimadura quando a duração expira.
    @Override
    public void reduzirDuracao() {
        duracao--;
    }
    /**
     * Verifica se o efeito de queimadura expirou.
     * 
     * @return true se o efeito expirou, false caso contrário
     */
    @Override
    public boolean expirou() {
        return duracao <= 0;
    }
}


