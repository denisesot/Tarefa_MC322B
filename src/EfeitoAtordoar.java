public class EfeitoAtordoar implements Efeito {

    private Entidade alvo;
    private int duracao = 1;

    public EfeitoAtordoar(Entidade alvo) {
        this.alvo = alvo;
    }

    @Override
    public void aplicar() {
        alvo.setAtordoado(true);
        System.out.println(alvo.getNome() + " está atordoado!");
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