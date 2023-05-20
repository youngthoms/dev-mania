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
    private Rectangle attackHitbox;
    private String direction;
    private boolean collisionOn = false;
    private boolean invincible = false;
    private boolean attacking = false;
    private int invincibleCounter = 0;
    private int screenX, screenY;
    public static final int SPRITE_COUNTER_NUMBER = 9;
    private Image up1, up2, down1, down2, left1, left2, right1, right2;
    private Image attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String[] dialogues;

    public Entity(GamePanel g) {
        this.gp = g;
        this.setScreenX(SCREEN_WIDTH / 2 - (TILE_SIZE / 2));
        this.setScreenY(SCREEN_HEIGHT / 2 - (TILE_SIZE / 2));

        dialogues = new String[15];
    }

    public void setDefaultValues(int worldX, int worldY, int speed, String direction) {
        this.setWorldX(worldX);
        this.setWorldY(worldY);
        this.setSpeed(speed);
        this.setDirection(direction);
    }

    public void setAttackHitbox(Rectangle attackHitbox) {
        this.attackHitbox = attackHitbox;
    }

    public Rectangle getAttackHitbox() {
        return attackHitbox;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public int getInvincibleCounter() {
        return invincibleCounter;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincibleCounter(int invincibleCounter) {
        this.invincibleCounter = invincibleCounter;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
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

    public String[] getDialogues() {
        return dialogues;
    }

    public void setDialogues(String[] dialogues) {
        this.dialogues = dialogues;
    }

    public void speak() {

    }

    public void setAction() {
    }

    public void update() {
    }

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
        //System.out.printf("Up  <x:%d ; y:%d>\n", this.getWorldX(), this.getWorldY());
    }

    public void moveDown() {
        this.setWorldY(this.getWorldY() + this.getSpeed());
        //System.out.printf("Down  <x:%d ; y:%d>\n", this.getWorldX(), this.getWorldY());
    }

    public void moveLeft() {
        this.setWorldX(this.getWorldX() - this.getSpeed());
        //System.out.printf("Left  <x:%d ; y:%d>\n", this.getWorldX(), this.getWorldY());
    }

    public void moveRight() {
        this.setWorldX(this.getWorldX() + this.getSpeed());
        //System.out.printf("Right  <x:%d ; y:%d>\n", this.getWorldX(), this.getWorldY());
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

        if (worldX + TILE_SIZE > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - TILE_SIZE < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + TILE_SIZE > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - TILE_SIZE < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {


            switch (this.getDirection()) {
                case "up":
                    if (this.isAttacking()) {
                        if (this.getSpriteNumber() == 1) {
                            image = attackUp1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = attackUp2;
                        }
                    } else {
                        if (this.getSpriteNumber() == 1) {
                            image = up1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = up2;
                        }
                    }
                    break;
                case "down":
                    if (this.isAttacking()) {
                        if (this.getSpriteNumber() == 1) {
                            image = attackDown1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = attackDown2;
                        }
                    } else {
                        if (this.getSpriteNumber() == 1) {
                            image = down1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = down2;
                        }
                    }
                    break;
                case "left":
                    if (this.isAttacking()) {
                        if (this.getSpriteNumber() == 1) {
                            image = attackLeft1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = attackLeft2;
                        }
                    } else {
                        if (this.getSpriteNumber() == 1) {
                            image = left1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = left2;
                        }
                    }
                    break;
                case "right":
                    if (this.isAttacking()) {
                        if (this.getSpriteNumber() == 1) {
                            image = attackRight1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = attackRight2;
                        }
                    } else {
                        if (this.getSpriteNumber() == 1) {
                            image = right1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = right2;
                        }
                    }
                    break;
            }
        }
        if (this.isInvincible()) {
            gc.strokeText("Hit", screenX, screenY);
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

    public Image getUp1() {
        return up1;
    }

    public Image getUp2() {
        return up2;
    }

    public Image getDown1() {
        return down1;
    }

    public Image getDown2() {
        return down2;
    }

    public Image getRight1() {
        return right1;
    }

    public Image getRight2() {
        return right2;
    }

    public Image getLeft1() {
        return left1;
    }

    public Image getLeft2() {
        return left2;
    }

    public void setAttackRight1(Image attackRight1) {
        this.attackRight1 = attackRight1;
    }

    public void setAttackLeft2(Image attackLeft2) {
        this.attackLeft2 = attackLeft2;
    }

    public void setAttackLeft1(Image attackLeft1) {
        this.attackLeft1 = attackLeft1;
    }

    public void setAttackDown2(Image attackDown2) {
        this.attackDown2 = attackDown2;
    }

    public void setAttackDown1(Image attackDown1) {
        this.attackDown1 = attackDown1;
    }

    public void setAttackUp2(Image attackUp2) {
        this.attackUp2 = attackUp2;
    }

    public void setAttackUp1(Image attackUp1) {
        this.attackUp1 = attackUp1;
    }

    public void setAttackRight2(Image attackRight2) {
        this.attackRight2 = attackRight2;
    }

    public Image getAttackUp1() {
        return attackUp1;
    }

    public Image getAttackUp2() {
        return attackUp2;
    }

    public Image getAttackDown1() {
        return attackDown1;
    }

    public Image getAttackDown2() {
        return attackDown2;
    }

    public Image getAttackLeft1() {
        return attackLeft1;
    }

    public Image getAttackLeft2() {
        return attackLeft2;
    }

    public Image getAttackRight1() {
        return attackRight1;
    }

    public Image getAttackRight2() {
        return attackRight2;
    }
}
