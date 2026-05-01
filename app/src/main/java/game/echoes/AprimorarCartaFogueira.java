package game.echoes;

import java.util.List;
import java.util.Scanner;

/**
 * Estratégia de fogueira que aprimora uma carta permanente do baralho.
 */
public class AprimorarCartaFogueira implements AcaoFogueira {
    @Override
    public String getNome() {
        return "Aprimorar carta";
    }

    @Override
    public void executar(Heroi heroi, Scanner scanner) {
        List<Carta> baralho = heroi.getBaralhoPrincipal();
        int indice = TerminalUI.selecionarCarta(
                "Aprimorar carta",
                baralho,
                "Enter aprimora a carta selecionada. Q cancela.",
                9);

        if (indice >= 0 && indice < baralho.size()) {
            Carta carta = baralho.get(indice);
            if (carta.aprimorar()) {
                TerminalUI.sucesso(carta.getNome() + " foi aprimorada.");
            } else {
                TerminalUI.alerta("Essa carta já estava aprimorada.");
            }
        } else {
            TerminalUI.log("Nada foi aprimorado.");
        }
    }
}
