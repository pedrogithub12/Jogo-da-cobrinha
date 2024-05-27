package cobrateste;

import java.util.Scanner;

public class CobraMain {

    public static void main(String[] args) throws InterruptedException {
        boolean gameOver = false;
        Jogo jogo = new Jogo(20, 20); // Passa as dimensões do tabuleiro
        CobraCorpo cc = new CobraCorpo(jogo);
        cc.carregarCorpo(10, 10); // Inicializa o corpo da cobra no centro do tabuleiro
        jogo.inicializarFruta(cc.getCorpo()); // Inicializa a fruta

        Scanner scanner = new Scanner(System.in);

        // Imprime o tabuleiro inicialmente
        jogo.jogar(cc.getCorpo());

        while (!gameOver) {
            // Atualiza a direção da cobra com a entrada do usuário, se disponível
            if (scanner.hasNextLine()) {
                String novaDirecao = scanner.nextLine().trim();
                if ("wasd".contains(novaDirecao) && novaDirecao.length() == 1) {
                    cc.setDirecao(novaDirecao);
                }
            }

            cc.andar(jogo.getFruta(), 20, 20);

            if (cc.isGameOver()) { // Verifica se o jogo acabou usando o método isGameOver
                gameOver = true;
                System.out.println("Game Over!");
            } else {
                jogo.jogar(cc.getCorpo());
                // Simula o tempo de jogo
                Thread.sleep(500);
            }
        }

        scanner.close();
    }
}
