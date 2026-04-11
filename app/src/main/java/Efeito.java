public interface Efeito {
    void aplicar();      // executa o efeito (ex: dano de veneno)
    void reduzirDuracao();
    boolean expirou();   // verifica se acabou
} 
