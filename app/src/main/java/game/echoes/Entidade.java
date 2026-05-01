package game.echoes;
import java.util.*;

/****
 * Classe abstrata que representa uma entidade no jogo, como o herói ou o inimigo.
 * 
 * Cada entidade possui nome, vida, escudo e pode ser afetada por efeitos (veneno, atordoamento, etc.).
 * A classe fornece métodos para receber dano, ganhar escudo, curar-se e gerenciar efeitos ativos.
 */

public abstract class Entidade {
    protected String nome;
    protected int escudo;
    protected int vida;
    protected int vidaMax;
    
    protected boolean atordoado = false;

    protected List<Efeito> efeitos = new ArrayList<>();

    public Entidade(String nome, int vidaMax) {
        this.nome = nome;
        this.vidaMax = vidaMax;
        this.vida = vidaMax;
        this.escudo = 0;
    }

    /**
     * Adiciona um efeito ativo à entidade.
     * 
     * @param efeito O efeito a ser adicionado
     */

    public void adicionarEfeito(Efeito efeito) {
        efeitos.add(efeito);
    }
    /**
     * Aplica todos os efeitos ativos na entidade.
     * Remove efeitos expirados automaticamente.
     */

    public void aplicarEfeitos() {
        Iterator<Efeito> it = efeitos.iterator();
        
        while (it.hasNext()) {
            Efeito e = it.next();
            e.aplicar();
            e.reduzirDuracao();
            if (e.expirou()) {
                it.remove();
            }
        }
    }

    /**
     * Aplica dano considerando o escudo antes da vida.
     * 
     * @param dano valor do dano recebido
     */

    public void receberDano(int dano) {
        if (dano >= escudo) {
            dano -= escudo;
            escudo = 0;
            vida -= dano;
            if (vida < 0) {
                vida = 0;
            }
        } else {
            escudo -= dano;
        }
    }

    /**
     * Ganha escudo.
     * 
     * @param ganho valor do escudo a ser adicionado
     */
    
    public void ganharEscudo(int ganho) {
        escudo += ganho;
    }
    /**
     * Reseta o escudo da entidade.
     */
    public void resetaEscudo() {
        escudo = 0;
    }

    /**
     * Curar a entidade.
     * 
     * @param valor quantidade de vida a ser recuperada
     */

    public void curar(int valor) {
        this.vida += valor;
        if (this.vida > this.vidaMax) {
            this.vida = this.vidaMax;
        }
    }

    /**
     * Remove efeitos temporários entre eventos do mapa.
     */
    public void limparEfeitos() {
        efeitos.clear();
        atordoado = false;
    }

    /**
     * Verifica se a entidade está atordoada.
     * 
     * @return true se a entidade estiver atordoada
     */

    public boolean estaAtordoado() {
        return atordoado;
    }

    public void setAtordoado(boolean status) {
        this.atordoado = status;
    }
   
   /**
     * Verifica se a entidade ainda está viva.
     * 
     * @return true se a vida for maior que zero
     */
   public boolean estaVivo() {
        return vida > 0;
    }

    // Getters
    /**
     * Retorna o nome da entidade.
     * 
     * @return O nome da entidade
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a vida atual da entidade.
     * 
     * @return Os pontos de vida atuais
     */
    public int getVida() {
        return vida;
    }

    /**
     * Retorna o escudo atual da entidade.
     * 
     * @return Os pontos de escudo atuais
     */
    public int getEscudo() {
        return escudo;
    }

    /**
     * Retorna a vida máxima da entidade.
     * 
     * @return O valor máximo de vida
     */
    public int getVidaMax() {
        return vidaMax;
    }

    public String getResumoEfeitos() {
        List<String> nomes = new ArrayList<>();
        for (Efeito efeito : efeitos) {
            if (efeito instanceof EfeitoVeneno) {
                nomes.add("Veneno");
            } else if (efeito instanceof EfeitoQueimadura) {
                nomes.add("Queimadura");
            } else if (efeito instanceof EfeitoAtordoar) {
                nomes.add("Atordoado");
            }
        }
        if (atordoado && !nomes.contains("Atordoado")) {
            nomes.add("Atordoado");
        }
        if (nomes.isEmpty()) {
            return "Sem efeitos";
        }
        return String.join(", ", nomes);
    }
}   
