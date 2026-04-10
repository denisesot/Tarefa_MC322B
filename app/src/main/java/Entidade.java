import java.util.*;

public abstract class Entidade {
    protected String nome;
    protected int escudo;
    protected int vida;
    protected int vidaMax;
    
    protected boolean atordoado = false;

    protected List<Efeito> efeitos = new ArrayList<>();

    public Entidade(String nome, int vidaMax) {
        this.nome = nome;
        this.vidaMax = vidaMax;
        this.vida = vidaMax;
        this.escudo = 0;
    }


    // OBSERVER
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

    //DANO
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

    //ESCUDO
    public void ganharEscudo(int ganho) {
        escudo += ganho;
    }

    public void resetaEscudo() {
        escudo = 0;
    }

    //CURA
    public void curar(int valor) {
        this.vida += valor;
        if (this.vida > this.vidaMax) {
            this.vida = this.vidaMax;
        }
    }

    //STATUS: ATORDOADO
    public boolean estaAtordoado() {
        return atordoado;
    }
    public void setAtordoado(boolean status) {
        this.atordoado = status;
    }
   
   // STATUS
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
}   