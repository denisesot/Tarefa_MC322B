package game.echoes;
import java.util.*;
import game.echoes.Heroi;
import game.echoes.Inimigo;

/**
 * Classe que representa Cthulhu, o chefe final do jogo.
 * 
 * Cthulhu é o inimigo mais poderoso, com mais vida e ataques especiais que podem causar dano cataclísmico.
 * Ocasionalmente realiza um ataque cósmico devastador, representando seu poder incomparável.
 */
public class CthulhuBoss extends Inimigo {
    /**
     * Cria o Cthulhu, o chefe final, com vida inicial de 40.
     */
    public CthulhuBoss() {
        super(40, "Cthulhu");
    }

    /**
     * Cthulhu distorce a realidade, causando sempre 6 de dano.
     * Ocasionalmente (30% de chance), realiza um ataque cósmico devastador de 10 dano adicional.
     * 
     * @param heroi O herói alvo do ataque
     */
    @Override
    public void atacar(Heroi heroi) {
        Random r = new Random();
        

        System.out.println("Cthulhu distorce a realidade!");

        heroi.receberDano(6);

        if (r.nextInt(100) < 30) {
            System.out.println("ATAQUE CÓSMICO!");
            heroi.receberDano(10);
        }
    }
    
}


