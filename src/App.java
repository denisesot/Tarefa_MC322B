import java.util.*;

public class App implements Publisher {

    private List<Subscriber> subscribers = new ArrayList<>();
    private List<Subscriber> paraRemover = new ArrayList<>();

    @Override
    public void inscrever(Subscriber s) {
        subscribers.add(s);
    }

    @Override
    public void desinscrever(Subscriber s) {
        paraRemover.add(s);
    }
    @Override
    public void notificar(String evento) {
        for (Subscriber s : subscribers) {
            s.Notificacao(evento);
        }
        subscribers.removeAll(paraRemover);
        paraRemover.clear();
    }
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
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        Heroi heroi = new Heroi(40, "Silas Vane");
        Inimigo inimigo = new Inimigo(25, "Cthulhu", this);
        GerenciadorDeCartas gerenciador = new GerenciadorDeCartas(this);
        

        // INTRODUÇÃO
        imprimirLento("=================================================================================================================", 10);
        imprimirLento("                                               ECHOES OF THE ABYSS                                               ", 15);
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
        this.notificar("FIM_TURNO_" + heroi.getNome().toUpperCase());
            
            if (inimigo.estaVivo() && heroi.estaVivo()) {
                System.out.println("\n---Turno de " + inimigo.getNome() + "---");
                
                // Mudou de atacar() para agir(), já que agora ele tem mecânicas novas
                inimigo.agir(heroi);
                
                // ---> NOTIFICAÇÃO DE FIM DE TURNO DO INIMIGO <---
                this.notificar("FIM_TURNO_" + inimigo.getNome().toUpperCase());
            }
        }   
        if (heroi.estaVivo()) {
            System.out.println("\nVocê baniu o horror cósmico de volta para o abismo!");
        } else {
            System.out.println("\nSua mente sucumbiu à loucura...Game Over.");
        }

        scanner.close();
    }
    public static void main(String[] args) {
        App meuJogo = new App();
        meuJogo.iniciar();
    }
}  

