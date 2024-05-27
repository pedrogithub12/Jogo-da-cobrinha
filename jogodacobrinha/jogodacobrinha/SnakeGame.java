package jogodacobrinha;

import java.util.Random;
import java.util.Scanner;

public class SnakeGame {
    private boolean gameOver;
    private Cobra cobra;
    private Tabuleiro tabuleiro;
    private Ponto fruta;

    public SnakeGame() {
        this.gameOver = false;
        this.cobra = new Cobra(20, 20); 
        this.tabuleiro = new Tabuleiro();
        this.fruta = gerarPosicaoAleatoria(); 
    }

    public void comecarJogo() {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            tabuleiro.desenharTabuleiro(cobra, fruta);
            processarEntrada(scanner);
            cobra.move();

            
            Ponto head = cobra.getBody().getFirst();
            int headX = head.getX();
            int headY = head.getY();

            
            int fruitX = fruta.getX();
            int fruitY = fruta.getY();

            
            if (headX == fruitX && headY == fruitY) {
                cobra.grow();
                fruta = gerarPosicaoAleatoria(); 
            }
        }
    }

    private void processarEntrada(Scanner scanner) {
        if (scanner.hasNext()) {
            String input = scanner.next();
            switch (input.toLowerCase()) {
                case "w":
                    if (cobra.getDirection() != Direcao.DOWN) {
                        cobra.setDirection(Direcao.UP);
                    }
                    break;
                case "s":
                    if (cobra.getDirection() != Direcao.UP) {
                        cobra.setDirection(Direcao.DOWN);
                    }
                    break;
                case "a":
                    if (cobra.getDirection() != Direcao.RIGHT) {
                        cobra.setDirection(Direcao.LEFT);
                    }
                    break;
                case "d":
                    if (cobra.getDirection() != Direcao.LEFT) {
                        cobra.setDirection(Direcao.RIGHT);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private Ponto gerarPosicaoAleatoria() {
        Random random = new Random();
        int x = random.nextInt(Tabuleiro.getWidth() - 2) + 1; 
        int y = random.nextInt(Tabuleiro.HEIGHT - 2) + 1; 
        return new Ponto(x, y);
    }
}
