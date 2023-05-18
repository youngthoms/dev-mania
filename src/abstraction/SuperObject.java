package abstraction;

import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.GamePanel;

public class SuperObject {

    private Image image, image1, image2;
    private String name;
    private boolean colision = false;
    private int worldX, worldY;
    private Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    private int hitboxDefaultX = 0;
    private int hitboxDefaultY = 0;

    public void draw(GraphicsContext gc, GamePanel gp) {
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (worldX + gp.TILE_SIZE > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.TILE_SIZE < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.TILE_SIZE > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.TILE_SIZE < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
            gc.drawImage(image, screenX, screenY);
        }

    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public String getName() {
        return name;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getWorldY() {
        return this.worldY;
    }

    public int getWorldX() {
        return this.worldX;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    public Image getImage() {
        return this.image;
    }

    public Image getImage1() {
        return image1;
    }

    public Image getImage2() {
        return image2;
    }

    public int getHitboxDefaultY() {
        return hitboxDefaultY;
    }

    public void setHitboxDefaultY(int hitboxDefaultY) {
        this.hitboxDefaultY = hitboxDefaultY;
    }

    public int getHitboxDefaultX() {
        return hitboxDefaultX;
    }

    public void setHitboxDefaultX(int hitboxDefaultX) {
        this.hitboxDefaultX = hitboxDefaultX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public boolean getColision(){
        return this.colision;
    }

    public void setColision(boolean colision) {
        this.colision = colision;
    }
}
