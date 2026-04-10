public abstract class Entidade {
    protected String nome;
    protected int escudo;
    protected int vida;
    protected List<Efeito> efeitos;
    protected boolean atordoado;

    public Entidade(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = 0;
        this.efeitos = new ArrayList<>();
        this.atordoado = false;
    }
    public void aplicarEfeito(Efeito novoEfeito) {
        for (Efeito e : efeitos) {
            if (e.getNome().equals(novoEfeito.getNome())) {
                e.adicionarAcumulos(novoEfeito.getAcumulos());
                return; // Se já tem, só soma e sai
            }
        }
        efeitos.add(novoEfeito); // Se não tem, adiciona
    }
    public void removerEfeito(Efeito e) {
        efeitos.remove(e);
    }
    public boolean isAtordoado() {
    return atordoado;
    }
    public void setAtordoado(boolean status) {
    this.atordoado = status;
    }
    public void exibirEfeitos() {
        if (efeitos.size() > 0) {
            System.out.print("Efeitos: ");
            for (Efeito e : efeitos) {
                System.out.print("[" + e.getString() + "] ");
            }
            System.out.println();
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
}