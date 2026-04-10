public class EfeitoQueimadura implements Efeito {
    private Entidade alvo;
    private int duracao = 2;

    public EfeitoQueimadura(Entidade alvo) {
        this.alvo = alvo;
    }

    @Override
    public void aplicar() {
        alvo.receberDano(1);
        System.out.println("🔥 " + alvo.getNome() + " sofreu 1 de dano por queimadura!");
    }

    @Override
    public void reduzirDuracao() {
        duracao--;
    }

    @Override
    public boolean expirou() {
        return duracao <= 0;
    }
    public void resetarDuracao() { //caso o efeito seja aplicado denovo
        this.duracao = 2;
    }
}