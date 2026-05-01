package game.echoes;
import java.util.Scanner;
import java.util.List;

/**
 * Evento que apresenta uma escolha de risco ao herói.
 * 
 * Neste evento, o jogador decide entre aceitar uma consequência negativa em
 * troca de uma recompensa ou ignorar a tentação e seguir pelo mapa.
 */
public class Escolha extends Evento {

    /**
     * Retorna o nome exibido para este evento no mapa.
     * 
     * @return O nome do evento narrativo
     */
    @Override
    public String getNomeEvento() {
        return "Altar Profano";
    }
    
    /**
     * Inicia o evento de escolha.
     * 
     * @param heroi O herói que enfrenta a escolha
     * @param scanner O scanner para ler a escolha do jogador
     * @return true se o herói sobreviveu, false se morreu
     */
    @Override
    public boolean iniciar(Heroi heroi, Scanner scanner) {
        TerminalUI.alerta(
            "Você encontra um altar gotejando sangue escuro.\n" +
            "Uma força antiga parece chamar por você..."
        );

        int opcao = TerminalUI.selecionarOpcao(
            "Altar Profano",
            java.util.List.of(
                "Beber o sangue — perde 5 PV, ganha 30 de Ouro",
                "Ignorar e seguir em frente"
            ),
            "Use setas/Enter ou número para escolher."
        );

        if (opcao == 0) {
            heroi.receberDano(5);

            if (heroi.estaVivo()) {
                heroi.ganharOuro(30);
                TerminalUI.sucesso(
                    "Sua garganta queima, mas você encontra moedas no fundo do cálice!\n" +
                    "Ouro atual: " + heroi.getOuro()
                );
            } else {
                TerminalUI.alerta("O sangue profano consumiu sua última força...");
            }
        } else {
            TerminalUI.log("Você ignora a tentação e segue em frente.");
        }

        return heroi.estaVivo();
    }
}
