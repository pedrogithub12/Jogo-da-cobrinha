package cobrateste;

import java.util.Scanner;

public class CobraMain {

    public static void main(String[] args) throws InterruptedException {
        boolean gameOver = false;
        Jogo jogo = new Jogo(20, 20);
        CobraCorpo cc = new CobraCorpo(jogo);
        cc.carregarCorpo(10, 10); // centro do tabuleiro
        jogo.inicializarFruta(cc.getCorpo());

        Scanner scanner = new Scanner(System.in);

        
        jogo.jogar(cc.getCorpo());

        while (!gameOver) {
            if (scanner.hasNextLine()) {
                String novaDirecao = scanner.nextLine().trim();
                if ("wasd".contains(novaDirecao) && novaDirecao.length() == 1) {
                    cc.setDirecao(novaDirecao);
                }
            }

            cc.andar(jogo.getFruta(), 20, 20);

            if (cc.isGameOver()) {
                gameOver = true;
                System.out.println("Game Over!");
            } else {
                jogo.jogar(cc.getCorpo());
                //deixa o terminal mais fluído para jogar
                Thread.sleep(500);
            }
        }

        scanner.close();
    }
}
