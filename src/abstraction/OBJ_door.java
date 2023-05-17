package abstraction;

import javafx.scene.image.Image;

import static view.GamePanel.TILE_SIZE;

public class OBJ_door extends SuperObject {

    public OBJ_door() {
        name = "Key";
        image = new Image("file:res/bonus/door.png", TILE_SIZE, TILE_SIZE, false, false);
    }

}
