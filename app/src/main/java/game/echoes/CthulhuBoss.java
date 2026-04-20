package game.echoes;
import java.util.*;
import game.echoes.Heroi;
import game.echoes.Inimigo;

public class CthulhuBoss extends Inimigo {
    public CthulhuBoss() {
        super(40, "Cthulhu");
    }

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


