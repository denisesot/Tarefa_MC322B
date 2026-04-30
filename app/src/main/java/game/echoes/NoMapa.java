package game.echoes;
import java.util.ArrayList;
import java.util.List;

public class NoMapa {

    private Evento evento;
    private List<NoMapa> proximos = new ArrayList<>();

    public NoMapa(Evento evento) {
        this.evento = evento;
    }

    public Evento getEvento() {
        return evento;
    }

    public List<NoMapa> getProximos() {
        return proximos;
    }

    public void adicionarCaminho(NoMapa no) {
        proximos.add(no);
    }
}


