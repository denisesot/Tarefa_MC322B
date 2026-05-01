package game.echoes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Centraliza a apresentação visual do jogo no terminal.
 */
public class TerminalUI {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String DIM = "\u001B[2m";

    private static final int CARD_WIDTH = 38;
    private static final int CARD_HEIGHT = 8;
    private static final int CARTAS_POR_PAGINA = 6;
    private static final int CARTAS_COMBATE_POR_PAGINA = 6;
    private static final int COMBAT_ART_LINES = 8;
    private static final int CHARACTER_COLUMN_WIDTH = 42;
    private static final String COLUMN_GAP = "        ";
    private static final String CLEAR_SCREEN = "\033[H\033[J";
    private static final String RESIZE_TERMINAL = "\033[8;44;140t";
    private static final String HIDE_CURSOR = "\033[?25l";
    private static final String SHOW_CURSOR = "\033[?25h";
    private static final int PAUSA_CURTA_MS = 1800;
    private static final int PAUSA_MAXIMA_MS = 8500;
    private static final int HISTORICO_LARGURA = 96;
    private static final int HISTORICO_ALTURA = 18;
    private static Mapa mapaAtual;
    private static NoMapa noAtual;

    private TerminalUI() {
    }

    public static void atualizarMapaAtual(Mapa mapa, NoMapa atual) {
        mapaAtual = mapa;
        noAtual = atual;
    }

    public static void prepararJanela() {
        System.out.print(RESIZE_TERMINAL);
        System.out.flush();
        pausar(250);
    }

    public static void limparTela() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    public static void pausar() {
        pausar(PAUSA_CURTA_MS);
    }

    public static void pausarLeitura(String texto) {
        int tamanho = texto == null ? 0 : texto.length();
        int linhas = texto == null || texto.isBlank() ? 1 : texto.split("\\R").length;
        int tempo = PAUSA_CURTA_MS + tamanho * 18 + linhas * 260;
        pausar(Math.min(PAUSA_MAXIMA_MS, tempo));
    }

