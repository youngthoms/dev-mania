package abstraction;

import controller.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.GamePanel;


public class Player extends Entity {
    public static final int SPRITE_COUNTER_NUMBER = 16;
    private Image up1, up2, down1, down2, left1, left2, right1, right2;
    private String direction;
    KeyHandler keyH;

    public Player(GamePanel g, KeyHandler keyH) {
        super(g);
        this.keyH = keyH;
        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void setDefaultValues() {
        this.setX(this.getGamePanel().SCREEN_WIDTH / 2);
        this.setY(this.getGamePanel().SCREEN_HEIGHT / 2);
        this.setSpeed(4);
        direction = "down";
    }

    public void update() {
        if (keyH.up || keyH.down || keyH.left || keyH.right) {
            if (keyH.up) {
                direction = "up";
                this.moveUp();
            } else if (keyH.down) {
                direction = "down";
                this.moveDown();
            } else if (keyH.left) {
                direction = "left";
                this.moveLeft();
            } else if (keyH.right) {
                direction = "right";
                this.moveRight();
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

        switch (direction) {
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

        gc.drawImage(image, this.getX(), this.getY());
    }

    public void getPlayerImage() {
        int imageSize = this.getGamePanel().TILE_SIZE;
        up1 = new Image("/boy_up_1.png", imageSize, imageSize, false, false);
        up2 = new Image("/boy_up_2.png", imageSize, imageSize, false, false);
        down1 = new Image("/boy_down_1.png", imageSize, imageSize, false, false);
        down2 = new Image("/boy_down_2.png", imageSize, imageSize, false, false);
        left1 = new Image("/boy_left_1.png", imageSize, imageSize, false, false);
        left2 = new Image("/boy_left_2.png", imageSize, imageSize, false, false);
        right1 = new Image("/boy_right_1.png", imageSize, imageSize, false, false);
        right2 = new Image("/boy_right_2.png", imageSize, imageSize, false, false);
    }
}
