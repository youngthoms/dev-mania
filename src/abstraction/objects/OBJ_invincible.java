package abstraction.objects;

import javafx.scene.image.Image;

import static view.GamePanel.TILE_SIZE;

public class OBJ_invincible extends SuperObject {
    public OBJ_invincible() {
        setName("Invincible");
        setImage(new Image("res/object/heart_full.png", TILE_SIZE, TILE_SIZE, false, false));
    }
}
