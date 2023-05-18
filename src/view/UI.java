package view;

import abstraction.OBJ_heart;
import abstraction.OBJ_key;
import abstraction.SuperObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static view.GamePanel.TILE_SIZE;


public class UI {
    private GamePanel gp;
    private Font currentFont;
    private Image keyImage, heart_full, heart_half, heart_blank;
    private boolean messageOn = false;
    private String message = "";
    private String currentDialogue="";
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

    public void drawDialogueScreen(){
        int x = gp.TILE_SIZE*3;
        int y = gp.TILE_SIZE/2;
        int width = gp.SCREEN_WIDTH-5*(gp.TILE_SIZE),height=gp.TILE_SIZE*4;
        drawSubWindow(x,y,width,height);

        x += gp.TILE_SIZE;
        y += gp.TILE_SIZE;
        Color c = Color.WHITE;
        gp.getGraphicsContext2D().setFill(c);
        gp.getGraphicsContext2D().fillText(currentDialogue,x,y);
    }

    public void drawSubWindow(int x,int y , int width,int height){
        Color c = Color.BLACK;
        gp.getGraphicsContext2D().setFill(c);
        gp.getGraphicsContext2D().fillRoundRect(x,y,width,height,35,35);
    }

    public void draw(GraphicsContext gc) {
        gc.setFont(this.currentFont);
        gc.setStroke(Color.WHITE);
        gc.drawImage(keyImage, TILE_SIZE / 2, TILE_SIZE / 2, TILE_SIZE, TILE_SIZE);
        gc.strokeText("x " + this.gp.getPlayer().getHasKey(), 74, 65);
        drawPlayerLife(gc);

        if (gp.getGameState() == gp.getPlayState()){
        }
        if (gp.getGameState() == gp.getPauseState()){
            drawPauseScreen(gc);
        }
        if (gp.getGameState() == gp.getDialogueState()){
            drawDialogueScreen();
        }
    }

    public void drawPauseScreen(GraphicsContext gc){
        Text text = new Text("PAUSED");
        int x,y = gp.SCREEN_HEIGHT/2;
        int lenght = (int)text.getLayoutBounds().getWidth();
        x = gp.SCREEN_WIDTH/2 - lenght/2;
        gc.fillText("PAUSED",x,y);

    }

    public String getCurrentDialogue() {
        return currentDialogue;
    }

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
}
