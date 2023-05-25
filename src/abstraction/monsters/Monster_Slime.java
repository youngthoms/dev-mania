package abstraction.monsters;

import abstraction.Entity;
import abstraction.Player;
import javafx.scene.shape.Rectangle;
import view.GamePanel;

import java.util.Random;

import static view.GamePanel.FPS;

public abstract class Monster_Slime extends Entity implements Monster {
    private String name;
    private int actionLockCounter = 0;

    /**
     * Constructeur de l'entité.
     *
     * @param g le panneau de jeu auquel l'entité appartient
     */
    public Monster_Slime(GamePanel g) {
        super(g);
        this.setSpeed(1);
        this.setMaxLife(4);
        this.setLife(this.getMaxLife());
        this.setDirection("down");

        this.setHitbox(new Rectangle(3, 18, 42, 30));
        this.setSolidHitboxDefaultX((int) this.getHitbox().getX());
        this.setSolidHitboxDefaultY((int) this.getHitbox().getY());
        this.getImage();
    }

    public int getActionLockCounter() {
        return actionLockCounter;
    }

    public void setActionLockCounter(int actionLockCounter) {
        this.actionLockCounter = actionLockCounter;
    }

    public void setAction() {
        setActionLockCounter(getActionLockCounter() + 1);
        if (getActionLockCounter() == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                setDirection("up");
            }
            if (i > 25 && i <= 50) {
                setDirection("down");
            }
            if (i > 50 && i <= 75) {
                setDirection("left");
            }
            if (i > 75) {
                setDirection("right");
            }

            setActionLockCounter(0);
        }
    }

    public void handleCollision(boolean contactPlayer) {
        if (contactPlayer) {
            Player player = this.getGamePanel().getPlayer();
            if (!player.getHasInvincibility()) {
                if (!player.isInvincible()) {
                    player.setLife(player.getLife() - 1);
                    player.setInvincible(true);
                }
            }
        }
    }

    public void handleSpriteCounter(int spriteCounter) {
        this.setSpriteCounter(spriteCounter + 1);
        if (spriteCounter > SPRITE_COUNTER_NUMBER) {
            if (this.getSpriteNumber() == 1) {
                this.setSpriteNumber(2);
            } else if (this.getSpriteNumber() == 2) {
                this.setSpriteNumber(1);
            }
            this.setSpriteCounter(0);
        }
    }

    public void handleInvicibilityState(boolean isIvincible) {
        if (isIvincible) {
            this.setInvincibleCounter(this.getInvincibleCounter() + 1);
            if (this.getInvincibleCounter() > FPS / 2) {
                this.setInvincible(false);
                this.setInvincibleCounter(0);
            }
        }
    }

    public void update() {
        setAction();
        setCollisionOn(false);
        getGamePanel().getCollisionChecker().checkTile(this);
        getGamePanel().getColisionObject().checkObject(this, false);
        boolean contactPlayer = getGamePanel().getCollisionChecker().checkPlayer(this);
        handleCollision(contactPlayer);

        if (!this.getCollisionOn()) {
            switch (getDirection()) {
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
        handleSpriteCounter(this.getSpriteCounter());
        handleInvicibilityState(this.isInvincible());
    }
}
