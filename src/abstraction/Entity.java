package abstraction;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import view.GamePanel;

public abstract class Entity {
    private int x, y, speed, life, strength;
    private GamePanel gp;
    private int spriteCounter = 0;
    private int spriteNumber = 1;

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public int getSpriteNumber() {
        return spriteNumber;
    }

    public void getPlayerImage() {
    }

    public Entity(GamePanel g) {
        this.gp = g;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public void setSpriteNumber(int spriteNumber) {
        this.spriteNumber = spriteNumber;
    }

    public GamePanel getGamePanel() {
        return gp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveUp() {
        this.setY(this.getY() - this.getSpeed());
    }

    public void moveDown() {
        this.setY(this.getY() + this.getSpeed());
    }

    public void moveLeft() {
        this.setX(this.getX() - this.getSpeed());
    }

    public void moveRight() {
        this.setX(this.getX() + this.getSpeed());
    }

    public void update() {
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(this.getX(), this.getY(), this.getGamePanel().TILE_SIZE, this.getGamePanel().TILE_SIZE);
    }
}
