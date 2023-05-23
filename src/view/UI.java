package view;

import abstraction.objects.OBJ_heart;
import abstraction.objects.OBJ_key;
import abstraction.objects.OBJ_lifePotion;
import abstraction.objects.SuperObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static view.GamePanel.TILE_SIZE;

/**
 * Cette classe gère l'interface utilisateur du jeu.
 */
public class UI {
    private GamePanel gp;
    private Font currentFont;
    private Image keyImage, heart_full, heart_half, heart_blank, lifePotion;
    private boolean messageOn = false;
    private String message = "";
    private String currentDialogue = "";

    /**
     * Initialise un nouvel objet UI.
     *
     * @param gp Le panneau de jeu associé.
     */
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
        SuperObject lifePotion = new OBJ_lifePotion();
        this.lifePotion = lifePotion.getImage();
    }

    /**
     * Affiche un message à l'écran.
     *
     * @param message Le message à afficher.
     */
    public void showMessage(String message) {
        this.message = message;
        this.messageOn = true;
    }

    /**
     * Dessine la barre de vie du joueur.
     *
     * @param gc Le contexte graphique dans lequel dessiner.
     */
    public void drawPlayerLife(GraphicsContext gc) {
        int x = TILE_SIZE / 2;
        int y = TILE_SIZE / 2;
        int i = 0;

        // Cœur vide
        while (i < gp.getPlayer().getMaxLife() / 2) {
            gc.drawImage(heart_blank, x, y);
            i++;
            x += TILE_SIZE;
        }

        x = TILE_SIZE / 2;
        y = TILE_SIZE / 2;
        i = 0;

        // Vie actuelle
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

    /**
     * Dessine l'écran de dialogue.
     */
    public void drawDialogueScreen() {
        int x = gp.TILE_SIZE * 3;
        int y = gp.TILE_SIZE / 2;
        int width = gp.SCREEN_WIDTH - 5 * (gp.TILE_SIZE);
        int height = gp.TILE_SIZE * 4;
        drawSubWindow(x, y, width, height);

        x += gp.TILE_SIZE;
        y += gp.TILE_SIZE;
        Color c = Color.WHITE;
        gp.getGraphicsContext2D().setFill(c);
        gp.getGraphicsContext2D().fillText(currentDialogue, x, y);
    }

    /**
     * Dessine une sous-fenêtre.
     *
     * @param x      La position horizontale de la fenêtre.
     * @param y      La position verticale de la fenêtre.
     * @param width  La largeur de la fenêtre.
     * @param height La hauteur de la fenêtre.
     */
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = Color.BLACK;
        gp.getGraphicsContext2D().setFill(c);
        gp.getGraphicsContext2D().fillRoundRect(x, y, width, height, 35, 35);
    }

    /**
     * Dessine les éléments de l'interface utilisateur.
     *
     * @param gc Le contexte graphique dans lequel dessiner.
     */
    public void draw(GraphicsContext gc) {
        drawPlayerInventory(gc);
        drawPlayerLife(gc);

        if (gp.getGameState() == gp.getPlayState()) {
            // ...
        }
        if (gp.getGameState() == gp.getPauseState()) {
            drawPauseScreen(gc);
        }
        if (gp.getGameState() == gp.getDialogueState()) {
            drawDialogueScreen();
        }
    }

    /**
     * Dessine l'écran de pause.
     *
     * @param gc Le contexte graphique dans lequel dessiner.
     */
    public void drawPauseScreen(GraphicsContext gc) {
        Text text = new Text("PAUSED");
        int x, y = gp.SCREEN_HEIGHT / 2;
        int length = (int) text.getLayoutBounds().getWidth();
        x = gp.SCREEN_WIDTH / 2 - length / 2;
        gc.fillText("PAUSED", x, y);
    }

    /**
     * Dessine l'inventaire du joueur.
     *
     * @param gc Le contexte graphique dans lequel dessiner.
     */
    public void drawPlayerInventory(GraphicsContext gc) {
        gc.setFont(this.currentFont);
        gc.setStroke(Color.WHITE);
        gc.drawImage(keyImage, TILE_SIZE / 2, TILE_SIZE + (TILE_SIZE / 2), TILE_SIZE, TILE_SIZE);
        gc.strokeText("x " + this.gp.getPlayer().getHasKey(), 74, 65 + TILE_SIZE);
        gc.drawImage(lifePotion, TILE_SIZE / 2, 2.5 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        gc.strokeText("x " + this.gp.getPlayer().getHasLifePotion(), 74, 110 + TILE_SIZE);
    }

    /**
     * Obtient le dialogue actuel.
     *
     * @return Le dialogue actuel.
     */
    public String getCurrentDialogue() {
        return currentDialogue;
    }

    /**
     * Définit le dialogue actuel.
     *
     * @param currentDialogue Le dialogue actuel.
     */
    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
}
