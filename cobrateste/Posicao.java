package cobrateste;

public class Posicao {
    int x, y;

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean ehIgual(Posicao outra) {
        return this.x == outra.x && this.y == outra.y;
    }
}
