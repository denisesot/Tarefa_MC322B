public class CartaAtordoar extends Carta {
    private Publisher publisher;
    public CartaAtordoar(int custo, Publisher publisher) {
        super("Grito do Vazio", "Atordoa o inimigo por 1 turno.", custo);
        this.publisher = publisher;
    }
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("🌀 Um ruído ensurdecedor paralisa o inimigo!");
        inimigo.aplicarEfeito(new EfeitoAtordoar(inimigo, 1, publisher));
    }
}