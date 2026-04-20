package game.echoes;

import game.echoes.Heroi;
import game.echoes.Inimigo;

public class Cultista extends Inimigo {
    
    public Cultista() {
        super(20, "Cultista");
    }

    @Override
    public void atacar(Heroi heroi) {
        System.out.println("O Cultista ataca freneticamente!");
        heroi.receberDano(4);
    }
}


