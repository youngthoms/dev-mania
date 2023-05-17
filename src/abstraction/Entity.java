package abstraction;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import view.GamePanel;

public abstract class Entity {
    private int worldX, worldY, speed, life, strength;
    private GamePanel gp;
    private int spriteCounter = 0;
    private int spriteNumber = 1;
    private int solidHitboxDefaultX, solidHitboxDefaultY;
    private Rectangle hitbox;
    private String direction;
    private boolean collisionOn = false;

    public int getsolidHitboxDefaultY() {
        return solidHitboxDefaultY;
    }

    public int getsolidHitboxDefaultX() {
        return solidHitboxDefaultX;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public int getSpriteNumber() {
        return spriteNumber;
    }

    public void getPlayerImage() {
    }

    public Rectangle getHitbox() {
        return hitbox;
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

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public String getDirection() {
        return direction;
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

    public boolean getCollisionOn() {
        return collisionOn;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public void setSolidHitboxDefaultX(int solidHitboxDefaultX) {
        this.solidHitboxDefaultX = solidHitboxDefaultX;
    }

    public void setSolidHitboxDefaultY(int solidHitboxDefaultY) {
        this.solidHitboxDefaultY = solidHitboxDefaultY;
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
