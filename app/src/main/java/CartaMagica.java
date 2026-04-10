import java.util.Random;

public class CartaMagica extends Carta {

    public CartaMagica(String nome, String descricao, int custo) {
        super(nome, descricao, custo);
    }

    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        System.out.println("✨ " + jogador.getNome() + " recitou palavras proibidas do tomo!");
        
        Random rand = new Random();
        int sorteio = rand.nextInt(3); // Sorteia um número de 0 a 2

        if (sorteio == 0) {
            alvo.setVeneno(true);
            System.out.println(">> EFEITO SORTEADO: " + alvo.getNome() + " foi envenenado!");
        } else if (sorteio == 1) {
            alvo.setAtordoado(true);
            System.out.println(">> EFEITO SORTEADO: " + alvo.getNome() + " está atordoado!");
        } else {
            alvo.setQueimadura(true);
            System.out.println(">> EFEITO SORTEADO: " + alvo.getNome() + " começou a queimar!");
        }
    }
}