public class EfeitoAtordoar implements Efeito {

    private Entidade alvo;
    private int duracao;

    public EfeitoAtordoar(Entidade alvo, int duracao) {
        this.alvo = alvo;
        this.duracao = duracao;
    }

    @Override
    public void aplicar() {
        alvo.setAtordoado(true);
    }

    @Override
    public void reduzirDuracao() {
        duracao--;

        if (duracao <= 0) {
            alvo.setAtordoado(false);
        }
    }

    @Override
    public boolean expirou() {
        return duracao <= 0;
    }
}