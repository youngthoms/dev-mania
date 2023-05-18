package abstraction;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import view.GamePanel;

import static view.GamePanel.*;
import static view.GamePanel.TILE_SIZE;

public abstract class Entity {
    private int worldX, worldY, speed, life, maxLife, strength;
    private GamePanel gp;
    private int spriteCounter = 0;
    private int spriteNumber = 1;
    private int solidHitboxDefaultX, solidHitboxDefaultY;
    private Rectangle hitbox;
    private String direction;
    private boolean collisionOn = false;
    private int screenX, screenY;
    public static final int SPRITE_COUNTER_NUMBER = 9;
    private Image up1, up2, down1, down2, left1, left2, right1, right2;

    public Entity(GamePanel g) {
        this.gp = g;
        this.setScreenX(SCREEN_WIDTH / 2 - (TILE_SIZE / 2));
        this.setScreenY(SCREEN_HEIGHT / 2 - (TILE_SIZE / 2));

        this.setHitbox(new Rectangle(8, 16, 32, 32));
        this.setSolidHitboxDefaultX((int) this.getHitbox().getX());
        this.setSolidHitboxDefaultY((int) this.getHitbox().getY());
        this.setDefaultValues();
    }

    public int getSolidHitboxDefaultY() {
        return solidHitboxDefaultY;
    }

    public int getSolidHitboxDefaultX() {
        return solidHitboxDefaultX;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setAction(){};

    public void update(){
        setAction();
        collisionOn = false;
        getGamePanel().getCollisionChecker().checkTile(this);
        getGamePanel().getCollisionChecker().checkPlayer(this);
        getGamePanel().getColisionObject().checkObject(this,false);

        if (!this.getCollisionOn()) {
            switch (direction) {
                case "up":
                    this.moveUp();
                    break;
                case "down":
                    this.moveDown();
                    break;
                case "left":
                    this.moveLeft();
                    break;
                case "right":
                    this.moveRight();
                    break;
            }
        }

        this.setSpriteCounter(this.getSpriteCounter() + 1);
        if (this.getSpriteCounter() > SPRITE_COUNTER_NUMBER) {
            if (this.getSpriteNumber() == 1) {
                this.setSpriteNumber(2);
            } else if (this.getSpriteNumber() == 2) {
                this.setSpriteNumber(1);
            }
            this.setSpriteCounter(0);
        }
    };

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



    public void setDefaultValues() {
        this.setWorldX(TILE_SIZE * 23);
        this.setWorldY(TILE_SIZE * 20);
        this.setSpeed(5);
        this.setDirection("down");
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

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void moveUp() {
        this.setWorldY(this.getWorldY() - this.getSpeed());
        System.out.printf("Up  <x:%d ; y:%d>\n", this.getWorldX(), this.getWorldY());
    }

    public void moveDown() {
        this.setWorldY(this.getWorldY() + this.getSpeed());
        System.out.printf("Down  <x:%d ; y:%d>\n", this.getWorldX(), this.getWorldY());
    }

    public void moveLeft() {
        this.setWorldX(this.getWorldX() - this.getSpeed());
        System.out.printf("Left  <x:%d ; y:%d>\n", this.getWorldX(), this.getWorldY());
    }

    public void moveRight() {
        this.setWorldX(this.getWorldX() + this.getSpeed());
        System.out.printf("Right  <x:%d ; y:%d>\n", this.getWorldX(), this.getWorldY());
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public void draw(GraphicsContext gc) {
        Image image = null;
        int screenX = getWorldX() - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = getWorldY() - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (worldX + gp.TILE_SIZE > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.TILE_SIZE < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.TILE_SIZE > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.TILE_SIZE < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {


            switch (this.getDirection()) {
                case "up":
                    if (this.getSpriteNumber() == 1) {
                        image = up1;
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (this.getSpriteNumber() == 1) {
                        image = down1;
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (this.getSpriteNumber() == 1) {
                        image = left1;
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (this.getSpriteNumber() == 1) {
                        image = right1;
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = right2;
                    }
                    break;
            }
        }

        gc.drawImage(image, screenX, screenY, getGamePanel().TILE_SIZE, getGamePanel().TILE_SIZE);

    }


    public void setRight2(Image right2) {
        this.right2 = right2;
    }

    public void setRight1(Image right1) {
        this.right1 = right1;
    }

    public void setLeft2(Image left2) {
        this.left2 = left2;
    }

    public void setLeft1(Image left1) {
        this.left1 = left1;
    }

    public void setDown2(Image down2) {
        this.down2 = down2;
    }

    public void setDown1(Image down1) {
        this.down1 = down1;
    }

    public void setUp2(Image up2) {
        this.up2 = up2;
    }

    public void setUp1(Image up1) {
        this.up1 = up1;
    }
}
