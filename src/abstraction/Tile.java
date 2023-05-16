package abstraction;

import javafx.scene.image.Image;

public class Tile {
    private Image image;
    public boolean collision;

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public boolean getCollision() {
        return collision;
    }

    public void setAsObstacle() {
        this.collision = true;
    }

}
