package cobrateste;

import java.util.ArrayList;

public class CobraCorpo {

    private ArrayList<Posicao> corpo;
    private String direcao;
    private Jogo jogo;
    private boolean gameOver;

    public CobraCorpo(Jogo jogo) {
        this.jogo = jogo;
        this.gameOver = false; // Inicializa a variável de game over como false
    }

    public void setDirecao(String novaDirecao) {
    // Verifica se a nova direção é oposta à direção atual
    if (("wasd".contains(novaDirecao) && novaDirecao.length() == 1) &&
        !(novaDirecao.equals("w") && direcao.equals("s")) &&
        !(novaDirecao.equals("s") && direcao.equals("w")) &&
        !(novaDirecao.equals("a") && direcao.equals("d")) &&
        !(novaDirecao.equals("d") && direcao.equals("a"))) {
        this.direcao = novaDirecao;
    }
}

    public ArrayList<Posicao> getCorpo() {
        return corpo;
    }

    public void setCorpo(ArrayList<Posicao> corpo) {
        this.corpo = corpo;
    }

    public String getDirecao() {
        return direcao;
    }



    public boolean isGameOver() {
        return gameOver;
    }

    public void carregarCorpo(int xInicial, int yInicial) {
        corpo = new ArrayList<>();
        corpo.add(new Posicao(xInicial, yInicial));
        direcao = "d"; // Inicializa a direção para "d" (direita)
    }

    public void andar(Posicao fruta, int largura, int altura) {
        if (gameOver) {
            return; // Se o jogo já acabou, não podemos mover a cobra
        }

        Posicao cabecaAtual = corpo.get(0);
        Posicao novaCabeca = null;

        switch (direcao) {
            case "w":
                novaCabeca = new Posicao(cabecaAtual.x, cabecaAtual.y - 1);
                break;
            case "a":
                novaCabeca = new Posicao(cabecaAtual.x - 1, cabecaAtual.y);
                break;
            case "s":
                novaCabeca = new Posicao(cabecaAtual.x, cabecaAtual.y + 1);
                break;
            case "d":
                novaCabeca = new Posicao(cabecaAtual.x + 1, cabecaAtual.y);
                break;
            default:
                novaCabeca = new Posicao(cabecaAtual.x, cabecaAtual.y); // Caso a direção seja inválida, mantemos a cabeça no lugar
                break;
        }

        // Verifica se a nova posição da cabeça colide com o corpo da cobra ou as bordas do tabuleiro
        if (verificaColisao(novaCabeca, largura, altura)) {
            gameOver = true;
            return;
        }

        corpo.add(0, novaCabeca);
        if (!novaCabeca.ehIgual(fruta)) {
            corpo.remove(corpo.size() - 1);
        } else {
            jogo.consumirFruta(corpo);
        }
    }

    private boolean verificaColisao(Posicao posicao, int largura, int altura) {
        // Verifica se a nova posição da cabeça colide com as bordas do tabuleiro
        if (posicao.x < 0 || posicao.x >= largura || posicao.y < 0 || posicao.y >= altura) {
            return true;
        }

        // Verifica se a nova posição da cabeça colide com o corpo da cobra
        for (int i = 1; i < corpo.size(); i++) {
            if (posicao.ehIgual(corpo.get(i))) {
                return true;
            }
        }
        

        return false;
    }
    
}
