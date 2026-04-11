package game.echoes;
import java.util.*;

/**
 * Classe principal do jogo Echoes of the Abyss.
 * 
 * Responsável por inicializar o jogo, controlar o fluxo da batalha
 * entre o herói e o inimigo, gerenciar turnos e interações com o usuário.
 */


public class App {

    /**
     * Imprime texto caractere por caractere simulando efeito de digitação.
     * 
     * @param texto Texto a ser exibido
     * @param delay Tempo de espera entre caracteres (em ms)
     */

    public static void imprimirLento(String texto, int delay) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    /**
     * Método principal que inicia o jogo.
     * 
     * Responsável por:
     * - Criar o herói e o inimigo
     * - Controlar o loop da batalha
     * - Gerenciar turnos e entrada do usuário
     * 
     * @param args argumentos de linha de comando (não utilizados)
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Heroi heroi = new Heroi(40, "Silas Vane");
        Inimigo inimigo = new Inimigo(25, "Cthulhu");
        
        GerenciadorDeCartas gerenciador = new GerenciadorDeCartas();
        imprimirLento("=================================================================================================================", 10);
        imprimirLento("                                                ECHOES OF THE ABYSS                                              ", 15);
        imprimirLento("=================================================================================================================", 10);
        imprimirLento("Silas Vane, the Archivist of Shadows, opens a forbidden tome recovered from a forgotten archive.", 75);
        imprimirLento("", 50);
        imprimirLento("The pages whisper secrets older than humanity itself.", 75);
        imprimirLento("", 50);
        imprimirLento("Something stirs beneath the ocean...", 90);
        imprimirLento(".", 90);
        imprimirLento(".", 90);
        imprimirLento(".", 90);

        // ASCII DO CTHULHU
        System.out.println("""
                            .........................:.*%%@@%%-:.............................................#*%@@@@@@%*-::...................:
                            %%%%%#%##%%%%%%%%####%##.**%%@%+@%%*.%#-:#-.:-###########*#=...#############****:%%#####*#@@@*#######%%%%%%#%%%%%%%
                            ------::::::::::::---::.=%@%:-::.%%+.--+#@%%-=...:....................:....:::..=%-....::-%%@@:::::::::::::--------
                            .......................:....-...#%+...:%@@#%%........-:-.--=:::+:+:.=:........++#@=:......-%@%-....................
                            %%#################***=:#@@#+*+++=+**+@*++********:..+:::#++%==%%+--=*.:*****#%:=*@**#*=++-%@@*#############%######
                            **-:*+:=======-===+--+@@@@@*.::::...::@@=.:::::-.::-+*#-:%#@%##@%#=.##:+.+.:=....:*@%==-.:-%@%+====================
                            @@%@%::...........:=##@%%@@%:........:%@*:.......-#-#-%#-:+@@@@@=++#%%%%:+:..::..-%%-.....-%@*:...........-::......
                            #*%#@**==+##***##.==%@@#*#@@%=:**++**+*%@#*-..-:::*@%%%@%**%@@@%#*@@@#@%%#=*:.:+=%%#***#:.%%%##***+*++++:-%@%%%@#=:
                            ...=@%%*+:......::*@%%-..#@@@#+::::::--+:@%%*%*++:@@@%%@@%*%@@@*%%@%%@@@%#.%.*%%@-@+::.:.=%@#.......+--.#%@@@@@@@@%
                            .::-%=:........-.%+@%::..:*#@@@@@@+=:-.::=-..%@@%%===@@@@%#%@@@*%%@@@@:-*=@@%@%:=%%+..:.%@@%......+#+..#=@-::::+%@@
                            ***#.:-#**#****=+@*@#**********#%##@@@@@%@%@##+.@@@@#=-@@@%%@@@@@@@@--*#%@@@@-*#@-*:@-%%@@###*******%:-#*@%#***+##@
                            ---:+.::::::::-=@@#@*.:------::.+++*++*%@#@@@@@%#=@@@%@%*-@@@@@@@%=#%#@@@@%-#%%@%#=#%%%%%=-----::::=*%#%%@-..:::-#=
                            """);

        // REVELAÇÃO DO BOSS
        imprimirLento("The ocean trembles...", 40);
        imprimirLento("Ancient whispers echo in the darkness...", 40);
        imprimirLento("Reality begins to fracture...", 40);
        imprimirLento("A cosmic horror rises from the abyss...", 40);
        imprimirLento("CTHULHU HAS AWAKENED.", 50);

        System.out.println("\n=== BATALHA COMEÇOU ===");
        
        // Aplica efeitos ativos no início de cada turno (veneno, queimadura, etc.)
        heroi.aplicarEfeitos();
        inimigo.aplicarEfeitos();

        while (heroi.estaVivo() && inimigo.estaVivo()) {

            heroi.resetarMana();
            heroi.resetaEscudo();
        
            gerenciador.prepararNovoTurno();
            
            boolean turnoAtivo = true;
            
            while (turnoAtivo && heroi.estaVivo() && inimigo.estaVivo()) {
                System.out.println("\n-----------------------------------------------------------------------");
                System.out.println(heroi.getNome() + " - Vida: " + heroi.getVida() + ", Escudo: " + heroi.getEscudo());
                System.out.println("Mana: " + heroi.getMana());
                System.out.println("VS " + inimigo.getNome() + " | Vida: " + inimigo.getVida());
                inimigo.anunciarIntencao(); 
                gerenciador.exibirMao();
                System.out.println("\nEscolha uma ação:");
                System.out.println("0 - Sair do jogo");
                System.out.println("99 - Encerrar turno");
                System.out.println("\nDigite o número da carta: ");

                int escolha = scanner.nextInt();
                
                if (escolha == 0) {
                    System.out.println("Você abandonou a batalha...");
                    System.exit(0);
                }
                if (escolha == 99) {
                    turnoAtivo = false;
                } else {
                    gerenciador.jogarCarta(escolha -1, heroi, inimigo);
                }

                if (heroi.getMana() <= 0) {
                    System.out.println("\nSua mana acabou! Encerrando turno...");
                    turnoAtivo = false;
                }
            } 
            
            // TURNO INIMIGO
            if (inimigo.estaVivo() && heroi.estaVivo()) {
                System.out.println("\n---Turno de " + inimigo.getNome() + "---");
                if (inimigo.estaVivo()) {
                    if (!inimigo.estaAtordoado()) {
                        inimigo.atacar(heroi);
                    } else {
                        System.out.println("💫 " + inimigo.getNome() + " está atordoado e perdeu o turno!");
                        inimigo.setAtordoado(false); 
                    }
                }
            }  
        } 
        if (heroi.estaVivo()) {
            System.out.println("\nVocê baniu o horror cósmico de volta para o abismo!");
        } else {
            System.out.println("\nSua mente sucumbiu à loucura...Game Over.");
        }

        scanner.close();
    } 
} 