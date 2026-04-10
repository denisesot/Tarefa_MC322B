public class Inimigo extends Entidade {

    private int vulneravel; 
    public Inimigo(int vida, String nome) {
        super(nome, vida);
        this.vulneravel = 0;
    }
    public void atacar(Heroi heroi) {
        
        if (this.estaAtordoado()) {
        System.out.println("💫 " + this.getNome() + " está atordoado!");
        return;
    }
        int dano = 5; 
        System.out.println(nome + " atacou causando " + dano + " de dano!");
        heroi.receberDano(dano);
    }
    public void anunciarIntencao() {
        System.out.println(">>> INTENÇÃO: " + this.getNome() + " planeja atacar causando 5 de dano neste turno!");
    }
    public void aplicarVulneravel(int valor) {
        this.vulneravel += valor;
    }
}