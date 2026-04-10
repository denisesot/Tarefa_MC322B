public class EfeitoVeneno implements Efeito {

    private Entidade alvo;
    private int duracao = 1;

    public EfeitoVeneno(Entidade alvo) {
        this.alvo = alvo;
    }

    @Override
    public void aplicar() {
        alvo.receberDano(3);
        System.out.println(alvo.getNome() + " sofre 3 de dano por veneno!");
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