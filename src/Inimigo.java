import java.util.Random;

public class Inimigo extends Entidade {

    private int vulneravel; 
    private Publisher publisher;
    private int proximaAcao;

    public Inimigo(int vida, String nome, Publisher publisher) {
        super(nome, vida); // Chama o construtor de Entidade
        this.vulneravel = 0;
        this.publisher = publisher;
        sortearProximaAcao(); // Já define o que ele vai fazer no 1º turno
    }
    private void sortearProximaAcao() {
        Random rand = new Random();
        this.proximaAcao = rand.nextInt(2); 
    }
    public void agir(Heroi heroi) {
        if (this.isAtordoado()) {
            System.out.println("🌀 " + nome + " está ATORDOADO e não pode agir neste turno!");
            sortearProximaAcao(); // Sorteia a ação do próximo turno antes de pular
            return;
        }
        if (proximaAcao == 0) {
            int dano = 5; 
            System.out.println("💥 " + nome + " atacou causando " + dano + " de dano!");
            heroi.receberDano(dano);
        } else {
            System.out.println("🧪 " + nome + " expele uma névoa tóxica!");
            heroi.aplicarEfeito(new EfeitoVeneno(heroi, 2, publisher));
        }
        sortearProximaAcao(); 
    }
        public void anunciarIntencao() {
        if (this.isAtordoado()) {
            System.out.println(">>> INTENÇÃO: " + this.getNome() + " está atordoado e passará o turno.");
        } else if (proximaAcao == 0) {
            System.out.println(">>> INTENÇÃO: " + this.getNome() + " planeja atacar causando 5 de dano neste turno!");
        } else {
            System.out.println(">>> INTENÇÃO: " + this.getNome() + " planeja envenenar você neste turno!");
        }
    }
    public void aplicarVulneravel(int valor) {
        this.vulneravel += valor;
    }
    public void recebeDano(int dano) {
        if (this.vulneravel > 0) {
            dano += this.vulneravel; // Soma o dano extra
            System.out.println("⚠️ Ataque atingiu um ponto fraco! (+ " + this.vulneravel + " de dano)");
        }
        super.receberDano(dano); 
    }
}