package jogodacobrinha;

import java.util.LinkedList;

public class Cobra {
    private LinkedList<Ponto> body;
    private Direcao direction;
    private int boardWidth;
    private int boardHeight;

    public Cobra(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.body = new LinkedList<>();
        this.body.add(new Ponto(10, 10)); 
        this.direction = Direcao.RIGHT; 
    }

    public LinkedList<Ponto> getBody() {
        return body;
    }

    public void move() {
        Ponto head = body.getFirst();
        Ponto newHead = new Ponto(head.getX(), head.getY());

        switch (direction) {
            case UP:
                newHead.setY(newHead.getY() - 1);
                break;
            case DOWN:
                newHead.setY(newHead.getY() + 1);
                break;
            case LEFT:
                newHead.setX(newHead.getX() - 1);
                break;
            case RIGHT:
                newHead.setX(newHead.getX() + 1);
                break;
        }

        body.addFirst(newHead);

        if (!hasCollided()) {
            body.removeLast(); 
        } else {
            System.out.println("Game Over - Colisão!");
            System.exit(0);
        }
    }

    public void grow() {
        Ponto tail = body.getLast();
        body.add(new Ponto(tail.getX(), tail.getY()));
    }

    private boolean hasCollided() {
        Ponto head = body.getFirst();
        int headX = head.getX();
        int headY = head.getY();

        // Verifica colisão com as bordas
        if (headX <= 0 || headX >= boardWidth - 1 || headY <= 0 || headY >= boardHeight - 1) {
            return true;
        }

        
        for (int i = 1; i < body.size(); i++) {
            Ponto part = body.get(i);
            if (head.equals(part)) {
                return true;
            }
        }

        return false;
    }

    public Direcao getDirection() {
        return direction;
    }

    public void setDirection(Direcao direction) {
        this.direction = direction;
    }
}
