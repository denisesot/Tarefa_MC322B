public class CartaVeneno extends Carta {
    private int acumulos;
    private Publisher publisher;

    public CartaVeneno(int custo, int acumulos, Publisher publisher) {
        super("Frasco Sombrio", "Aplica " + acumulos + " de Veneno no inimigo.", custo);
        this.acumulos = acumulos;
        this.publisher = publisher;
    }
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("🧪 Silas lança um frasco de ácido abissal!");
        inimigo.aplicarEfeito(new EfeitoVeneno(inimigo, acumulos, publisher));
    }
}