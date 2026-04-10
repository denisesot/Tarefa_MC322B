public abstract class Entidade {
    protected String nome;
    protected int escudo;
    protected int vida;
    protected int vidaMax;
    protected boolean atordoado = false;
    protected EfeitoQueimadura efeitoQueimadura;
    protected EfeitoVeneno efeitoVeneno;
    protected boolean queimaduraAtiva = false;
    protected boolean venenoAtivo = false;

    public Entidade(String nome, int vidaMax) {
        this.nome = nome;
        this.vidaMax = vidaMax;
        this.vida = vidaMax;
        this.escudo = 0;
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
    public void setAtordoado(boolean status) {
        this.atordoado = status;
    }
    public boolean isAtordoado() {
        return this.atordoado;
    }
    public void setQueimadura(boolean ativa) {
        this.queimaduraAtiva = ativa;
        if (ativa) {
            this.efeitoQueimadura = new EfeitoQueimadura(this); // Instancia o efeito
            System.out.println("🔥 " + this.getNome() + " começou a queimar!");
        } else {
            System.out.println("💧 As chamas em " + this.getNome() + " se apagaram!");
        }
     }
    public void setVeneno(boolean ativo) {
        this.venenoAtivo = ativo;
        if (ativo) {
            this.efeitoVeneno = new EfeitoVeneno(this);
            System.out.println("🧪 " + this.getNome() + " foi envenenado!");
        } else {
            System.out.println("🌿 O veneno em " + this.getNome() + " perdeu o efeito!");
        }
    }
    public void processarEfeitos() {
        if (queimaduraAtiva && efeitoQueimadura != null) {
            efeitoQueimadura.aplicar();
            efeitoQueimadura.reduzirDuracao();
            
            if (efeitoQueimadura.expirou()) {
                setQueimadura(false); 
            }
        }
        if (venenoAtivo && efeitoVeneno != null) {
            efeitoVeneno.aplicar();
            efeitoVeneno.reduzirDuracao();
            
            if (efeitoVeneno.expirou()) {
                setVeneno(false);
            }
        }
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

    public void curar(int valor) {
        this.vida += valor;
        if (this.vida > this.vidaMax) {
            this.vida = this.vidaMax;
        }
    }

    public int getVida() {
        return vida;
    }

    public int getEscudo() {
        return escudo;
    }
}