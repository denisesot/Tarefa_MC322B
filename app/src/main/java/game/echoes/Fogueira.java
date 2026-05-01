package game.echoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Evento de progressão que usa Strategy para escolher entre descanso e melhoria.
 */
public class Fogueira extends Evento {
    private List<AcaoFogueira> acoes;

    public Fogueira() {
        acoes = new ArrayList<>();
        acoes.add(new DescansarFogueira());
        acoes.add(new AprimorarCartaFogueira());
        acoes.add(new TrocarCartaFogueira());
    }

    @Override
    public String getNomeEvento() {
        return "Fogueira";
    }

    @Override
    public boolean iniciar(Heroi heroi, Scanner scanner) {
        boolean usandoFogueira = true;
        boolean descansou = false;
        boolean aprimorou = false;

        while (usandoFogueira && heroi.estaVivo()) {
            List<String> opcoes = new ArrayList<>();
            for (int i = 0; i < acoes.size(); i++) {
                String disponibilidade = "";
                if (acoes.get(i) instanceof DescansarFogueira && descansou) {
                    disponibilidade = " (já usado)";
                } else if (acoes.get(i) instanceof AprimorarCartaFogueira && aprimorou) {
                    disponibilidade = " (já usado)";
                }
                opcoes.add(acoes.get(i).getNome() + disponibilidade);
            }
            opcoes.add("Seguir viagem");

            String rodape = "Vida: " + heroi.getVida() + "/" + heroi.getVidaMax()
                    + " | Baralho atual: " + heroi.getBaralhoPrincipal().size()
                    + " | Reserva: " + heroi.getBaralhoReserva().size();
            int opcao = TerminalUI.selecionarOpcao("Fogueira", opcoes, rodape);
            if (opcao == -1 || opcao == acoes.size()) {
                usandoFogueira = false;
            } else if (opcao >= 0 && opcao < acoes.size()) {
                AcaoFogueira acao = acoes.get(opcao);
                if (acao instanceof DescansarFogueira && descansou) {
                    TerminalUI.alerta("Você já descansou nesta fogueira.");
                } else if (acao instanceof AprimorarCartaFogueira && aprimorou) {
                    TerminalUI.alerta("Você já aprimorou uma carta nesta fogueira.");
                } else {
                    acao.executar(heroi, scanner);
                    if (acao instanceof DescansarFogueira) {
                        descansou = true;
                    } else if (acao instanceof AprimorarCartaFogueira) {
                        aprimorou = true;
                    }
                }
            } else {
                TerminalUI.alerta("Opção inválida.");
            }
        }

        TerminalUI.log("Você deixa a fogueira para trás.");
        return heroi.estaVivo();
    }
}
