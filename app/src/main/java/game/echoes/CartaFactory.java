package game.echoes;

import java.util.Random;

/**
 * Factory Method simples para centralizar a criação de cartas do jogo.
 */
public class CartaFactory {
    private static final java.util.Random RANDOM = new java.util.Random();

    /**
     * Construtor privado para evitar instan\u00e7iao de factory.
     */
    private CartaFactory() {
    }

    /**
     * Cria uma carta aleatória.
     * 
     * @return A carta criada
     */
    public static Carta criarCartaAleatoria() {
        return criarCartaPorTipo(RANDOM.nextInt(9));
    }

    /**
     * Cria uma carta do tipo especificado.
     * 
     * @param tipo O c\u00f3digo do tipo de carta (0-8)
     * @return A carta criada
     */
    public static Carta criarCartaPorTipo(int tipo) {
        switch (tipo) {
            case 0:
                return new CartaDano("Golpe Brutal", "Causa 8 de dano.", 1, 8);
            case 1:
                return new CartaEscudo("Barreira Mística", "Ganha 6 de escudo.", 1, 6);
            case 2:
                return new CartaVeneno("Toque Tóxico", "Aplica 3 de veneno por 2 turnos.", 1, 3, 2);
            case 3:
                return new CartaChama("Fogo Corrupto", "Causa 4 de dano e aplica queimadura.", 1, 4);
            case 4:
                return new CartaCura("Gota de Sanidade", "Cura 8 de vida.", 1, 8);
            case 5:
                return new CartaEnergetica("Adrenalina", "Recupera 2 de mana.", 0, 2);
            case 6:
                return new CartaMagica("Anomalia", "Aplica um efeito aleatório no alvo.", 2);
            case 7:
                return new CartaSacrificio("Preço de Sangue", "Perde 5 de vida, causa 15 de dano.", 1, 15, 5);
            default:
                return new CartaAtordoar("Sussurro do Abismo", "Impede a ação do inimigo.", 2, 1);
        }
    }
}
