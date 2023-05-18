package view;

import abstraction.OBJ_heart;
import abstraction.OBJ_key;
import abstraction.SuperObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import static view.GamePanel.TILE_SIZE;


public class UI {
    private GamePanel gp;
    private Font currentFont;
    private Image keyImage, heart_full, heart_half, heart_blank;
    private boolean messageOn = false;
    private String message = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        this.currentFont = Font.font("Arial", 40);
        OBJ_key key = new OBJ_key();
        keyImage = key.getImage();

        // Hud object
        SuperObject heart = new OBJ_heart();
        heart_full = heart.getImage();
        heart_half = heart.getImage1();
        heart_blank = heart.getImage2();
    }

    public void showMessage(String message) {
        this.message = message;
        this.messageOn = true;
    }

    public void drawPlayerLife(GraphicsContext gc) {
        int x = TILE_SIZE / 2;
        int y = TILE_SIZE / 2;
        int i = 0;

        // Blank heart
        while (i < gp.getPlayer().getMaxLife() / 2) {
            gc.drawImage(heart_blank, x, y);
            i++;
            x += TILE_SIZE;
        }

        x = TILE_SIZE / 2;
        y = TILE_SIZE / 2;
        i = 0;

        // Current life
        while (i < gp.getPlayer().getLife()) {
            gc.drawImage(heart_half, x, y);
            i++;
            if (i < gp.getPlayer().getLife()) {
                gc.drawImage(heart_full, x, y);
            }
            i++;
            x += TILE_SIZE;
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setFont(this.currentFont);
        gc.setStroke(Color.WHITE);
        gc.drawImage(keyImage, TILE_SIZE / 2, TILE_SIZE + 24, TILE_SIZE, TILE_SIZE);
        gc.strokeText("x " + this.gp.getPlayer().getHasKey(), 74, 65 + TILE_SIZE);

        drawPlayerLife(gc);
    }
}
