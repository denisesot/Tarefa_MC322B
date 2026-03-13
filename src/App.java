import java.util.Scanner;

public class App {

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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Heroi heroi = new Heroi(40, "Silas Vane");
        Inimigo inimigo = new Inimigo(25, "Cthulhu");
        CartaEscudo escudo = new CartaEscudo("Ward of Protection", 1, 5);
        CartaDano espada = new CartaDano("Forbidden Blade", 1, 6);
        CartaCorneta corneta = new CartaCorneta("Corneta de Guerra", 1, 2);

        // INTRODUÇÃO
        imprimirLento("=================================================================================================================", 10);
        imprimirLento("                                               ECHOES OF THE ABYSS                                               ", 15);
        imprimirLento("=================================================================================================================", 10);
        imprimirLento("Silas Vane, the Archivist of Shadows, opens a forbidden tome recovered from a forgotten archive.", 75);
        imprimirLento("", 50);
        imprimirLento("The pages whisper secrets older than humanity itself.", 75);
        imprimirLento("", 50);
        imprimirLento("Something stirs beneath the ocean...", 90);
        imprimirLento("", 100000000);


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
            heroi.resetarMana(); // início do turno
            heroi.resetaEscudo();
            boolean turno = true;
            while (turno) {
                System.out.println("\n------------------");
                System.out.println(heroi.getNome() + " | Vida: " + heroi.getVida() + " | Escudo: " + heroi.getEscudo());
                System.out.println("Mana: " + heroi.getMana());
                System.out.println("VS");
                System.out.println(inimigo.getNome() + " | Vida: " + inimigo.getVida());
                System.out.println("\nEscolha uma ação:");
                System.out.println("0 - Sair do jogo");
                System.out.println("1 - Usar carta de dano");
                System.out.println("2 - Usar carta de escudo");
                System.out.println("3 - Corneta de Guerra");
                System.out.println("4 - Encerrar turno");
                System.out.print("\n ");

                int escolha = scanner.nextInt();
                if (escolha == 0){
                    System.out.println("Você abandonou a batalha...");
                    scanner.close();
                    System.exit(0);
                }
                if (escolha == 1) {
                    espada.usar(heroi, inimigo);
                } else if (escolha == 2) {
                    escudo.usar(heroi);
                } else if (escolha == 3) {
                    corneta.usar(heroi, inimigo);
                } else if (escolha == 4) {
                    turno = false; 
                } else {
                    System.out.println("Opção inválida!");
                }   
                if (!inimigo.estaVivo()) {
                    break;
                }
                if (heroi.getMana() == 0) {
                    System.out.println("Você ficou sem mana!");
                    turno = false;
                }
            }
            if (inimigo.estaVivo()) { // turno do inimigo
                System.out.println("\nTurno do inimigo!");
                inimigo.atacar(heroi);
            }
        }
        if (heroi.getVida() > 0) {
            System.out.println("\nVocê venceu!");
        } else {
            System.out.println("\nVocê foi derrotado!");

        }
        scanner.close();
    }
}