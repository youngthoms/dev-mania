package abstraction;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import view.GamePanel;

import java.io.File;
import java.util.Random;

import view.GamePanel;

import static view.GamePanel.TILE_SIZE;

public class NPC_OldMan extends Entity {

    public static final String RES_URL = "res" + File.separator + "npc";
    private int actionLockCounter = 0;

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        this.setScreenX(getGamePanel().SCREEN_WIDTH / 2 - (getGamePanel().TILE_SIZE / 2));
        this.setScreenY(getGamePanel().SCREEN_HEIGHT / 2 - (getGamePanel().TILE_SIZE / 2));
        setDirection("down");
        setSpeed(1);
        getOldmanImage();
        // Hitbox
        this.setHitbox(new Rectangle(8, 16, 32, 32));
        this.setSolidHitboxDefaultX((int) this.getHitbox().getX());
        this.setSolidHitboxDefaultY((int) this.getHitbox().getY());

        this.setDefaultValues(TILE_SIZE * 23, TILE_SIZE * 20, 1, "down");
        setDialogue();
    }

    public static String getURL(String ImageName) {
        return RES_URL + File.separator + ImageName;
    }

    public void getOldmanImage() {
        setUp1(new Image(getURL("oldman_up_1.png"), getGamePanel().TILE_SIZE, getGamePanel().TILE_SIZE, false, false));
        setUp2(new Image(getURL("oldman_up_2.png"), getGamePanel().TILE_SIZE, getGamePanel().TILE_SIZE, false, false));
        setDown1(new Image(getURL("oldman_down_1.png"), getGamePanel().TILE_SIZE, getGamePanel().TILE_SIZE, false, false));
        setDown2(new Image(getURL("oldman_down_2.png"), getGamePanel().TILE_SIZE, getGamePanel().TILE_SIZE, false, false));
        setLeft1(new Image(getURL("oldman_left_1.png"), getGamePanel().TILE_SIZE, getGamePanel().TILE_SIZE, false, false));
        setLeft2(new Image(getURL("oldman_left_2.png"), getGamePanel().TILE_SIZE, getGamePanel().TILE_SIZE, false, false));
        setRight1(new Image(getURL("oldman_right_1.png"), getGamePanel().TILE_SIZE, getGamePanel().TILE_SIZE, false, false));
        setRight2(new Image(getURL("oldman_right_2.png"), getGamePanel().TILE_SIZE, getGamePanel().TILE_SIZE, false, false));
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

    public void update() {
        setAction();
        setCollisionOn(false);
        getGamePanel().getCollisionChecker().checkTile(this);
        getGamePanel().getCollisionChecker().checkPlayer(this);
        getGamePanel().getColisionObject().checkObject(this, false);

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

    public int getActionLockCounter() {
        return actionLockCounter;
    }

    public void setActionLockCounter(int actionLockCounter) {
        this.actionLockCounter = actionLockCounter;
    }

    public void setDialogue() {
        dialogues[0] = "May this black hole \ngo somewhere ...";
    }

    public void speak() {
        getGamePanel().getUi().setCurrentDialogue(getDialogues()[0]);
    }
}
