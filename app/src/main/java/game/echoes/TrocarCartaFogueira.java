package game.echoes;

import java.util.List;
import java.util.Scanner;

/**
 * Estratégia de fogueira que troca uma carta do baralho atual por uma da reserva.
 */
public class TrocarCartaFogueira implements AcaoFogueira {
    @Override
    public String getNome() {
        return "Trocar carta com a reserva";
    }

    @Override
    public void executar(Heroi heroi, Scanner scanner) {
        List<Carta> baralhoAtual = heroi.getBaralhoPrincipal();
        List<Carta> baralhoReserva = heroi.getBaralhoReserva();

        if (baralhoReserva.isEmpty()) {
            TerminalUI.alerta("Seu baralho reserva está vazio. Vença batalhas ou compre cartas para enchê-lo.");
            return;
        }

        int indiceAtual = TerminalUI.selecionarCarta(
                "Carta que sai do baralho atual",
                baralhoAtual,
                "Enter escolhe a carta que vai para a reserva. Q cancela.",
                9);
        if (indiceAtual < 0 || indiceAtual >= baralhoAtual.size()) {
            TerminalUI.log("Troca cancelada.");
            return;
        }

        int indiceReserva = TerminalUI.selecionarCarta(
                "Carta que entra da reserva",
                baralhoReserva,
                "Enter escolhe a carta que entra no baralho atual. Q cancela.",
                9);

        if (indiceAtual >= 0 && indiceAtual < baralhoAtual.size()
                && indiceReserva >= 0 && indiceReserva < baralhoReserva.size()) {
            Carta saiu = baralhoAtual.get(indiceAtual);
            Carta entrou = baralhoReserva.get(indiceReserva);
            heroi.trocarCartaComReserva(indiceAtual, indiceReserva);
            TerminalUI.sucesso(entrou.getNome() + " entrou no baralho atual.\n"
                    + saiu.getNome() + " foi para o baralho reserva.\n"
                    + "Tamanho do baralho atual mantido: " + baralhoAtual.size() + " cartas.");
        } else {
            TerminalUI.log("Troca cancelada.");
        }
    }
}
