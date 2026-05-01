package game.echoes;

import java.util.Random;

import game.echoes.Heroi;
import game.echoes.Inimigo;

/**
 * Classe que representa uma Aberração, um inimigo intermediário.
 * 
 * A Aberração é um inimigo versátil que pode atacar brutalmente ou regenerar sua vida.
 * Ela testa a adaptabilidade do jogador em suas estratégias de combate.
 */
public class Aberracao extends Inimigo {

    /**
     * Cria uma Aberração com vida inicial de 25.
     */
    public Aberracao() {
        super(25, "Aberração");
    }

    /**
     * A Aberração pode atacar brutalmente causando 8 de dano ou regenerar 5 de vida.
     * Escolhe aleatoriamente entre as duas ações.
     * 
     * @param heroi O herói alvo do ataque
     */
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
