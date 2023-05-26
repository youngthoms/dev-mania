package abstraction.objects;

import javafx.scene.image.Image;

import static view.GamePanel.TILE_SIZE;

public class OBJ_Z extends SuperObject {
    public OBJ_Z() {
        setName("Z");
        setImage(new Image("res/bonus/x.png", TILE_SIZE, TILE_SIZE, false, false));
    }
}
