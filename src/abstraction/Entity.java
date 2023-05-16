package abstraction;

import javafx.scene.canvas.GraphicsContext;
import view.GamePanel;

public abstract class Entity {
    private int worldX, worldY, speed, life, strength;
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

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
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

    public void moveUp() {
        this.setWorldY(this.getWorldY() - this.getSpeed());
    }

    public void moveDown() {
        this.setWorldY(this.getWorldY() + this.getSpeed());
    }

    public void moveLeft() {
        this.setWorldX(this.getWorldX() - this.getSpeed());
    }

    public void moveRight() {
        this.setWorldX(this.getWorldX() + this.getSpeed());
    }

    public void update() {
    }

    public void draw(GraphicsContext gc) {
    }
}
