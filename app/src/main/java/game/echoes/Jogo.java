package game.echoes;

import java.util.*;

public class Jogo {

    private Heroi heroi;
    private Mapa mapa;

    public Jogo() {
        heroi = new Heroi(40, "Silas Vane");
        mapa = new Mapa();
    }

    public void iniciar() {

        Scanner scanner = new Scanner(System.in);
        NoMapa atual = mapa.getRaiz();

        while (heroi.estaVivo() && atual != null) {

            System.out.println("\n=================================");
            System.out.println("Um inimigo surge: " + atual.getInimigo().getNome());
            System.out.println("=================================");

            boolean venceu = Batalha.iniciar(heroi, atual.getInimigo(),scanner);

            if (!venceu) {
                System.out.println("\nGame Over.");
                return;
            }

            List<NoMapa> proximos = atual.getProximos();

            if (proximos.isEmpty()) {
                System.out.println("\n🎉 Você venceu o jogo!");
                return;
            }

            // ESCOLHA
            System.out.println("\nEscolha o próximo caminho:");

            for (int i = 0; i < proximos.size(); i++) {
                System.out.println((i + 1) + " - Enfrentar " + proximos.get(i).getInimigo().getNome());
            }

            int escolha = scanner.nextInt() - 1;

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


