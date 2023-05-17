package view;

import abstraction.OBJ_key;
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
    private Image keyImage;
    private boolean messageOn = false;
    private String message = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        this.currentFont = Font.font("Arial", 40);
        OBJ_key key = new OBJ_key();
        keyImage = key.getImage();
    }

    public void showMessage(String message) {
        this.message = message;
        this.messageOn = true;
    }
    public void draw(GraphicsContext gc) {
        gc.setFont(this.currentFont);
        gc.setStroke(Color.WHITE);
        gc.drawImage(keyImage, TILE_SIZE / 2, TILE_SIZE / 2, TILE_SIZE, TILE_SIZE);
        gc.strokeText("x " + this.gp.getPlayer().getHasKey(), 74, 65);
    }
}
