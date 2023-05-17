package abstraction;

import javafx.scene.image.Image;

import static view.GamePanel.TILE_SIZE;

public class OBJ_key extends SuperObject {

    public OBJ_key() {
        setName("Key");
        setImage(new Image("file:res/bonus/key.png", TILE_SIZE, TILE_SIZE, false, false));
    }

}