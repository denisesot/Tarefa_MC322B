package game.echoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import game.echoes.Carta;
import game.echoes.CartaFactory;
import game.echoes.Evento;
import game.echoes.GerenciadorDeCartas;
import game.echoes.Heroi;
import game.echoes.Inimigo;
import game.echoes.TerminalUI;

/**
 * Evento que representa um combate no mapa.
 * 
 * A batalha controla os turnos entre o herói e um inimigo, aplica efeitos,
 * gerencia o uso de cartas e entrega recompensas quando o inimigo é derrotado.
 */
public class Batalha extends Evento {

    private Inimigo inimigo;

    /**
     * Cria uma batalha com um inimigo específico.
     * 
     * @param inimigo O inimigo enfrentado neste evento
     */
    public Batalha(Inimigo inimigo) {
        this.inimigo = inimigo;
    }

    /**
     * Retorna o nome exibido para a batalha no mapa.
     * 
     * @return O nome da batalha com o inimigo atual
     */
    @Override
    public String getNomeEvento() {
        return "Batalha contra " + inimigo.getNome();
    }

    /**
     * Executa o combate entre o herói e o inimigo.
     * 
     * @param heroi O herói que participa da batalha
     * @param scanner O scanner usado para ler as ações do jogador
     * @return true se o herói sobreviveu, false caso contrário
     */
    @Override
    public boolean iniciar(Heroi heroi, Scanner scanner) {
        TerminalUI.alerta("Um " + inimigo.getNome() + " bloqueia seu caminho!");

        GerenciadorDeCartas gerenciador = new GerenciadorDeCartas(heroi.getBaralhoPrincipal());
        List<String> historico = new ArrayList<>();
        int turno = 1;

        while (heroi.estaVivo() && inimigo.estaVivo()) {
            heroi.resetarMana();
            heroi.resetaEscudo();
            heroi.aplicarEfeitos();
            inimigo.aplicarEfeitos();
            gerenciador.prepararNovoTurno();
            historico.add("Turno " + turno + " começou.");

            boolean turnoAtivo = true;
            while (turnoAtivo && heroi.estaVivo() && inimigo.estaVivo()) {
                int escolha = TerminalUI.lerAcaoCombate(heroi, inimigo, gerenciador.getMao(), turno, historico);
                if (escolha == -1) {
                    continue;
                }
                if (escolha == 0) {
                    TerminalUI.alerta("Você abandonou a batalha...");
                    System.exit(0);
                }

                if (escolha == 99) {
                    turnoAtivo = false;
                } else {
                    int indice = escolha - 1;
                    List<Carta> mao = gerenciador.getMao();
                    if (indice >= 0 && indice < mao.size()) {
                        Carta carta = mao.get(indice);
                        if (heroi.getMana() >= carta.getCusto()) {
                            String nomeCarta = carta.getNome();
                            gerenciador.jogarCarta(indice, heroi, inimigo);
                            String mensagem = heroi.getNome() + " usou " + nomeCarta + ".";
                            historico.add(mensagem);
                            TerminalUI.exibirAcontecimentoCombate(heroi, inimigo, gerenciador.getMao(), turno,
                                    historico, "Carta usada", mensagem);
                        } else {
                            String mensagem = "Energia insuficiente para usar " + carta.getNome() + ".";
                            historico.add(mensagem);
                            TerminalUI.exibirAcontecimentoCombate(heroi, inimigo, gerenciador.getMao(), turno,
                                    historico, "Energia insuficiente", mensagem);
                        }
                    } else {
                        String mensagem = "Escolha inválida.";
                        historico.add(mensagem);
                        TerminalUI.exibirAcontecimentoCombate(heroi, inimigo, gerenciador.getMao(), turno,
                                historico, "Ação inválida", mensagem);
                    }
                }

                if (heroi.getMana() <= 0) {
                    historico.add("Sua mana acabou. Turno encerrado.");
                    TerminalUI.exibirAcontecimentoCombate(heroi, inimigo, gerenciador.getMao(), turno,
                            historico, "Fim do turno", "Sua mana acabou. Encerrando turno.");
                    turnoAtivo = false;
                }
            }

            if (inimigo.estaVivo() && heroi.estaVivo()) {
                if (!inimigo.estaAtordoado()) {
                    inimigo.atacar(heroi);
                    String mensagem = inimigo.getNome() + " atacou " + heroi.getNome() + ".";
                    historico.add(mensagem);
                    TerminalUI.exibirAcontecimentoCombate(heroi, inimigo, gerenciador.getMao(), turno,
                            historico, "Turno inimigo", mensagem);
                } else {
                    String mensagem = inimigo.getNome() + " está atordoado e perdeu o turno.";
                    historico.add(mensagem);
                    TerminalUI.exibirAcontecimentoCombate(heroi, inimigo, gerenciador.getMao(), turno,
                            historico, "Turno inimigo", mensagem);
                    inimigo.setAtordoado(false);
                }
            }
            turno++;
        }

        if (heroi.estaVivo()) {
            entregarRecompensas(heroi);
        } else {
            TerminalUI.alerta("Sua mente sucumbiu à loucura... Game Over.");
        }
        return heroi.estaVivo();
    }

    /**
     * Retorna o inimigo desta batalha.
     * 
     * @return O inimigo enfrentado
     */
    public Inimigo getInimigo() {
        return inimigo;
    }

    private void entregarRecompensas(Heroi heroi) {
        System.out.println("\nVocê venceu a batalha!");

        Random rand = new Random();
        int ouroGanho = rand.nextInt(21) + 15;
        heroi.ganharOuro(ouroGanho);
        System.out.println("Você saqueou " + ouroGanho + " de ouro! (Total: " + heroi.getOuro() + ")");

        List<Carta> opcoes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            opcoes.add(CartaFactory.criarCartaAleatoria());
        }

        for (Carta recompensa : opcoes) {
            heroi.adicionarCarta(recompensa);
            System.out.println("RECOMPENSA: Você adicionou a carta ["
                    + recompensa.getNome() + "] ao seu baralho!");
        }
    }
}
