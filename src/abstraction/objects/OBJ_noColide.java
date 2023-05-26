package abstraction.objects;

import javafx.scene.image.Image;

import static view.GamePanel.TILE_SIZE;

public class OBJ_noColide extends SuperObject {
    public OBJ_noColide() {
        setName("NoColide");
        setImage(new Image("res/bonus/y.png", TILE_SIZE, TILE_SIZE, false, false));
    }
}
