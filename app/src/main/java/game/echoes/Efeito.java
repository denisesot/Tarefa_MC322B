package game.echoes;
/**
 * Interface que representa um efeito que pode ser aplicado a uma entidade (herói ou inimigo).
 * 
 * Efeitos podem ser coisas como veneno, atordoamento, queimadura, etc.
 * Cada efeito tem um método para aplicar seu efeito, reduzir sua duração e verificar se expirou.
 */
public interface Efeito {
    /* Aplica o efeito na entidade. Ex: causar dano de veneno, impedir ação por atordoamento, etc. */
    void aplicar();      
    
    /* Reduz a duração do efeito. */
    void reduzirDuracao();

    /**
     * Verifica se o efeito terminou.
     * 
     * @return true se o efeito expirou, false caso contrário
     */
    boolean expirou();   
} 



