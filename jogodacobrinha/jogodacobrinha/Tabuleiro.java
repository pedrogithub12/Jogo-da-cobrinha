package jogodacobrinha;

public class Tabuleiro {
    private static final int WIDTH = 20;
    static final int HEIGHT = 20;

    public void desenharTabuleiro(Cobra cobra, Ponto fruta) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (x == 0 || x == getWidth() - 1 || y == 0 || y == HEIGHT - 1) {
                    System.out.print("#");
                } else if (x == fruta.getX() && y == fruta.getY()) {
                    System.out.print("F");
                } else {
                    boolean imprimirCobra = false;
                    for (Ponto ponto : cobra.getBody()) {
                        if (ponto.getX() == x && ponto.getY() == y) {
                            System.out.print("O");
                            imprimirCobra = true;
                            break;
                        }
                    }
                    if (!imprimirCobra) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    public static int getWidth() {
        return WIDTH;
    }
}

