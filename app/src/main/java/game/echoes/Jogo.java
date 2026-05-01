package game.echoes;

import java.util.*;

/**
 * Classe principal que gerencia o fluxo do jogo.
 * 
 * O Jogo é responsável por criar o herói, o mapa, e executar o loop principal
 * que navega pelos eventos do mapa até que o herói morra ou vena o jogo.
 */
public class Jogo {

    private Heroi heroi;
    private Mapa mapa;

    /**
     * Construtor que inicializa o jogo com um herói e um mapa.
     */
    public Jogo() {
        heroi = new Heroi(40, "Silas Vane");
        mapa = new Mapa();
    }

    /**
     * Inicia o loop principal do jogo.
     * Navega pelos eventos do mapa até que o herói morra ou vena.
     */
    public void iniciar() {

        Scanner scanner = new Scanner(System.in);
        NoMapa atual = mapa.getRaiz();

        while (heroi.estaVivo() && atual != null) {
            TerminalUI.atualizarMapaAtual(mapa, atual);

            System.out.println("\n=================================");
            System.out.println("Você avança pelo mapa...");
            System.out.println("Evento atual: " + atual.getEvento().getClass().getSimpleName());
            System.out.println("=================================");

            // Executa evento (Batalha, Loja, Escolha, etc.)
            boolean sobreviveu = atual.getEvento().iniciar(heroi, scanner);

            if (!sobreviveu) {
                System.out.println("\n💀 Sua jornada terminou...");
                break;
            }

            atual.marcarVisitado();
            TerminalUI.atualizarMapaAtual(mapa, atual);
            List<NoMapa> proximos = atual.getProximos();

            if (proximos.isEmpty()) {
                System.out.println("\n Você venceu o jogo!");
                break;
            }

            int escolha = TerminalUI.selecionarCaminhoMapa(mapa, atual);

            if (escolha >= 0 && escolha < proximos.size()) {
                atual = proximos.get(escolha);
            } else {
                System.out.println("Escolha inválida, seguindo caminho padrão...");
                atual = proximos.get(0);
            }
        }

        scanner.close();
    }
}