    private static void pausar(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void titulo(String texto) {
        mostrarMensagem(texto, "");
    }

    public static void log(String texto) {
        mostrarMensagem("Registro", texto, DIM);
        pausarLeitura(texto);
    }

    public static void alerta(String texto) {
        mostrarMensagem("Atenção", texto, YELLOW + BOLD);
        pausarLeitura(texto);
    }

    public static void sucesso(String texto) {
        mostrarMensagem("Sucesso", texto, GREEN + BOLD);
        pausarLeitura(texto);
    }

    public static void aguardarTecla() {
        ModoTerminal modo = new ModoTerminal();
        modo.ativar();
        try {
            if (modo.estaAtivo()) {
                while (true) {
                    if (modo.temEntrada()) {
                        modo.lerTecla();
                        return;
                    }
                    dormir(80);
                }
            } else {
                lerTeclaComDrenagem();
            }
        } catch (IOException e) {
            return;
        } finally {
            modo.restaurar();
        }
    }

    public static void mostrarMensagem(String titulo, String texto) {
        mostrarMensagem(titulo, texto, CYAN + BOLD);
    }

    private static void mostrarMensagem(String titulo, String texto, String cor) {
        limparTela();
        System.out.println(MAGENTA + BOLD + "ECHOES OF THE ABYSS" + RESET);
        System.out.println();
        System.out.println(cor + "=== " + titulo + " ===" + RESET);
        System.out.println();
        for (String linha : texto.split("\\R")) {
            if (!linha.isBlank()) {
                System.out.println(cor + linha + RESET);
            }
        }
        System.out.flush();
    }

    public static void exibirEstadoCombate(Heroi heroi, Inimigo inimigo, List<Carta> mao, int turno) {
        limparTela();
        System.out.println(MAGENTA + BOLD + "ECHOES OF THE ABYSS" + RESET + DIM + "  |  Turno " + turno + RESET);
        System.out.println();
        exibirPersonagens(heroi, inimigo);
        System.out.println();
        exibirCartas(mao);
        System.out.println();
        System.out.println(CYAN + BOLD + "Energia: " + heroi.getMana() + "/" + heroi.getMaxMana() + RESET);
        System.out.println(CYAN + "99. Encerrar turno" + RESET);
        System.out.println(RED + "0. Sair do jogo" + RESET);
    }

    public static int lerAcaoCombate(Heroi heroi, Inimigo inimigo, List<Carta> mao, int turno) {
        return lerAcaoCombate(heroi, inimigo, mao, turno, List.of());
    }

    public static int lerAcaoCombate(Heroi heroi, Inimigo inimigo, List<Carta> mao, int turno, List<String> historico) {
        ModoTerminal modo = new ModoTerminal();
        modo.ativar();

        int frame = 0;
        int selecionada = 0;
        try {
            if (!modo.estaAtivo()) {
                System.out.print(montarTelaCombate(heroi, inimigo, mao, turno, frame, selecionada, historico));
                System.out.flush();
                int tecla = lerTeclaComDrenagem();
                int inicioScroll = calcularInicioScroll(selecionada, mao.size(), CARTAS_COMBATE_POR_PAGINA);
                return converterTeclaParaAcao(tecla, mao.size(), inicioScroll);
            }

            while (true) {
                System.out.print(montarTelaCombate(heroi, inimigo, mao, turno, frame, selecionada, historico));
                System.out.flush();

                int inicioScroll = calcularInicioScroll(selecionada, mao.size(), CARTAS_COMBATE_POR_PAGINA);
                int acao = lerEntradaCombate(modo, mao.size(), selecionada, inicioScroll);
                if (acao != -1) {
                    if (acao == -10) {
                        selecionada = moverSelecao(selecionada, mao.size(), -1);
                    } else if (acao == -11) {
                        selecionada = moverSelecao(selecionada, mao.size(), 1);
                    } else if (acao == -12) {
                        selecionada = moverSelecao(selecionada, mao.size(), -3);
                    } else if (acao == -13) {
                        selecionada = moverSelecao(selecionada, mao.size(), 3);
                    } else if (acao == -20) {
                        mostrarHistorico(historico);
                    } else if (acao == -30) {
                        mostrarMapaAtual();
                    } else {
                        return acao;
                    }
                }

                dormir(70);
                frame++;
            }
        } catch (IOException e) {
            return -1;
        } finally {
            modo.restaurar();
        }
    }

    public static int selecionarOpcao(String titulo, List<String> opcoes, String rodape) {
        ModoTerminal modo = new ModoTerminal();
        modo.ativar();
        int selecionada = 0;
        int frame = 0;

        try {
            if (!modo.estaAtivo()) {
                System.out.print(montarTelaMenu(titulo, opcoes, rodape, selecionada, frame));
                System.out.flush();
                int opcao = converterTeclaParaOpcao(lerTeclaComDrenagem(), opcoes.size(), selecionada);
                return opcao == -99 ? -1 : opcao;
            }

            while (true) {
                System.out.print(montarTelaMenu(titulo, opcoes, rodape, selecionada, frame));
                System.out.flush();

                int acao = lerEntradaMenu(modo, opcoes.size(), selecionada);
                if (acao == -10) {
                    selecionada = moverSelecao(selecionada, opcoes.size(), -1);
                } else if (acao == -11) {
                    selecionada = moverSelecao(selecionada, opcoes.size(), 1);
                } else if (acao == -99) {
                    return -1;
                } else if (acao != -1) {
                    return acao;
                }


                dormir(90);
                frame++;
            }
        } catch (IOException e) {
            return -1;
        } finally {
            modo.restaurar();
        }
    }

    public static int selecionarCarta(String titulo, List<Carta> cartas, String rodape) {
        return selecionarCarta(titulo, cartas, rodape, CARTAS_POR_PAGINA);
    }

    public static int selecionarCarta(String titulo, List<Carta> cartas, String rodape, int cartasPorPagina) {
        ModoTerminal modo = new ModoTerminal();
        modo.ativar();
        int selecionada = 0;
        int frame = 0;

        try {
            if (!modo.estaAtivo()) {
                System.out.print(montarTelaCartas(titulo, cartas, rodape, selecionada, frame, cartasPorPagina));
                System.out.flush();
                int inicioPagina = calcularInicioPagina(selecionada, cartas.size(), cartasPorPagina);
                int opcao = converterTeclaParaCarta(lerTeclaComDrenagem(), cartas.size(), selecionada, inicioPagina);
                return opcao == -99 ? -1 : opcao;
            }

            while (true) {
                System.out.print(montarTelaCartas(titulo, cartas, rodape, selecionada, frame, cartasPorPagina));
                System.out.flush();

                int inicioPagina = calcularInicioPagina(selecionada, cartas.size(), cartasPorPagina);
                int acao = lerEntradaCartas(modo, cartas.size(), selecionada, inicioPagina);
                if (acao == -10) {
                    selecionada = moverSelecao(selecionada, cartas.size(), -1);
                } else if (acao == -11) {
                    selecionada = moverSelecao(selecionada, cartas.size(), 1);
                } else if (acao == -12) {
                    selecionada = moverSelecao(selecionada, cartas.size(), -3);
                } else if (acao == -13) {
                    selecionada = moverSelecao(selecionada, cartas.size(), 3);
                } else if (acao == -99) {
                    return -1;
                } else if (acao != -1) {
                    return acao;
                }

                frame++;
            }
        } catch (IOException e) {
            return -1;
        } finally {
            modo.restaurar();
        }
    }

    public static int selecionarCaminhoMapa(NoMapa atual) {
        return selecionarCaminhoMapa(null, atual);
    }

    public static int selecionarCaminhoMapa(Mapa mapa, NoMapa atual) {
        List<NoMapa> proximos = atual.getProximos();
        ModoTerminal modo = new ModoTerminal();
        modo.ativar();
        int selecionada = 0;
        int frame = 0;
        boolean exibindoMapa = false;

        try {
            if (!modo.estaAtivo()) {
                while (true) {
                    System.out.print(exibindoMapa
                            ? montarAbaMapa(mapa, atual, proximos, frame)
                            : montarTelaCaminhosMapa(atual, selecionada, frame));
                    System.out.flush();
                    int opcao = converterTeclaParaOpcao(lerTeclaComDrenagem(), proximos.size(), selecionada);
                    if (exibindoMapa) {
                        if (opcao == -30 || opcao == -99 || opcao >= 0) {
                            exibindoMapa = false;
                        }
                    } else if (opcao == -30) {
                        exibindoMapa = true;
                    } else {
                        return opcao == -99 ? -1 : opcao;
                    }
                    frame++;
                }
            }

            while (true) {
                if (exibindoMapa) {
                    System.out.print(montarAbaMapa(mapa, atual, proximos, frame));
                } else {
                    System.out.print(montarTelaCaminhosMapa(atual, selecionada, frame));
                }
                System.out.flush();
                
                int acao = lerEntradaMenu(modo, proximos.size(), selecionada);

                if (exibindoMapa) {
                    if (acao == -30 || acao == -99 || acao >= 0) {
                        exibindoMapa = false;
                    }
                } else if (acao == -30) {
                    exibindoMapa = true;
                } else if (acao == -10) {
                    selecionada = moverSelecao(selecionada, proximos.size(), -1);
                } else if (acao == -11) {
                    selecionada = moverSelecao(selecionada, proximos.size(), 1);
                } else if (acao == -99) {
                    return -1;
                } else if (acao != -1) {
                    return acao;
                }

                frame++;
            }
        } catch (IOException e) {
            return -1;
        } finally {
            modo.restaurar();
        }
    }

    public static void exibirAcontecimentoCombate(Heroi heroi, Inimigo inimigo, List<Carta> mao, int turno,
            List<String> historico, String titulo, String texto) {
        String mensagem = texto == null || texto.isBlank() ? "Nada aconteceu." : texto;
        System.out.print(montarTelaAcontecimentoCombate(heroi, inimigo, mao, turno, historico, titulo, mensagem));
        System.out.flush();
        aguardarTecla();
    }

    private static void pausarLeituraCombate(String texto) {
        int tamanho = texto == null ? 0 : texto.length();
        int linhas = texto == null || texto.isBlank() ? 1 : texto.split("\\R").length;
        int tempo = 2600 + tamanho * 22 + linhas * 420;
        pausar(Math.min(PAUSA_MAXIMA_MS, tempo));
    }

    private static int converterTeclaParaAcao(int tecla) {
        return converterTeclaParaAcao(tecla, 9, 0);
    }

    private static int converterTeclaParaAcao(int tecla, int total, int inicioPagina) {
        if (tecla == -1) {
            return -1;
        }
        if (tecla >= '1' && tecla <= '9') {
            int opcao = inicioPagina + (tecla - '0');
            return opcao <= total ? opcao : -1;
        }
        if (tecla == '0' || tecla == 'q' || tecla == 'Q') {
            return 0;
        }
        if (tecla == 'e' || tecla == 'E' || tecla == ' ') {
            return 99;
        }
        if (tecla == 'r' || tecla == 'R') {
            return -20;
        }
        if (tecla == 'm' || tecla == 'M') {
            return -30;
        }
        return -1;
    }

    private static int lerTeclaComDrenagem() throws IOException {
        int tecla = System.in.read();
        while (System.in.available() > 0) {
            int restante = System.in.read();
            if (restante == '\n' || restante == '\r') {
                break;
            }
        }
        return tecla;
    }

    private static int lerEntradaCombate(ModoTerminal modo, int total, int selecionada, int inicioPagina)
            throws IOException {
        int tecla = modo.lerTecla();
        if (tecla == 27) {
            int direcao = lerDirecaoEscape(modo);
            if (direcao == 'D') return -10;
            if (direcao == 'C') return -11;
            if (direcao == 'A') return -12;
            if (direcao == 'B') return -13;
        }
        if (tecla == '\n' || tecla == '\r') {
            return selecionada + 1;
        }
        return converterTeclaParaAcao(tecla, total, inicioPagina);
    }

    private static int lerEntradaMenu(ModoTerminal modo, int total, int selecionada) throws IOException {
        int tecla = modo.lerTecla();
        if (tecla == 27) {
            int direcao = lerDirecaoEscape(modo);
            if (direcao == 'A' || direcao == 'D') return -10;
            if (direcao == 'B' || direcao == 'C') return -11;
        }
        return converterTeclaParaOpcao(tecla, total, selecionada);
    }

    private static int lerEntradaCartas(ModoTerminal modo, int total, int selecionada, int inicioPagina)
            throws IOException {
        int tecla = modo.lerTecla();
        if (tecla == 27) {
            int direcao = lerDirecaoEscape(modo);
            if (direcao == 'D') return -10;
            if (direcao == 'C') return -11;
            if (direcao == 'A') return -12;
            if (direcao == 'B') return -13;
        }
        return converterTeclaParaCarta(tecla, total, selecionada, inicioPagina);
    }

    private static int lerDirecaoEscape(ModoTerminal modo) throws IOException {
        int colchete = modo.lerTecla();
        if (colchete != '[') {
            return -1;
        }
        return modo.lerTecla();
    }

    private static int converterTeclaParaCarta(int tecla, int total, int selecionada, int inicioPagina) {
        if (tecla == -1) {
            return -1;
        }
        if (tecla >= '1' && tecla <= '9') {
            int opcao = inicioPagina + (tecla - '1');
            return opcao < total ? opcao : -1;
        }
        if (tecla == '\n' || tecla == '\r') {
            return selecionada;
        }
        if (tecla == '0' || tecla == 'q' || tecla == 'Q') {
            return -99;
        }
        if (tecla == 'm' || tecla == 'M') {
            return -30;
        }
        return -1;
    }

    private static int converterTeclaParaOpcao(int tecla, int total, int selecionada) {
        if (tecla == -1) {
            return -1;
        }
        if (tecla >= '1' && tecla <= '9') {
            int opcao = tecla - '1';
            return opcao < total ? opcao : -1;
        }
        if (tecla == '\n' || tecla == '\r') {
            return selecionada;
        }
        if (tecla == '0' || tecla == 'q' || tecla == 'Q') {
            return -99;
        }
        if (tecla == 'm' || tecla == 'M') {
            return -30;
        }
        return -1;
    }

    private static int moverSelecao(int atual, int total, int delta) {
        if (total <= 0) {
            return 0;
        }
        return Math.floorMod(atual + delta, total);
    }

    private static String montarTelaCombate(Heroi heroi, Inimigo inimigo, List<Carta> mao, int turno, int frame,
            int selecionada, List<String> historico) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(CLEAR_SCREEN);
        buffer.append(MAGENTA).append(BOLD).append("ECHOES OF THE ABYSS").append(RESET);
        buffer.append(DIM).append("  |  Turno ").append(turno);
        buffer.append("  |  setas/Enter selecionam carta, 1-").append(mao.size()).append(" também funciona");
        buffer.append("  |  R registro, M mapa");
        buffer.append(RESET).append("\n\n");
        adicionarPersonagens(buffer, heroi, inimigo, frame, COMBAT_ART_LINES);
        adicionarAnimacao(buffer, frame);
        buffer.append("\n");
        int inicioScroll = calcularInicioScroll(selecionada, mao.size(), CARTAS_COMBATE_POR_PAGINA);
        int fimScroll = Math.min(mao.size(), inicioScroll + CARTAS_COMBATE_POR_PAGINA);
        adicionarCartas(buffer, mao, selecionada, inicioScroll, fimScroll);
        buffer.append(DIM).append("Cartas ").append(inicioScroll + 1).append("-").append(fimScroll)
                .append(" de ").append(mao.size())
                .append(". Setas rolam a mão; números escolhem cartas visíveis.").append(RESET).append("\n");
        buffer.append("\n");
        buffer.append(CYAN).append(BOLD).append("Energia: ").append(heroi.getMana()).append("/")
                .append(heroi.getMaxMana()).append(RESET).append("   ");
        buffer.append(CYAN).append("E/ESPAÇO. Encerrar turno").append(RESET).append("   ");
        buffer.append(MAGENTA).append("R. Registro").append(RESET).append("   ");
        buffer.append(YELLOW).append("M. Mapa").append(RESET).append("   ");
        buffer.append(RED).append("Q ou 0. Sair").append(RESET).append("\n");
        return buffer.toString();
    }

