public abstract class Efeito implements Subscriber {
    protected String nome;
    protected Entidade dono;
    protected int acumulos;
    protected Publisher publisher;

    public Efeito(String nome, Entidade dono, int acumulos, Publisher publisher) {
        this.nome = nome;
        this.dono = dono;
        this.acumulos = acumulos;
        this.publisher = publisher;
        this.publisher.inscrever(this); // Já se inscreve ao ser criado
    }
    public String getNome() { 
        return nome; 
    }
    public int getAcumulos() { 
        return acumulos; 
    }
    public void adicionarAcumulos(int valor) { 
        this.acumulos += valor; 
    }
    public String getString() {
        return nome + " (" + acumulos + ")";
    }
    protected void remover() {
        if (this.acumulos <= 0) { //remove se 0,os acumulos
            System.out.println("O efeito " + nome + " em " + dono.getNome() + " dissipou-se.");
            dono.removerEfeito(this);
            publisher.desinscrever(this);
        }
    }
}