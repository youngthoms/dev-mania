package abstraction.objects;

import javafx.scene.image.Image;

import static view.GamePanel.TILE_SIZE;

public class OBJ_X extends SuperObject {
    public OBJ_X() {
        setName("X");
        setImage(new Image("res/bonus/x.png", TILE_SIZE, TILE_SIZE, false, false));
    }
}
