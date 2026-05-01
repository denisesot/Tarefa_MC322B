package game.echoes;

import java.util.Scanner;

/**
 * Strategy para ações disponíveis na fogueira.
 */
public interface AcaoFogueira {
    String getNome();

    void executar(Heroi heroi, Scanner scanner);
}
