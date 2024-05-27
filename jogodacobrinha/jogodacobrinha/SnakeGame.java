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
        this.cobra = new Cobra(20, 20); // Ajusta o tamanho do tabuleiro aqui
        this.tabuleiro = new Tabuleiro();
        this.fruta = gerarPosicaoAleatoria(); // Gera uma posição inicial aleatória para a fruta
    }

    public void comecarJogo() {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            tabuleiro.desenharTabuleiro(cobra, fruta);
            processarEntrada(scanner);
            cobra.move();

            // Obter a posição da cabeça da cobra
            Ponto head = cobra.getBody().getFirst();
            int headX = head.getX();
            int headY = head.getY();

            // Obter a posição da fruta
            int fruitX = fruta.getX();
            int fruitY = fruta.getY();

            // Verificar se a cabeça da cobra alcançou a fruta
            if (headX == fruitX && headY == fruitY) {
                cobra.grow();
                fruta = gerarPosicaoAleatoria(); // Gera uma nova posição para a fruta
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
        int x = random.nextInt(Tabuleiro.getWidth() - 2) + 1; // Evita as bordas do tabuleiro
        int y = random.nextInt(Tabuleiro.HEIGHT - 2) + 1; // Evita as bordas do tabuleiro
        return new Ponto(x, y);
    }
}
