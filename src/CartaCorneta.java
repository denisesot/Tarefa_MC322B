public class CartaCorneta {

    private String nome;
    private int custo;
    private int vulneravel;

    public CartaCorneta(String nome, int custo, int vulneravel) {
        this.nome = nome;
        this.custo = custo;
        this.vulneravel = vulneravel;
    }

    public void usar(Heroi heroi, Inimigo inimigo) {

        if (heroi.getMana() < custo) {
            System.out.println("Energia insuficiente para usar " + nome + "!");
            return;
        }

        heroi.gastarMana(custo);

        System.out.println("\n📯 Silas Vane ergue a Corneta de Guerra...");
        System.out.println("Um som antigo ecoa pelos abismos do oceano.");
        System.out.println("As páginas proibidas do tomo vibram com poder.");
        System.out.println("O som reverbera contra a mente de " + inimigo.getNome() + "!");

      
      inimigo.aplicarVulneravel(vulneravel);

        System.out.println(inimigo.getNome() + " está vulnerável!");
        System.out.println("Ele receberá +2 de dano nos próximos ataques.");
    }
}