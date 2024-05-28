package cobrateste;

import java.util.ArrayList;

public class CobraCorpo {

    private ArrayList<Posicao> corpo;
    private String direcao;
    private Jogo jogo;
    private boolean gameOver;

    public CobraCorpo(Jogo jogo) {
        this.jogo = jogo;
        this.gameOver = false;
    }

    public void setDirecao(String novaDirecao) {
    //direção é oposta
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
        direcao = "d";
    }

    public void andar(Posicao fruta, int largura, int altura) {
        if (gameOver) {
            return;
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
                novaCabeca = new Posicao(cabecaAtual.x, cabecaAtual.y);
                break;
        }

        // verifica colisão
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
        // colisão com as bordas
        if (posicao.x < 0 || posicao.x >= largura || posicao.y < 0 || posicao.y >= altura) {
            return true;
        }

        // colisão com o corpo
        for (int i = 1; i < corpo.size(); i++) {
            if (posicao.ehIgual(corpo.get(i))) {
                return true;
            }
        }
        

        return false;
    }
    
}
