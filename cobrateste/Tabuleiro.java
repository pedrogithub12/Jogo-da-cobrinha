package cobrateste;

import java.util.ArrayList;

public class Tabuleiro {

    private int tabuleiroAltura;
    private int tabuleiroComprimento;

    public Tabuleiro(int largura, int altura) {
        this.tabuleiroAltura = altura;
        this.tabuleiroComprimento = largura;
    }

    public int getTabuleiroAltura() {
        return tabuleiroAltura;
    }

    public int getTabuleiroComprimento() {
        return tabuleiroComprimento;
    }

    public void printTabuleiro(ArrayList<Posicao> cobra, Posicao fruta) {
        for (int y = 0; y < tabuleiroAltura; y++) {
            for (int x = 0; x < tabuleiroComprimento; x++) {
                Posicao posAtual = new Posicao(x, y);
                if (posicaoNaCobra(posAtual, cobra)) {
                    System.out.print("O"); // cobra
                } else if (posAtual.ehIgual(fruta)) {
                    System.out.print("F"); // fruta
                } else {
                    System.out.print("."); // resto
                }
            }
            System.out.println();
        }
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