    private static String montarTelaMenu(String titulo, List<String> opcoes, String rodape, int selecionada, int frame) {
        StringBuilder buffer = new StringBuilder();
        int larguraOpcao = 72;
        buffer.append(CLEAR_SCREEN);
        buffer.append(MAGENTA).append(BOLD).append("ECHOES OF THE ABYSS").append(RESET);
        buffer.append(DIM).append("  |  setas/Enter, número ou Q").append(RESET).append("\n\n");
        buffer.append(CYAN).append(BOLD).append("=== ").append(titulo).append(" ===").append(RESET).append("\n\n");
        for (int i = 0; i < opcoes.size(); i++) {
            boolean selecionadaAtual = i == selecionada;
            String cor = selecionadaAtual ? GREEN + BOLD : YELLOW;
            String marcador = selecionadaAtual ? ">> " : "   ";
            buffer.append(cor).append(marcador).append("+").append(repetir("-", larguraOpcao - 2)).append("+")
                    .append(RESET).append("\n");
            buffer.append(cor).append(marcador).append("| ")
                    .append(ajustar((i + 1) + ". " + opcoes.get(i), larguraOpcao - 4))
                    .append(" |").append(RESET).append("\n");
            buffer.append(cor).append(marcador).append("+").append(repetir("-", larguraOpcao - 2)).append("+")
                    .append(RESET).append("\n");
        }
        adicionarAnimacao(buffer, frame);
        if (rodape != null && !rodape.isBlank()) {
            buffer.append("\n").append(DIM).append(rodape).append(RESET).append("\n");
        }
        return buffer.toString();
    }

