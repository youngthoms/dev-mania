package abstraction;

import controller.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import view.GamePanel;

import java.io.File;

import static view.GamePanel.*;


public class Player extends Entity {
    private int screenX, screenY;
    public static final int SPRITE_COUNTER_NUMBER = 9;
    private Image up1, up2, down1, down2, left1, left2, right1, right2;
    public static final String RES_URL = "file:res" + File.separator + "player";
    KeyHandler keyH;

    public Player(GamePanel g, KeyHandler keyH) {
        super(g);
        this.keyH = keyH;

        this.setScreenX(SCREEN_WIDTH / 2 - (TILE_SIZE / 2));
        this.setScreenY(SCREEN_HEIGHT / 2 - (TILE_SIZE / 2));

        this.setHitbox(new Rectangle(8, 16, 32, 32));
        this.setDefaultValues();
        this.getPlayerImage();
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

    public void setDefaultValues() {
        this.setWorldX(TILE_SIZE * 23);
        this.setWorldY(TILE_SIZE * 20);
        this.setSpeed(5);
        this.setDirection("down");
    }

    public static String getURL(String ImageName) {
        return RES_URL + File.separator + ImageName;
    }

    public void update() {
        String direction = this.getDirection();
        if (keyH.up || keyH.down || keyH.left || keyH.right) {
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

            int objectIndex=this.getGamePanel().getColisionObject().checkObject(this,true);

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

    public void draw(GraphicsContext gc) {
        Image image = null;

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

        gc.drawImage(image, this.getScreenX(), this.getScreenY());
    }

    public void getPlayerImage() {
        up1 = new Image(getURL("boy_up_1.png"), TILE_SIZE, TILE_SIZE, false, false);
        up2 = new Image(getURL("boy_up_2.png"), TILE_SIZE, TILE_SIZE, false, false);
        down1 = new Image(getURL("boy_down_1.png"), TILE_SIZE, TILE_SIZE, false, false);
        down2 = new Image(getURL("boy_down_2.png"), TILE_SIZE, TILE_SIZE, false, false);
        left1 = new Image(getURL("boy_left_1.png"), TILE_SIZE, TILE_SIZE, false, false);
        left2 = new Image(getURL("boy_left_2.png"), TILE_SIZE, TILE_SIZE, false, false);
        right1 = new Image(getURL("boy_right_1.png"), TILE_SIZE, TILE_SIZE, false, false);
        right2 = new Image(getURL("boy_right_2.png"), TILE_SIZE, TILE_SIZE, false, false);
    }
}
