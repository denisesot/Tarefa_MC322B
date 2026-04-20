package game.echoes;
import java.util.*;

public class NoMapa {

    private Inimigo inimigo;
    private List<NoMapa> proximos = new ArrayList<>();

    public NoMapa(Inimigo inimigo) {
        this.inimigo = inimigo;
    }

    public Inimigo getInimigo() {
        return inimigo;
    }

    public List<NoMapa> getProximos() {
        return proximos;
    }

    public void adicionarCaminho(NoMapa no) {
        proximos.add(no);
    }
}


