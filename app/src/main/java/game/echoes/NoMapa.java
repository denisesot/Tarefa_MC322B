package game.echoes;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um nó do mapa na estrutura de eventos do jogo.
 * 
 * Cada nó armazena um evento (Batalha, Loja, Escolha, Fogueira) e referências para os próximos nós,
 * formando uma árvore de navegação que permite ao jogador progredir pelo mapa do jogo.
 */
public class NoMapa {

    private Evento evento;
    private List<NoMapa> proximos = new ArrayList<>();
    private boolean visitado;

    /**
     * Cria um nó do mapa contendo um evento específico.
     * 
     * @param evento O evento que este nó executa
     */
    public NoMapa(Evento evento) {
        this.evento = evento;
    }

    /**
     * Retorna o evento armazenado neste nó.
     * 
     * @return O evento do nó
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Retorna o inimigo se este nó contém uma Batalha.
     * 
     * @return O inimigo da batalha, ou null se não é uma batalha
     */
    public Inimigo getInimigo() {
        if (evento instanceof Batalha) {
            return ((Batalha) evento).getInimigo();
        }
        return null;
    }

    /**
     * Retorna a lista de nós subsequentes.
     * 
     * @return Os nós para os quais este nó pode levar
     */
    public List<NoMapa> getProximos() {
        return proximos;
    }

    /**
     * Marca este nó como visitado pelo jogador.
     */
    public void marcarVisitado() {
        visitado = true;
    }

    /**
     * Verifica se o jogador já passou por este nó.
     * 
     * @return true se o nó já foi visitado, false caso contrário
     */
    public boolean foiVisitado() {
        return visitado;
    }

    /**
     * Adiciona um caminho para um próximo nó.
     * 
     * @param no O nó a ser conectado
     */
    public void adicionarCaminho(NoMapa no) {
        proximos.add(no);
    }
}
