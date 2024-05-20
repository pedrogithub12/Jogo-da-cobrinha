package jogodacobrinha;

import java.util.Scanner;

public class SnakeGame {
    
    private boolean gameOver;
    private Snake cobra;
    private Tabuleiro tabuleiro;
    private Ponto fruta;

    public SnakeGame(){
        this.gameOver = false;
        this.cobra = new Snake(0, 0);
        this.tabuleiro = new Tabuleiro();
        this.fruta = PosicaoAleatoria(); // Gera uma posição inicial aleatória para a fruta
    }
    public void comecarJogo(){
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            tabuleiro.desenharTabuleiro(cobra, fruta);
            Indicador(scanner);
            cobra.move();

            //Obter a posição da cabeça da cobra
            Ponto head = cobra.getBody().getFirst();
            int headX = head.getX();
            int headY = head.getY();

            //Obter a posição da fruta
            int fruitX = fruta.getX();
            int fruitY = fruta.getY();

            //verificar se a cabeça da cobra alcançou a fruta
            if (headX == fruitX && headY == fruitY) {
                cobra.grow(); 
                /**grow(); metodo responsavel por fazer a cobra crescer
                 apos ter comido a fruta assim adicionando um novo segmento 
                 e estendendo seu comprimento
                **/
                fruta = PosicaoAleatoria(); //gera uma nova posição para a fruta
            }
        }
    }
            private void Indicador(Scanner scanner){
                if (scanner.hasNext()) {
                    /**hasNext verifica se há mais tokens para a leitura 
                      na fonte de entrada que o scanner esta lendo
                     */
                    String input = scanner.next();
                    /**input armazena a entrada do usuario lida pelo scanner 
                     */
                    switch (input.toLowerCase()) {
                         /**toLowerCase, permite que eu digite as letras em maiusculo 
                        e minusculo sem a possiblidade de dar erro no codigo
                        **/
                        case "w":
                            if (cobra.getDirection() != Direction.DOWN) {
                                cobra.setDirection(Direction.UP);
                            }
                            break;
                    case "s":
                            if (cobra.getDirection() != Direction.UP) {
                                cobra.setDirection(Direction.DOWN);
                            }
                            break;
                    case "a":
                            if (cobra.getDirection() != Direction.RIGHT) {
                                cobra.setDirection(Direction.LEFT);
                            }
                            break;
                    case "d":
                            if (cobra.getDirection() != Direction.LEFT) {
                                cobra.setDirection(Direction.RIGHT);
                            }
                            break;
                                default:
                            break;
                    }
                }
    }
        private ponto PosicaoAleatoria(){
            Aleatorio aleatorio = new Aleatorio();
            int y = aleatorio.nextInt(Tabuleiro.getWidth() - 2 ) + 1; //Evita as bordas do tabuleiro 
            /**
             basicamente este trecho serve para que a fruta não apareça nas bordas laterais do tabuleiro
             */
            int x = aleatorio.nextInt(Tabuleiro.HEIGHT - 2) + 1; // Evita as bordas do tabuleiro 
            /** esse trecho segue a mesma logica do trecho acima e 
             garante que a fruta não apareça nas bordas superior e inferior do tabuleiro
             */
            return new fruta(x, y);
            /*
             basicamente este trecho gera uma nova posição aleatoria para a fruta 

             Cria um novo objeto Fruta com as coordenadas x e y, para garantir que a fruta 
             esteja dentro dos limites do tabuleiro e não nas bordas.
             */
        }
}
