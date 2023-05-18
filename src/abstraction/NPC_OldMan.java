package abstraction;

import javafx.scene.image.Image;
import view.GamePanel;

import java.io.File;
import java.util.Random;

import view.GamePanel;

public class NPC_OldMan extends Entity {

    public static final String RES_URL = "file:res" + File.separator + "npc";
    private int actionLockCounter = 0;
    public NPC_OldMan(GamePanel gp) {
        super(gp);
        this.setScreenX(getGamePanel().SCREEN_WIDTH / 2 - (getGamePanel().TILE_SIZE / 2));
        this.setScreenY(getGamePanel().SCREEN_HEIGHT / 2 - (getGamePanel().TILE_SIZE / 2));
        setDirection("down");
        setSpeed(1);
        getOldmanImage();
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

    public void setAction(){
        setActionLockCounter(getActionLockCounter()+1);
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
}
