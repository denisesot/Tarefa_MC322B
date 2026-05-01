package game.echoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Evento de progressão que permite comprar cartas ou remover cartas do baralho.
 */
public class Loja extends Evento {
    private static final int PRECO_CARTA = 35;
    private static final int PRECO_REMOCAO = 45;

    @Override
    public String getNomeEvento() {
        return "Loja do Arquivista";
    }

    @Override
    public boolean iniciar(Heroi heroi, Scanner scanner) {
        List<Carta> ofertas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ofertas.add(CartaFactory.criarCartaAleatoria());
        }

        boolean comprando = true;
        while (comprando && heroi.estaVivo()) {
            List<String> opcoes = new ArrayList<>();
            opcoes.add("Comprar uma carta das ofertas");
            opcoes.add("Remover uma carta do baralho por " + PRECO_REMOCAO + " ouro");
            opcoes.add("Sair da loja");

            int opcao = TerminalUI.selecionarOpcao(
                    "Loja do Arquivista",
                    opcoes,
                    "Ouro atual: " + heroi.getOuro() + " | Comprar carta: " + PRECO_CARTA + " ouro");
            if (opcao == 0) {
                escolherCartaParaComprar(heroi, ofertas);
            } else if (opcao == 1) {
                removerCarta(heroi, scanner);
            } else {
                comprando = false;
            }
        }
        return heroi.estaVivo();
    }

    private void escolherCartaParaComprar(Heroi heroi, List<Carta> ofertas) {
        if (ofertas.isEmpty()) {
            TerminalUI.log("Todas as ofertas já foram compradas.");
            return;
        }

        int indice = TerminalUI.selecionarCarta(
                "Ofertas da loja",
                ofertas,
                "Ouro: " + heroi.getOuro() + " | Enter compra por " + PRECO_CARTA + " ouro. Q cancela.");

        if (indice >= 0 && indice < ofertas.size()) {
            comprarCarta(heroi, ofertas, indice);
        } else {
            TerminalUI.log("Compra cancelada.");
        }
    }

    private void comprarCarta(Heroi heroi, List<Carta> ofertas, int indice) {
        if (!heroi.gastarOuro(PRECO_CARTA)) {
            TerminalUI.alerta("Ouro insuficiente.");
            return;
        }
        Carta comprada = ofertas.remove(indice);
        heroi.adicionarCartaReserva(comprada);
        TerminalUI.sucesso("Você comprou " + comprada.getNome() + " e guardou no baralho reserva.");
    }

    private void removerCarta(Heroi heroi, Scanner scanner) {
        if (!heroi.gastarOuro(PRECO_REMOCAO)) {
            TerminalUI.alerta("Ouro insuficiente.");
            return;
        }

        List<Carta> baralho = heroi.getBaralhoPrincipal();
        int indice = TerminalUI.selecionarCarta(
                "Remover carta",
                baralho,
                "Enter remove a carta selecionada. Q cancela e devolve o ouro.");

        if (indice >= 0 && indice < baralho.size()) {
            Carta removida = baralho.get(indice);
            heroi.removerCarta(indice);
            TerminalUI.sucesso(removida.getNome() + " foi removida do baralho.");
        } else {
            heroi.ganharOuro(PRECO_REMOCAO);
            TerminalUI.log("Remoção cancelada. O ouro foi devolvido.");
        }
    }
}
