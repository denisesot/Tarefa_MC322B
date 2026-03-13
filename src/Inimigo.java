public class Inimigo {

    private String nome;
    private int escudo;
    private int vida;
    private int vulneravel;

    public Inimigo(int vida, String nome) {
        this.escudo = 0;
        this.nome = nome;
        this.vida = vida;
        this.vulneravel = 0;
    }

    public void receberDano(int dano) {

        if (vulneravel > 0) {
            dano += 2;
        }

        vida -= dano;

        if (vida < 0) {
            vida = 0;
        }

        System.out.println(nome + " recebeu " + dano + " de dano!");

        if (vulneravel > 0) {
            vulneravel--;
        }
    }

    public void atacar(Heroi heroi) {

        int dano = 5;

        System.out.println(nome + " atacou causando " + dano + " de dano!");

        heroi.receberDano(dano);
    }

    public void aplicarVulneravel(int valor) {
        vulneravel += valor;
        System.out.println(nome + " está vulnerável por " + vulneravel + " turnos!");
    }

    public boolean estaVivo() {

        if (vida > 0) {
            return true;
        } else {
            return false;
        }
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

    public int getVulneravel() {
        return vulneravel;
    }
}