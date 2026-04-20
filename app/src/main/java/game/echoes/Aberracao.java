package game.echoes;

import java.util.Random;

import game.echoes.Heroi;
import game.echoes.Inimigo;

/** * Classe que representa uma aberração no jogo.
*/
public class Aberracao extends Inimigo {

    public Aberracao() {
        super(25, "Aberração");
    }

    @Override
    public void atacar(Heroi heroi) {
        Random r= new Random();

        if (r.nextBoolean()) {
            System.out.println("A Aberração causa ataque brutal!");
            heroi.receberDano(8);
        } else {
            System.out.println("A Aberração regenera!");
            this.curar(5);
        }
       
    }
    
}
