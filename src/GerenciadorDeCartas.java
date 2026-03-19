import java.util.*;

public class GerenciadorDeCartas {
    private List<Carta> baralho = new ArrayList<>();
    private List<Carta> mao = new ArrayList<>();
    private List<Carta> descarte = new ArrayList<>();

    public GerenciadorDeCartas() {
        
        for(int i=0; i < 10; i++){
            baralho.add(new CartaDano("Forbidden Blade", "Causa 6 de dano.", 1, 6));
            baralho.add(new CartaEscudo("Ward of Protection", "Ganha 5 de escudo.", 1, 5));
            baralho.add(new CartaCorneta("Corneta de Guerra", "Deixa o inimigo vulnerável.", 1, 2));
        }
        Collections.shuffle(baralho);
    }
    
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