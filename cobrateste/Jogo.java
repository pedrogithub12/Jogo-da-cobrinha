package cobrateste;

import java.util.ArrayList;

public class Jogo {
    private Fruta f;
    private Tabuleiro t;

    public Jogo(int largura, int altura) {
        f = new Fruta();
        t = new Tabuleiro(largura, altura);
    }

    public void inicializarFruta(ArrayList<Posicao> cobra) {
        f.gerarFruta(t.getTabuleiroComprimento(), t.getTabuleiroAltura(), cobra);
    }

    public void jogar(ArrayList<Posicao> corpo) {
        t.printTabuleiro(corpo, f.getFruta());
    }

    public void consumirFruta(ArrayList<Posicao> cobra) {
        f.gerarFruta(t.getTabuleiroComprimento(), t.getTabuleiroAltura(), cobra);
    }

    public Posicao getFruta() {
        return f.getFruta();
    }
}
