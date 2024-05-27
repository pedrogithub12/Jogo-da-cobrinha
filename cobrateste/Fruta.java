package cobrateste;

import java.util.ArrayList;
import java.util.Random;

public class Fruta {
    private Posicao fruta;
    private Random random;

    public Fruta() {
        random = new Random();
    }

    public Posicao getFruta() {
        return fruta;
    }

    public void setFruta(Posicao fruta) {
        this.fruta = fruta;
    }

    public void gerarFruta(int largura, int altura, ArrayList<Posicao> cobra) {
        int x, y;

        do {
            x = random.nextInt(largura);
            y = random.nextInt(altura);
            fruta = new Posicao(x, y);
        } while (posicaoNaCobra(fruta, cobra)); // Garante que a fruta não apareça no corpo da cobra
    }

    private boolean posicaoNaCobra(Posicao pos, ArrayList<Posicao> cobra) {
        for (Posicao p : cobra) {
            if (pos.ehIgual(p)) {
                return true;
            }
        }
        return false;
    }
}
