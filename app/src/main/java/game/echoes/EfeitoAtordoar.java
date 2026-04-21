package game.echoes;
/**
 * Classe que representa o efeito de atordoamento no jogo.
 * 
 * Este efeito, quando aplicado a uma entidade, impede que ela realize ações por um determinado número de turnos.
 */
public class EfeitoAtordoar implements Efeito {

    private Entidade alvo;
    private int duracao;

    public EfeitoAtordoar(Entidade alvo, int duracao) {
        this.alvo = alvo;
        this.duracao = duracao;
    }
    /**
     * Aplica o estado de atordoamento ao alvo.
     */
    @Override
    public void aplicar() {
        alvo.setAtordoado(true);
    }
    // Reduz a duração do efeito e remove o atordoamento quando a duração expira.
    @Override
    public void reduzirDuracao() {
        duracao--;

        if (duracao <= 0) {
            alvo.setAtordoado(false);
        }
    }
    
     /**
     * Verifica se o efeito de atordoamento expirou.
     * 
     * @return true se o efeito expirou, false caso contrário
     */
    @Override
    public boolean expirou() {
        return duracao <= 0;
    }
}

