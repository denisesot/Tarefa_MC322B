import java.util.*;

public abstract class Entidade {
    protected String nome;
    protected int escudo;
    protected int vida;
  
    protected List<Efeito> efeitos = new ArrayList<>();

    public Entidade(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = 0;
    }

    public void adicionarEfeito(Efeito efeito) {
        efeitos.add(efeito);
    }

    public void aplicarEfeitos() {
        Iterator<Efeito> it = efeitos.iterator();

        while (it.hasNext()) {
            Efeito e = it.next();
            e.aplicar();
            e.reduzirDuracao();

            if (e.expirou()) {
                it.remove();
            }
        }
    }

    public void receberDano(int dano) {
        if (dano >= escudo) {
            dano -= escudo;
            escudo = 0;
            vida -= dano;
            if (vida < 0) {
                vida = 0;
            }
        } else {
            escudo -= dano;
        }
    }
    public void ganharEscudo(int ganho) {
        escudo += ganho;
    }

    public void resetaEscudo() {
        escudo = 0;
    }

    public boolean estaVivo() { 
        return vida > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getEscudo() {
        return escudo;
    }

    protected boolean atordoado = false;

    public boolean estaAtordoado() {
        return atordoado;
    }

    public void setAtordoado(boolean valor) {
        atordoado = valor;
    }
}