    private static String montarTelaCartas(String titulo, List<Carta> cartas, String rodape, int selecionada, int frame) {
        return montarTelaCartas(titulo, cartas, rodape, selecionada, frame, CARTAS_POR_PAGINA);
    }

    private static String montarTelaCartas(String titulo, List<Carta> cartas, String rodape, int selecionada, int frame,
            int cartasPorPagina) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(CLEAR_SCREEN);
        buffer.append(MAGENTA).append(BOLD).append("ECHOES OF THE ABYSS").append(RESET);
        buffer.append(DIM).append("  |  setas/Enter, número ou Q").append(RESET).append("\n\n");
        buffer.append(CYAN).append(BOLD).append("=== ").append(titulo).append(" ===").append(RESET).append("\n\n");
        int inicioPagina = calcularInicioPagina(selecionada, cartas.size(), cartasPorPagina);
        int fimPagina = Math.min(cartas.size(), inicioPagina + cartasPorPagina);
        adicionarCartas(buffer, cartas, selecionada, inicioPagina, fimPagina);
        adicionarAnimacao(buffer, frame);
        buffer.append(DIM).append("Mostrando ").append(inicioPagina + 1).append("-").append(fimPagina)
                .append(" de ").append(cartas.size()).append(" cartas. Setas rolam a lista; números escolhem a carta visível.")
                .append(RESET).append("\n");
        if (rodape != null && !rodape.isBlank()) {
            buffer.append("\n").append(DIM).append(rodape).append(RESET).append("\n");
        }
        return buffer.toString();
    }

    private static String montarTelaCaminhosMapa(NoMapa atual, int selecionada, int frame) {
        StringBuilder buffer = new StringBuilder();
        List<NoMapa> proximos = atual.getProximos();
        buffer.append(CLEAR_SCREEN);
        buffer.append(MAGENTA).append(BOLD).append("ECHOES OF THE ABYSS").append(RESET);
        buffer.append(DIM).append("  |  Escolha de caminho").append(RESET).append("\n\n");
        buffer.append(CYAN).append(BOLD).append("Local atual").append(RESET).append("\n");
        adicionarMapaSimples(buffer, atual);
        buffer.append("\n").append(DIM).append("Pressione M para abrir a aba do mapa completo.").append(RESET)
                .append("\n\n");
        buffer.append(CYAN).append(BOLD).append("Próximos caminhos").append(RESET).append("\n\n");

        for (int i = 0; i < proximos.size(); i++) {
            boolean selecionadaAtual = i == selecionada;
            String cor = selecionadaAtual ? GREEN + BOLD : YELLOW;
            String marcador = selecionadaAtual ? ">> " : "   ";
            buffer.append(cor).append(marcador).append(i + 1).append(". ")
                    .append("+").append(repetir("-", 42)).append("+").append(RESET).append("\n");
            buffer.append(cor).append("   | ").append(ajustar(nomeNo(proximos.get(i)), 40)).append(" |")
                    .append(RESET).append("\n");
            buffer.append(cor).append("   +").append(repetir("-", 42)).append("+").append(RESET).append("\n");
        }

        adicionarAnimacao(buffer, frame);
        buffer.append("\n").append(DIM)
                .append("Use setas e Enter, pressione o número do caminho, ou M para ver o mapa. Q segue o caminho padrão.")
                .append(RESET).append("\n");
        return buffer.toString();
    }

    private static String montarAbaMapa(Mapa mapa, NoMapa atual, List<NoMapa> proximos, int frame) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(CLEAR_SCREEN);
        buffer.append(MAGENTA).append(BOLD).append("ECHOES OF THE ABYSS").append(RESET);
        buffer.append(DIM).append("  |  Aba Mapa").append(RESET).append("\n\n");
        buffer.append(CYAN).append(BOLD).append("Mapa da jornada").append(RESET).append("\n");
        if (mapa != null) {
            adicionarMapaCompleto(buffer, mapa, atual, proximos);
        } else {
            adicionarMapaSimples(buffer, atual);
        }
        adicionarAnimacao(buffer, frame);
        buffer.append("\n").append(DIM)
                .append("Cores: verde = onde você está, amarelo = pode escolher agora, azul = já passou, cinza = falta.")
                .append(RESET).append("\n");
        buffer.append(DIM).append("Pressione M ou Q para voltar aos caminhos.").append(RESET).append("\n");
        return buffer.toString();
    }

    private static void adicionarMapaSimples(StringBuilder buffer, NoMapa atual) {
        buffer.append(BLUE).append("+").append(repetir("-", 42)).append("+").append(RESET).append("\n");
        buffer.append(BLUE).append("| ").append(ajustar(nomeNo(atual), 40)).append(" |").append(RESET).append("\n");
        buffer.append(BLUE).append("+").append(repetir("-", 42)).append("+").append(RESET).append("\n");
        buffer.append(DIM).append("          |\n          v").append(RESET).append("\n");
    }

    private static void adicionarMapaCompleto(StringBuilder buffer, Mapa mapa, NoMapa atual, List<NoMapa> proximos) {
        buffer.append(montarMapaVisual(mapa, atual, proximos));
    }

    private static String corNoMapa(NoMapa no, NoMapa atual, List<NoMapa> proximos) {
        if (no == atual) {
            return GREEN + BOLD;
        }
        if (proximos.contains(no)) {
            return YELLOW + BOLD;
        }
        if (no.foiVisitado()) {
            return BLUE;
        }
        return DIM;
    }

    private static String montarMapaVisual(Mapa mapa, NoMapa atual, List<NoMapa> proximos) {
        final int largura = 76;
        final int altura = 18;
        char[][] canvas = new char[altura][largura];
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                canvas[y][x] = ' ';
            }
        }

        List<List<NoMapa>> camadas = mapa.getCamadas();
        Map<NoMapa, int[]> posicoes = new HashMap<>();
        for (int camadaIndice = 0; camadaIndice < camadas.size(); camadaIndice++) {
            List<NoMapa> camada = camadas.get(camadaIndice);
            int y = 2 + camadaIndice * 4;
            int espacamento = largura / (camada.size() + 1);
            for (int i = 0; i < camada.size(); i++) {
                int x = espacamento * (i + 1);
                posicoes.put(camada.get(i), new int[] { x, y });
            }
        }

        for (List<NoMapa> camada : camadas) {
            for (NoMapa origem : camada) {
                int[] inicio = posicoes.get(origem);
                if (inicio == null) {
                    continue;
                }
                for (NoMapa destino : origem.getProximos()) {
                    int[] fim = posicoes.get(destino);
                    if (fim != null) {
                        desenharConexao(canvas, inicio[0], inicio[1] + 1, fim[0], fim[1] - 1);
                    }
                }
            }
        }

        StringBuilder buffer = new StringBuilder();
        buffer.append(DIM).append("+").append(repetir("-", largura + 1)).append("+")
                .append(RESET).append("   ").append(CYAN).append(BOLD).append("Legenda").append(RESET).append("\n");
        for (int y = 0; y < altura; y++) {
            buffer.append(DIM).append("|").append(RESET);
            adicionarLinhaMapa(buffer, canvas[y], y, posicoes, atual, proximos);
            buffer.append(DIM).append(" |").append(RESET);
            adicionarLinhaLegendaMapa(buffer, y);
            buffer.append("\n");
        }
        buffer.append(DIM).append("+").append(repetir("-", largura + 1)).append("+").append(RESET).append("\n");
        return buffer.toString();
    }

    private static void desenharConexao(char[][] canvas, int x1, int y1, int x2, int y2) {
        int passos = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
        if (passos == 0) {
            return;
        }
        for (int i = 1; i < passos; i += 2) {
            int x = x1 + (x2 - x1) * i / passos;
            int y = y1 + (y2 - y1) * i / passos;
            if (y >= 0 && y < canvas.length && x >= 0 && x < canvas[y].length) {
                canvas[y][x] = '.';
            }
        }
    }

    private static void adicionarLinhaMapa(StringBuilder buffer, char[] linha, int y,
            Map<NoMapa, int[]> posicoes, NoMapa atual, List<NoMapa> proximos) {
        Map<Integer, NoMapa> nosNaLinha = new HashMap<>();
        for (Map.Entry<NoMapa, int[]> entrada : posicoes.entrySet()) {
            int[] posicao = entrada.getValue();
            if (posicao[1] == y) {
                nosNaLinha.put(posicao[0], entrada.getKey());
            }
        }

        int x = 0;
        while (x < linha.length) {
            NoMapa no = nosNaLinha.get(x);
            if (no != null) {
                String token = tokenNoMapa(no);
                buffer.append(corNoMapa(no, atual, proximos)).append(token).append(RESET);
                x += token.length();
            } else {
                buffer.append(DIM).append(linha[x]).append(RESET);
                x++;
            }
        }
    }

    private static void adicionarLinhaLegendaMapa(StringBuilder buffer, int linha) {
        String[] legenda = {
                "  ?  Evento narrativo",
                "  E  Batalha",
                "  B  Boss",
                "  $  Loja",
                "  F  Fogueira",
                "",
                GREEN + BOLD + "  verde" + RESET + "  onde voce esta",
                YELLOW + BOLD + "  amarelo" + RESET + " pode escolher",
                BLUE + "  azul" + RESET + "    ja passou",
                DIM + "  cinza" + RESET + "   falta"
        };
        if (linha < legenda.length) {
            buffer.append("   ").append(legenda[linha]);
        }
    }

    private static String tokenNoMapa(NoMapa no) {
        Evento evento = no.getEvento();
        if (evento instanceof Loja) {
            return "[$]";
        }
        if (evento instanceof Fogueira) {
            return "[F]";
        }
        if (evento instanceof Escolha) {
            return "[?]";
        }
        if (evento instanceof Batalha && ((Batalha) evento).getInimigo() instanceof CthulhuBoss) {
            return "[B]";
        }
        if (evento instanceof Batalha) {
            return "[E]";
        }
        return "[?]";
    }

    private static String nomeNo(NoMapa no) {
        return tipoEvento(no.getEvento()) + " - " + no.getEvento().getNomeEvento();
    }

    private static String tipoEvento(Evento evento) {
        if (evento instanceof Batalha) {
            return "BATALHA";
        }
        if (evento instanceof Fogueira) {
            return "FOGUEIRA";
        }
        if (evento instanceof Loja) {
            return "LOJA";
        }
        if (evento instanceof Escolha) {
            return "EVENTO";
        }
        return "NO";
    }

    public static void exibirRecompensas(List<Carta> opcoes) {
        titulo("Recompensa de carta");
        exibirCartas(opcoes);
        System.out.println(CYAN + "0. Pular recompensa" + RESET);
    }

    private static void exibirPersonagens(Heroi heroi, Inimigo inimigo) {
        StringBuilder buffer = new StringBuilder();
        adicionarPersonagens(buffer, heroi, inimigo);
        System.out.print(buffer.toString());
    }

    private static void adicionarPersonagens(StringBuilder buffer, Heroi heroi, Inimigo inimigo) {
        adicionarPersonagens(buffer, heroi, inimigo, 0, -1);
    }

    private static void adicionarPersonagens(StringBuilder buffer, Heroi heroi, Inimigo inimigo, int frame) {
        adicionarPersonagens(buffer, heroi, inimigo, frame, -1);
    }

    private static void adicionarPersonagens(StringBuilder buffer, Heroi heroi, Inimigo inimigo, int frame, int alturaMaxima) {
        List<String> arteHeroi = lerArte("art/heroi.txt", artePadraoHeroi());
        List<String> arteInimigo = lerArte("art/" + normalizar(inimigo.getNome()), artePadraoInimigo());
        if (alturaMaxima > 0) {
            arteHeroi = limitarArteParaCombate(arteHeroi, alturaMaxima);
            arteInimigo = limitarArteParaCombate(arteInimigo, alturaMaxima);
        }
        int altura = Math.max(arteHeroi.size(), arteInimigo.size());
        int deslocamentoHeroi = calcularDeslocamentoSprite(frame, true);
        int deslocamentoInimigo = calcularDeslocamentoSprite(frame, false);

        for (int i = 0; i < altura; i++) {
            String linhaHeroi = i < arteHeroi.size() ? arteHeroi.get(i) : "";
            String linhaInimigo = i < arteInimigo.size() ? arteInimigo.get(i) : "";
            linhaHeroi = deslocarSprite(linhaHeroi, deslocamentoHeroi);
            linhaInimigo = deslocarSprite(linhaInimigo, deslocamentoInimigo);
            adicionarDuasColunas(buffer, linhaHeroi, linhaInimigo, BLUE, RED);
        }

        adicionarDuasColunas(buffer,
                heroi.getNome() + "  HP: " + heroi.getVida() + "/" + heroi.getVidaMax(),
                inimigo.getNome() + "  HP: " + inimigo.getVida() + "/" + inimigo.getVidaMax(),
                BLUE + BOLD,
                RED + BOLD);
        adicionarDuasColunas(buffer,
                "Escudo: " + heroi.getEscudo(),
                "Intenção: ataque",
                CYAN,
                YELLOW);
        adicionarDuasColunas(buffer,
                "Efeitos: " + heroi.getResumoEfeitos(),
                "Efeitos: " + inimigo.getResumoEfeitos(),
                GREEN,
                GREEN);
    }

    private static List<String> limitarArteParaCombate(List<String> arte, int alturaMaxima) {
        if (arte.size() <= alturaMaxima) {
            return arte;
        }
        List<String> reduzida = new ArrayList<>();
        double passo = (double) (arte.size() - 1) / (alturaMaxima - 1);
        for (int i = 0; i < alturaMaxima; i++) {
            int indice = (int) Math.round(i * passo);
            reduzida.add(arte.get(Math.min(indice, arte.size() - 1)));
        }
        return reduzida;
    }

    private static int calcularDeslocamentoSprite(int frame, boolean heroi) {
        int fase = (frame / 5) % 12;
        int deslocamento;
        if (fase < 4) {
            deslocamento = fase;
        } else if (fase < 8) {
            deslocamento = 8 - fase;
        } else {
            deslocamento = 0;
        }
        return heroi ? deslocamento : 3 - deslocamento;
    }

    private static String deslocarSprite(String linha, int deslocamento) {
        if (linha.isBlank()) {
            return linha;
        }
        return repetir(" ", Math.max(0, deslocamento)) + linha;
    }

    private static void imprimirDuasColunas(String esquerda, String direita, String corEsquerda, String corDireita) {
        System.out.println(corEsquerda + ajustar(esquerda, CHARACTER_COLUMN_WIDTH) + RESET
                + COLUMN_GAP
                + corDireita + ajustar(direita, CHARACTER_COLUMN_WIDTH) + RESET);
    }

    private static void adicionarDuasColunas(StringBuilder buffer, String esquerda, String direita,
            String corEsquerda, String corDireita) {
        buffer.append(corEsquerda).append(ajustar(esquerda, CHARACTER_COLUMN_WIDTH)).append(RESET)
                .append(COLUMN_GAP)
                .append(corDireita).append(ajustar(direita, CHARACTER_COLUMN_WIDTH)).append(RESET)
                .append("\n");
    }

    private static void exibirCartas(List<Carta> cartas) {
        StringBuilder buffer = new StringBuilder();
        adicionarCartas(buffer, cartas, -1);
        System.out.print(buffer.toString());
    }

    private static String montarTelaAcontecimentoCombate(Heroi heroi, Inimigo inimigo, List<Carta> mao, int turno,
            List<String> historico, String titulo, String texto) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(CLEAR_SCREEN);
        buffer.append(MAGENTA).append(BOLD).append("ECHOES OF THE ABYSS").append(RESET);
        buffer.append(DIM).append("  |  Turno ").append(turno).append("  |  acontecimento").append(RESET).append("\n\n");
        adicionarPersonagens(buffer, heroi, inimigo, 0, COMBAT_ART_LINES);
        adicionarAnimacao(buffer, 0);
        buffer.append("\n");
        buffer.append(MAGENTA).append(BOLD).append("+").append(repetir("-", 92)).append("+").append(RESET).append("\n");
        buffer.append(MAGENTA).append(BOLD).append("| ").append(ajustar(titulo, 90)).append(" |").append(RESET).append("\n");
        buffer.append(MAGENTA).append(BOLD).append("+").append(repetir("-", 92)).append("+").append(RESET).append("\n");
        for (String linha : texto.split("\\R")) {
            if (!linha.isBlank()) {
                buffer.append(YELLOW).append("| ").append(ajustar(linha.trim(), 90)).append(" |").append(RESET).append("\n");
            }
        }
        buffer.append(MAGENTA).append(BOLD).append("+").append(repetir("-", 92)).append("+").append(RESET).append("\n");
        return buffer.toString();
    }

    private static void adicionarCartas(StringBuilder buffer, List<Carta> cartas, int selecionada) {
        adicionarCartas(buffer, cartas, selecionada, 0, cartas.size());
    }

    private static void adicionarCartas(StringBuilder buffer, List<Carta> cartas, int selecionada, int inicio, int fim) {
        for (int i = inicio; i < fim; i += 3) {
            List<String[]> caixas = new ArrayList<>();
            List<Boolean> selecionadas = new ArrayList<>();
            for (int j = i; j < i + 3 && j < fim; j++) {
                caixas.add(montarCarta(j + 1, cartas.get(j)));
                selecionadas.add(j == selecionada);
            }
            for (int linha = 0; linha < CARD_HEIGHT; linha++) {
                for (int caixaIndice = 0; caixaIndice < caixas.size(); caixaIndice++) {
                    String cor = selecionadas.get(caixaIndice) ? GREEN + BOLD : YELLOW;
                    buffer.append(cor).append(caixas.get(caixaIndice)[linha]).append(RESET).append("   ");
                }
                buffer.append("\n");
            }
            buffer.append("\n");
        }
    }

    private static int calcularInicioPagina(int selecionada, int total) {
        return calcularInicioPagina(selecionada, total, CARTAS_POR_PAGINA);
    }

    private static int calcularInicioPagina(int selecionada, int total, int cartasPorPagina) {
        if (total <= cartasPorPagina) {
            return 0;
        }
        int pagina = Math.max(0, selecionada) / cartasPorPagina;
        int inicio = pagina * cartasPorPagina;
        return Math.min(inicio, Math.max(0, total - cartasPorPagina));
    }

    private static int calcularInicioScroll(int selecionada, int total, int cartasVisiveis) {
        if (total <= cartasVisiveis) {
            return 0;
        }
        int meio = cartasVisiveis / 2;
        int inicio = Math.max(0, selecionada - meio);
        return Math.min(inicio, total - cartasVisiveis);
    }

    private static List<String> montarLinhasHistorico(List<String> historico, int largura) {
        List<String> linhas = new ArrayList<>();
        if (historico == null || historico.isEmpty()) {
            linhas.add("Sem registros ainda.");
            return linhas;
        }

        int inicio = Math.max(0, historico.size() - 8);
        for (int i = inicio; i < historico.size(); i++) {
            String prefixo = i == inicio ? "> " : "- ";
            linhas.addAll(quebrarLinha(prefixo + historico.get(i), largura));
        }

        if (linhas.size() > HISTORICO_ALTURA) {
            return linhas.subList(linhas.size() - HISTORICO_ALTURA, linhas.size());
        }
        return linhas;
    }

    public static void mostrarHistorico(List<String> historico) {
        ModoTerminal modo = new ModoTerminal();
        modo.ativar();
        try {
            System.out.print(montarTelaHistorico(historico));
            System.out.flush();

            if (!modo.estaAtivo()) {
                lerTeclaComDrenagem();
                return;
            }

            while (true) {
                    int tecla = modo.lerTecla();
                    if (tecla == '\n' || tecla == '\r' || tecla == 'r' || tecla == 'R'
                            || tecla == 'q' || tecla == 'Q' || tecla == '0') {
                        return;
                    }
                dormir(80);
            }

        } catch (IOException e) {
            return;
        } finally {
            modo.restaurar();
        }
    }

    public static void mostrarMapaAtual() {
        ModoTerminal modo = new ModoTerminal();
        modo.ativar();
        try {
            Mapa mapa = mapaAtual;
            NoMapa atual = noAtual;
            if (mapa == null || atual == null) {
                mostrarMensagem("Mapa", "O mapa ainda nao esta disponivel.");
                return;
            }

            System.out.print(montarAbaMapa(mapa, atual, atual.getProximos(), 0));
            System.out.flush();

            if (!modo.estaAtivo()) {
                lerTeclaComDrenagem();
                return;
            }

            while (true) {
                int tecla = modo.lerTecla();
                if (tecla == '\n' || tecla == '\r' || tecla == 'm' || tecla == 'M'
                        || tecla == 'q' || tecla == 'Q' || tecla == '0') {
                    return;
                }
                dormir(80);
            }
        } catch (IOException e) {
            return;
        } finally {
            modo.restaurar();
        }
    }

    private static String montarTelaHistorico(List<String> historico) {
        StringBuilder buffer = new StringBuilder();
        List<String> linhas = montarLinhasHistorico(historico, HISTORICO_LARGURA - 4);
        buffer.append(CLEAR_SCREEN);
        buffer.append(MAGENTA).append(BOLD).append("ECHOES OF THE ABYSS").append(RESET);
        buffer.append(DIM).append("  |  Registro da batalha").append(RESET).append("\n\n");
        buffer.append(MAGENTA).append(BOLD).append(bordaSuperior(HISTORICO_LARGURA)).append(RESET).append("\n");
        buffer.append(MAGENTA).append(BOLD).append("| ").append(ajustar("REGISTRO", HISTORICO_LARGURA - 4))
                .append(" |").append(RESET).append("\n");
        buffer.append(MAGENTA).append(BOLD).append(bordaMeio(HISTORICO_LARGURA)).append(RESET).append("\n");

        for (int i = 0; i < HISTORICO_ALTURA; i++) {
            String texto = i < linhas.size() ? linhas.get(i) : "";
            buffer.append(DIM).append("| ").append(ajustar(texto, HISTORICO_LARGURA - 4)).append(" |")
                    .append(RESET).append("\n");
        }

        buffer.append(MAGENTA).append(BOLD).append(bordaInferior(HISTORICO_LARGURA)).append(RESET).append("\n\n");
        buffer.append(CYAN).append("Enter, R, Q ou 0 para voltar ao combate.").append(RESET).append("\n");
        return buffer.toString();
    }

    private static List<String> quebrarLinha(String texto, int largura) {
        List<String> linhas = new ArrayList<>();
        String restante = texto;
        while (restante.length() > largura) {
            linhas.add(restante.substring(0, largura));
            restante = "  " + restante.substring(largura).trim();
        }
        linhas.add(restante);
        return linhas;
    }

    private static void adicionarNaPosicao(StringBuilder buffer, int linha, int coluna, String texto) {
        buffer.append("\033[").append(linha).append(";").append(coluna).append("H").append(texto);
    }

    private static String bordaSuperior(int largura) {
        return "+" + repetir("-", largura - 2) + "+";
    }

    private static String bordaMeio(int largura) {
        return "+" + repetir("-", largura - 2) + "+";
    }

    private static String bordaInferior(int largura) {
        return "+" + repetir("-", largura - 2) + "+";
    }

    private static void adicionarAnimacao(StringBuilder buffer, int frame) {
        int largura = CHARACTER_COLUMN_WIDTH * 2 + COLUMN_GAP.length();
        int posicao = frame % Math.max(1, largura - 8);
        String linha = repetir(" ", posicao) + "~~";
        buffer.append(DIM).append(ajustar(linha, largura)).append(RESET).append("\n");
    }

    private static String[] montarCarta(int numero, Carta carta) {
        String[] linhas = new String[CARD_HEIGHT];
        List<String> descricao = envolverTexto(carta.getDescricao(), CARD_WIDTH - 4, 3);
        linhas[0] = "+" + repetir("-", CARD_WIDTH - 2) + "+";
        linhas[1] = "| " + ajustar(numero + ". " + carta.getNome(), CARD_WIDTH - 4) + " |";
        linhas[2] = "| " + ajustar("", CARD_WIDTH - 4) + " |";
        linhas[3] = "| " + ajustar(descricao.size() > 0 ? descricao.get(0) : "", CARD_WIDTH - 4) + " |";
        linhas[4] = "| " + ajustar(descricao.size() > 1 ? descricao.get(1) : "", CARD_WIDTH - 4) + " |";
        linhas[5] = "| " + ajustar(descricao.size() > 2 ? descricao.get(2) : "", CARD_WIDTH - 4) + " |";
        linhas[6] = "| " + ajustar(carta.getCusto() + " energia", CARD_WIDTH - 4) + " |";
        linhas[7] = "+" + repetir("-", CARD_WIDTH - 2) + "+";
        return linhas;
    }

    private static List<String> envolverTexto(String texto, int largura, int maxLinhas) {
        List<String> linhas = new ArrayList<>();
        String[] palavras = texto.split("\\s+");
        StringBuilder linhaAtual = new StringBuilder();

        for (String palavra : palavras) {
            if (linhaAtual.length() == 0) {
                linhaAtual.append(palavra);
            } else if (linhaAtual.length() + 1 + palavra.length() <= largura) {
                linhaAtual.append(" ").append(palavra);
            } else {
                linhas.add(linhaAtual.toString());
                linhaAtual = new StringBuilder(palavra);
                if (linhas.size() == maxLinhas) {
                    break;
                }
            }
        }

        if (linhas.size() < maxLinhas && linhaAtual.length() > 0) {
            linhas.add(linhaAtual.toString());
        }

        if (linhas.size() > maxLinhas) {
            linhas = new ArrayList<>(linhas.subList(0, maxLinhas));
        }

        if (!linhas.isEmpty() && texto.length() > String.join(" ", linhas).length()) {
            int ultima = linhas.size() - 1;
            String linha = linhas.get(ultima);
            linhas.set(ultima, linha.length() > 3 ? linha.substring(0, Math.max(0, largura - 3)) + "..." : linha);
        }

        return linhas;
    }

    private static String ajustar(String texto, int largura) {
        String limpo = texto.length() > largura ? texto.substring(0, largura - 3) + "..." : texto;
        return String.format("%-" + largura + "s", limpo);
    }

    private static String repetir(String texto, int vezes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vezes; i++) {
            sb.append(texto);
        }
        return sb.toString();
    }

    private static void dormir(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static class ModoTerminal {
        private String estadoOriginal;
        private boolean ativo;
        private InputStream entrada;

        void ativar() {
            estadoOriginal = executarComSaida("sh", "-c", "stty -g < /dev/tty");
            if (estadoOriginal == null || estadoOriginal.isBlank()) {
                ativo = false;
                return;
            }
            ativo = executarSemSaida("sh", "-c", "stty -icanon -echo min 0 time 0 < /dev/tty");
            if (ativo) {
                try {
                    entrada = Files.newInputStream(Path.of("/dev/tty"));
                } catch (IOException e) {
                    restaurarEstadoTerminal();
                    ativo = false;
                }
            }
            if (ativo) {
                System.out.print(HIDE_CURSOR);
                System.out.flush();
            }
        }

        boolean estaAtivo() {
            return ativo;
        }

        boolean temEntrada() throws IOException {
            return entradaDisponivel() > 0;
        }

        int entradaDisponivel() throws IOException {
            return entrada == null ? 0 : entrada.available();
        }

        int lerTecla() throws IOException {
            if (entrada == null) {
                return -1;
            }
            return entrada.read();
        }

        void restaurar() {
            if (!ativo) {
                fecharEntrada();
                return;
            }
            restaurarEstadoTerminal();
            System.out.print(SHOW_CURSOR);
            System.out.flush();
            fecharEntrada();
            ativo = false;
        }

        private void restaurarEstadoTerminal() {
            if (estadoOriginal != null && !estadoOriginal.isBlank()) {
                executarSemSaida("sh", "-c", "stty " + estadoOriginal.trim() + " < /dev/tty");
            } else {
                executarSemSaida("sh", "-c", "stty sane < /dev/tty");
            }
        }

        private void fecharEntrada() {
            if (entrada == null) {
                return;
            }
            try {
                entrada.close();
            } catch (IOException e) {
                // Nada a fazer: o terminal ja foi restaurado.
            }
            entrada = null;
        }

        private String executarComSaida(String... comando) {
            try {
                Process processo = new ProcessBuilder(comando).redirectErrorStream(true).start();
                byte[] bytes = processo.getInputStream().readAllBytes();
                processo.waitFor();
                return new String(bytes, StandardCharsets.UTF_8);
            } catch (IOException e) {
                return "";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "";
            }
        }

        private boolean executarSemSaida(String... comando) {
            try {
                Process processo = new ProcessBuilder(comando).redirectErrorStream(true).start();
                processo.getInputStream().readAllBytes();
                return processo.waitFor() == 0;
            } catch (IOException e) {
                // Em IDEs sem /dev/tty, o jogo segue usando entrada comum.
                return false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
    }

    private static List<String> lerArte(String caminho, List<String> fallback) {
        InputStream input = TerminalUI.class.getClassLoader().getResourceAsStream(caminho);
        if (input == null) {
            return lerArteDoArquivo(caminho, fallback);
        }

        List<String> linhas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            return fallback;
        }
        return linhas;
    }

    private static List<String> lerArteDoArquivo(String caminho, List<String> fallback) {
        Path[] candidatos = {
            Path.of("app/src/main/resources", caminho),
            Path.of("src/main/resources", caminho)
        };

        for (Path candidato : candidatos) {
            if (Files.exists(candidato)) {
                try {
                    return Files.readAllLines(candidato, StandardCharsets.UTF_8);
                } catch (IOException e) {
                    return fallback;
                }
            }
        }
        return fallback;
    }

    private static String normalizar(String nome) {
        String texto = nome.toLowerCase();
        texto = texto.replace("ç", "c").replace("ã", "a").replace("á", "a").replace("é", "e");
        return texto + ".txt";
    }

    private static List<String> artePadraoHeroi() {
        List<String> linhas = new ArrayList<>();
        linhas.add("        ___________");
        linhas.add("    _.-'  SILAS  '-._");
        linhas.add("   /_____.-''''-._____\\");
        linhas.add("          / _  _ \\");
        linhas.add("         | (o)(o) |");
        linhas.add("         |   ^    |");
        linhas.add("          \\ '--' /");
        linhas.add("       ___/'----'\\___");
        linhas.add("      /  /|  ||  |\\  \\");
        linhas.add("     /__/ | _||_ | \\__\\");
        linhas.add("       || | /__\\ | ||");
        linhas.add("       || |  ✦   | ||");
        linhas.add("      /__\\|______|/__\\");
        return linhas;
    }

    private static List<String> artePadraoInimigo() {
        List<String> linhas = new ArrayList<>();
        linhas.add("   /\\_/\\");
        linhas.add("  ( o o )");
        linhas.add("   \\ ^ /");
        linhas.add("  /|___|\\");
        return linhas;
    }
}
