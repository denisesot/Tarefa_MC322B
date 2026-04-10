public class EfeitoAtordoar implements Efeito {

    private Entidade alvo;
    private int duracao = 2;

    public EfeitoAtordoar(Entidade alvo) {
        this.alvo = alvo;
    }

    @Override
    public void aplicar() {
        alvo.setAtordoado(true);
        System.out.println(alvo.getNome() + " está atordoado e não pode agir!");
    }

    @Override
    public void reduzirDuracao() {
        duracao--;
        if (duracao <= 0){
            alvo.setAtordoado(false); //remove stun
        }
    }

    @Override
    public boolean expirou() {
        return duracao <= 0;
    }
}