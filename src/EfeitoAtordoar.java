public class EfeitoAtordoar extends Efeito {
    public EfeitoAtordoar(Entidade dono, int acumulos, Publisher publisher) {
        super("Atordoar", dono, acumulos, publisher);
        dono.setAtordoado(true);
    }
    @Override
    public void Notificacao(String evento) {
        if (evento.equals("FIM_TURNO_" + dono.getNome().toUpperCase())) {
            this.acumulos -= 1;
            remover();
        }
    }
    @Override
    protected void remover() {
        if (this.acumulos <= 0) {
            dono.setAtordoado(false); 
            super.remover();
        }
    }
 }