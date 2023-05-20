package abstraction;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import view.GamePanel;

import java.util.Random;

public class MonsterGreenSlime extends Entity {
    private String name;
    private int actionLockCounter = 0;

    public MonsterGreenSlime(GamePanel gp) {
        super(gp);

        this.name = "Green Slime";
        this.setSpeed(1);
        this.setMaxLife(4);
        this.setLife(this.getMaxLife());
        this.setDirection("down");

        this.setHitbox(new Rectangle(3, 18, 42, 30));
        this.setSolidHitboxDefaultX((int) this.getHitbox().getX());
        this.setSolidHitboxDefaultY((int) this.getHitbox().getY());
        this.getImage();
    }

    public void getImage() {
        setUp1(new Image("file:res/monster/greenslime_down_1.png"));
        setUp2(new Image("file:res/monster/greenslime_down_2.png"));
        setDown1(new Image("file:res/monster/greenslime_down_1.png"));
        setDown2(new Image("file:res/monster/greenslime_down_2.png"));
        setLeft1(new Image("file:res/monster/greenslime_down_1.png"));
        setLeft2(new Image("file:res/monster/greenslime_down_2.png"));
        setRight1(new Image("file:res/monster/greenslime_down_1.png"));
        setRight2(new Image("file:res/monster/greenslime_down_2.png"));
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

    public int getActionLockCounter() {
        return actionLockCounter;
    }

    public void setActionLockCounter(int actionLockCounter) {
        this.actionLockCounter = actionLockCounter;
    }

    public void update() {
        setAction();
        setCollisionOn(false);
        getGamePanel().getCollisionChecker().checkTile(this);
        getGamePanel().getColisionObject().checkObject(this, false);
        boolean contactPlayer = getGamePanel().getCollisionChecker().checkPlayer(this);

        if (contactPlayer) {
            Player player = this.getGamePanel().getPlayer();
            if (!player.isInvincible()) {
                player.setLife(player.getLife() - 1);
                player.setInvincible(true);
            }
        }

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

        this.setSpriteCounter(this.getSpriteCounter() + 1);
        if (this.getSpriteCounter() > SPRITE_COUNTER_NUMBER) {
            if (this.getSpriteNumber() == 1) {
                this.setSpriteNumber(2);
            } else if (this.getSpriteNumber() == 2) {
                this.setSpriteNumber(1);
            }
            this.setSpriteCounter(0);
        }
    }
}
