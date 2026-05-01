package game.echoes;

import game.echoes.Jogo;

/**
 * Ponto de entrada do jogo "Echoes of the Abyss".
 * 
 * Esta classe é responsável por inicializar a interface visual e criar a instância do jogo.
 * Ao ser executada, prepara a janela do terminal e inicia a jornada do herói.
 */
public class App {

    public static void main(String[] args) {

        TerminalUI.prepararJanela();
        TerminalUI.mostrarMensagem("Echoes of the Abyss", "A jornada de Silas Vane começa.");
        TerminalUI.pausar();

        Jogo jogo = new Jogo();
        jogo.iniciar();
    }
}
       
