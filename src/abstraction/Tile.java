package abstraction;

import javafx.scene.image.Image;

public class Tile {
    private Image image;
    private boolean colision;
    public Tile() {
    }

    public Image getImage(){
        return this.image;
    }

    public void setImage(Image image){
        this.image=image;
    }
}
