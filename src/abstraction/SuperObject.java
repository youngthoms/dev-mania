package abstraction;

import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.GamePanel;

/**
 * SuperObject représente un objet avec des propriétés graphiques et de collision.
 */
public class SuperObject {

    private Image image, image1, image2; // Images associées à l'objet
    private String name; // Nom de l'objet
    private boolean collision = false; // Indique si l'objet est en collision avec un autre
    private int worldX, worldY; // Position de l'objet dans le monde
    private Rectangle hitbox = new Rectangle(0, 0, 48, 48); // Boîte de collision par défaut
    private int hitboxDefaultX = 0; // Position X par défaut de la boîte de collision
    private int hitboxDefaultY = 0; // Position Y par défaut de la boîte de collision

    /**
     * Dessine l'objet sur le contexte graphique spécifié.
     *
     * @param gc Le contexte graphique sur lequel dessiner l'objet.
     * @param gp Le panneau de jeu contenant les informations du joueur.
     */
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

    /**
     * Définit la boîte de collision de l'objet.
     *
     * @param hitbox La boîte de collision à définir.
     */
    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * Définit la position X de l'objet dans le monde.
     *
     * @param worldX La position X à définir.
     */
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    /**
     * Obtient le nom de l'objet.
     *
     * @return Le nom de l'objet.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtient la boîte de collision de l'objet.
     *
     * @return La boîte de collision de l'objet.
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Obtient la position Y de l'objet dans le monde.
     *
     * @return La position Y de l'objet.
     */
    public int getWorldY() {
        return worldY;
    }

    /**
     * Obtient la position X de l'objet dans le monde.
     *
     * @return La position X de l'objet.
     */
    public int getWorldX() {
        return worldX;
    }

    /**
     * Définit le nom de l'objet.
     *
     * @param name Le nom à définir.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Définit l'image principale de l'objet.
     *
     * @param image L'image à définir.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Définit une image supplémentaire pour l'objet.
     *
     * @param image1 L'image supplémentaire à définir.
     */
    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    /**
     * Définit une autre image supplémentaire pour l'objet.
     *
     * @param image2 L'autre image supplémentaire à définir.
     */
    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    /**
     * Obtient l'image principale de l'objet.
     *
     * @return L'image principale de l'objet.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Obtient une image supplémentaire de l'objet.
     *
     * @return L'image supplémentaire de l'objet.
     */
    public Image getImage1() {
        return image1;
    }

    /**
     * Obtient une autre image supplémentaire de l'objet.
     *
     * @return L'autre image supplémentaire de l'objet.
     */
    public Image getImage2() {
        return image2;
    }

    /**
     * Obtient la position Y par défaut de la boîte de collision.
     *
     * @return La position Y par défaut de la boîte de collision.
     */
    public int getHitboxDefaultY() {
        return hitboxDefaultY;
    }

    /**
     * Obtient la position X par défaut de la boîte de collision.
     *
     * @return La position X par défaut de la boîte de collision.
     */
    public int getHitboxDefaultX() {
        return hitboxDefaultX;
    }

    /**
     * Définit la position Y de l'objet dans le monde.
     *
     * @param worldY La position Y à définir.
     */
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    /**
     * Indique si l'objet est en collision avec un autre.
     *
     * @return true si l'objet est en collision, sinon false.
     */
    public boolean isCollision() {
        return collision;
    }

    /**
     * Définit si l'objet est en collision avec un autre.
     *
     * @param collision true pour indiquer une collision, false sinon.
     */
    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
