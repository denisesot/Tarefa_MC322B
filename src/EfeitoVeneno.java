public class EfeitoVeneno extends Efeito {
    public EfeitoVeneno(Entidade dono, int acumulos, Publisher publisher) {
        super("Veneno", dono, acumulos, publisher);
    }
    @Override
    public void Notificacao(String evento) {
        if (evento.equals("FIM_TURNO_" + dono.getNome().toUpperCase())) {
            System.out.println("🧪 [" + dono.getNome() + "] sofreu " + acumulos + " de dano de Veneno!");
            dono.receberDano(acumulos);
            this.acumulos -= 1;
            remover(); 
        }
    }
}