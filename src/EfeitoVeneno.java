public class EfeitoVeneno implements Efeito {

    private Entidade alvo;
    private int duracao;
    private int dano;

    public EfeitoVeneno(Entidade alvo, int dano, int duracao) {
        this.alvo = alvo;
        this.dano = dano;
        this.duracao = duracao;
    }

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