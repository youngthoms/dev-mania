package abstraction.tiles;

import javafx.scene.image.Image;

/**
 * Tile représente une tuile avec une image et une propriété de collision.
 */
public class Tile {
    private Image image; // Image associée à la tuile
    public boolean collision; // Indique si la tuile est un obstacle ou non

    /**
     * Définit l'image de la tuile.
     *
     * @param image L'image à définir.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Obtient l'image de la tuile.
     *
     * @return L'image de la tuile.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Indique si la tuile est un obstacle.
     *
     * @return true si la tuile est un obstacle, sinon false.
     */
    public boolean getCollision() {
        return collision;
    }

    /**
     * Définit la tuile comme un obstacle.
     */
    public void setAsObstacle() {
        this.collision = true;
    }
}
