public class EfeitoQueimadura implements Efeito {

    private Entidade alvo;
    private int duracao = 2;

    public EfeitoQueimadura(Entidade alvo) {
        this.alvo = alvo;
    }

    @Override
    public void aplicar() {
        alvo.receberDano(1);
        System.out.println("🔥 Queimadura causou dano!");
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