package game.echoes;

public class Mapa {

    private NoMapa raiz;

    public Mapa() {
        montarMapa();
    }

    private void montarMapa() {

        // Fase 1
        NoMapa inicio = new NoMapa(new Cultista());

        // Fase 2 (escolha)
        NoMapa caminho1 = new NoMapa(new Aberracao());
        NoMapa caminho2 = new NoMapa(new Cultista());

        // Boss
        NoMapa boss = new NoMapa(new CthulhuBoss());

        // Ligações
        inicio.adicionarCaminho(caminho1);
        inicio.adicionarCaminho(caminho2);

        caminho1.adicionarCaminho(boss);
        caminho2.adicionarCaminho(boss);

        this.raiz = inicio;
    }

    public NoMapa getRaiz() {
        return raiz;
    }
}

