package abstraction;

import controller.KeyHandler;
import javafx.scene.image.Image;
import view.GamePanel;

import java.io.File;

import static view.GamePanel.*;


public class Player extends Entity {
    private int hasKey = 0;
    public static final String RES_URL = "file:res" + File.separator + "player";
    KeyHandler keyH;

    public Player(GamePanel g, KeyHandler keyH) {
        super(g);
        this.keyH = keyH;
        this.getPlayerImage();

        // Player status
        this.setMaxLife(6); // 6 Life = 3 hearts
        this.setLife(this.getMaxLife());
    }


    public static String getURL(String ImageName) {
        return RES_URL + File.separator + ImageName;
    }

    public void update() {
        String direction = this.getDirection();
        if (keyH.up || keyH.down || keyH.left || keyH.right || keyH.interract) {
            if (keyH.up) {
                this.setDirection("up");
            } else if (keyH.down) {
                this.setDirection("down");
            } else if (keyH.left) {
                this.setDirection("left");
            } else if (keyH.right) {
                this.setDirection("right");
            }

            // Check tile collision
            this.setCollisionOn(false);
            this.getGamePanel().getCollisionChecker().checkTile(this);

            // Check object collision
            int objectIndex = this.getGamePanel().getColisionObject().checkObject(this, true);
            pickUpObject(objectIndex);

            // Check event
            this.getGamePanel().getEventHandler().checkEvent();


            int npcIndex = getGamePanel().getCollisionChecker().checkEntity(this, getGamePanel().getNpc());
            interactNPC(npcIndex);
            // If collision is false, player can move
            if (this.getCollisionOn() == false) {
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
        }
    }

    public void interactNPC(int index) {
        if (index != 999) {
            if (getGamePanel().getKeyH().interract) {
                getGamePanel().setGameState(getGamePanel().getDialogueState());
                getGamePanel().getNpc()[index].speak();
            }

        }
        getGamePanel().getKeyH().interract = false;
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = getGamePanel().object[index].getName();
            switch (objectName) {
                case "Key":
                    hasKey++;
                    getGamePanel().object[index] = null;
                    break;
                case "Door":
                    if (getHasKey() > 0) {
                        hasKey--;
                        getGamePanel().object[index] = null;
                    }
                    break;
                case "Boots" :
                    this.setSpeed(8);
                    getGamePanel().object[index] = null;

                case "LifePotion":
                    if (this.getLife()<getMaxLife()-1){
                        setLife(getLife()+1);
                        getGamePanel().object[index] = null;
                    }
                    else if (this.getLife()<getMaxLife()){
                        setLife(getMaxLife());
                        getGamePanel().object[index] = null;
                    }
                case "Teleporteur":
                    this.setWorldX(33*getGamePanel().TILE_SIZE);
                    this.setWorldY(7*getGamePanel().TILE_SIZE);
            }

        }

    }


    public void getPlayerImage() {
        setUp1(new Image(getURL("boy_up_1_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setUp2(new Image(getURL("boy_up_2_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setDown2(new Image(getURL("boy_down_2_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setDown1(new Image(getURL("boy_down_1_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setLeft1(new Image(getURL("boy_left_1_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setLeft2(new Image(getURL("boy_left_2_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setRight1(new Image(getURL("boy_right_1_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setRight2(new Image(getURL("boy_right_2_2.png"), TILE_SIZE, TILE_SIZE, false, false));
    }

    public int getHasKey() {
        return hasKey;
    }

    public void setHasKey(int hasKey) {
        this.hasKey = hasKey;
    }
}
