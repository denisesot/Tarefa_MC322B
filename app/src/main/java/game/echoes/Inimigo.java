package game.echoes;
/**
 * Classe que representa um inimigo no jogo.
 * 
 * O inimigo possui pontos de vida, um nome e um contador de vulnerabilidade.
 * Ele pode atacar o herói, anunciar suas intenções e ser afetado por cartas de status.
 */
public class Inimigo extends Entidade {

    private int vulneravel; 
    public Inimigo(int vida, String nome) {
        super(nome, vida);
        this.vulneravel = 0;
    }

    /**
     * Ataca o herói, causando dano. Se o inimigo estiver atordoado, ele não pode atacar.
     * O dano é aumentado pela vulnerabilidade acumulada.
     * 
     * @param heroi O herói alvo do ataque
     */
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