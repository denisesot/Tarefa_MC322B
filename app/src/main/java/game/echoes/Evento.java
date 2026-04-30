package game.echoes;

import java.util.Scanner;

/**
 * Classe abstrata que representa um evento no mapa
 * Qualquer nó do mapa (Batalha, Escolha, Loja, Fogueira) deve herdar desta classe
 */
public abstract class Evento {
    /**
     * Executa a lógica principal do evento.
     * * @param heroi O herói que está participando do evento 
     * @param scanner O scanner para ler as entradas do jogador via terminal
     * @return true se o herói sobreviveu ao evento e pode continuar, false caso tenha morrido
     */
    public abstract boolean iniciar(Heroi heroi, Scanner scanner);
    
}