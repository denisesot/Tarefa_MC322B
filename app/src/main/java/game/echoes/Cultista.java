package game.echoes;

import game.echoes.Heroi;
import game.echoes.Inimigo;

/**
 * Classe que representa um Cultista, um inimigo básico no jogo.
 * 
 * O Cultista é um inimigo com vida moderada que ataca freneticamente, causando dano ao herói.
 * Ele é utilizado como primeiro encontro do jogo.
 */
public class Cultista extends Inimigo {
    
    /**
     * Cria um Cultista com vida inicial de 20.
     */
    public Cultista() {
        super(20, "Cultista");
    }

    /**
     * O Cultista ataca o her\u00f3i freneticamente, causando dano moderado.
     * 
     * @param heroi O her\u00f3i alvo do ataque
     */
    @Override
    public void atacar(Heroi heroi) {
        System.out.println("O Cultista ataca freneticamente!");
        heroi.receberDano(4);
    }
}


