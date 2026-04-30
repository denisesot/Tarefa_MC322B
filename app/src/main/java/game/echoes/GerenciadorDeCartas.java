package game.echoes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/****
 * Classe responsável por gerenciar o baralho de cartas do jogador.
 * 
 * O GerenciadorDeCartas controla o baralho, a mão e a pilha de descarte,
 * além de fornecer métodos para preparar o turno, exibir a mão e jogar cartas.
 */
public class GerenciadorDeCartas {
    private List<Carta> baralho = new ArrayList<>();
    private List<Carta> mao = new ArrayList<>();
    private List<Carta> descarte = new ArrayList<>();

    public GerenciadorDeCartas(List<Carta> baralhoDoHeroi) { // copia as cartas do baralho do heroi para a batalha
        this.baralho.addAll(baralhoDoHeroi);
        Collections.shuffle(baralho);
    }
    
    /**
     * Prepara a mão do jogador para um novo turno, comprando cartas do baralho.
     * Se o baralho estiver vazio, recarrega a partir do descarte.
     */
    public void prepararNovoTurno() {
        descarte.addAll(mao);
        mao.clear();

        for (int i=0; i < 6; i++) {
            if (baralho.isEmpty()) {
                recarregarBaralho();
            }
            if (!baralho.isEmpty()) {
                mao.add(baralho.remove(0));
            }
        }
    
    }
    public void recarregarBaralho() {
        if (descarte.isEmpty()) return;
        baralho.addAll(descarte);
        descarte.clear();
        Collections.shuffle(baralho);
        }
    
    public void exibirMao() {
        System.out.println("\n---SUA MÃO---");
        for (int i = 0; i < mao.size(); i++) {
            Carta carta = mao.get(i);
            System.out.println((i + 1) + ". " + carta.getNome() + " (Custo: " + carta.getCusto() +" Energia) - " + carta.getDescricao());
        }
    }

    /**
     * Permite ao jogador jogar uma carta da mão, aplicando seu efeito e movendo-a para o descarte.
     * Verifica se o jogador tem energia suficiente antes de jogar a carta.
     * 
     * @param indice O índice da carta na mão (baseado em 0)
     * @param heroi O herói que está jogando a carta
     * @param inimigo O inimigo alvo da carta
     */
    public void jogarCarta(int indice, Heroi heroi, Inimigo inimigo) {
        if (indice >= 0 && indice < mao.size()) {
            Carta escolhida = mao.get(indice);
            if (heroi.getMana() >= escolhida.getCusto()) {
                heroi.gastarMana(escolhida.getCusto());
                escolhida.usar(heroi, inimigo); 
                descarte.add(escolhida);        
                mao.remove(indice);             
            } else {
                System.out.println("\n[X] Energia insuficiente para usar " + escolhida.getNome() + "!");
            }
        } else {
            System.out.println("Índice inválido!");
        }
    }
}

