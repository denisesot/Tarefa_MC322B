package game.echoes;

import java.util.Scanner;

/**
 * Estratégia de fogueira que recupera 30% da vida máxima.
 */
public class DescansarFogueira implements AcaoFogueira {
    @Override
    public String getNome() {
        return "Descansar";
    }

    @Override
    public void executar(Heroi heroi, Scanner scanner) {
        int cura = Math.max(1, heroi.getVidaMax() * 30 / 100);
        heroi.curar(cura);
        TerminalUI.sucesso("Você descansou e recuperou " + cura + " de vida. Vida atual: " + heroi.getVida());
    }
}
