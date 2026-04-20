package game.echoes;
/**
 * Classe que representa o efeito de veneno aplicado a uma entidade.
 * 
 * O veneno causa dano contínuo por um número determinado de turnos.
 * A cada turno, o alvo sofre dano e a duração do efeito é reduzida.
 * O efeito expira quando a duração chega a zero.
 */
public class EfeitoVeneno implements Efeito {

    private Entidade alvo;
    private int dano;
    private int duracao;

    public EfeitoVeneno(Entidade alvo, int dano, int duracao) {
        this.alvo = alvo;
        this.dano = dano;
        this.duracao = duracao;
    }

    
    /**
     * Aplica o estado de atordoamento ao alvo.
     */
    
    @Override
    public void aplicar() {
        alvo.receberDano(dano);
        System.out.println(alvo.getNome() + " sofre " + dano + " de dano por veneno!");
    }

    @Override
    public void reduzirDuracao() {
        duracao--;
    }

    @Override
    public boolean expirou() {
        return duracao <= 0;
    }
}


