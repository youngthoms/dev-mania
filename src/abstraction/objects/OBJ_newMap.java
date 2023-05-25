package abstraction.objects;

import javafx.scene.image.Image;

import static view.GamePanel.TILE_SIZE;

public class OBJ_newMap extends SuperObject {
    public OBJ_newMap() {
        setName("New map");
        setImage(new Image("res/bonus/teleporteur.png", TILE_SIZE, TILE_SIZE, false, false));
    }
}
