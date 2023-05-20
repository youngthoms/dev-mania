package abstraction;

import javafx.scene.image.Image;

import static view.GamePanel.TILE_SIZE;

public class OBJ_lifePotion extends SuperObject {

    public OBJ_lifePotion() {
        setName("LifePotion");
        setImage(new Image("file:res/bonus/potion_red.png", TILE_SIZE, TILE_SIZE, false, false));
    }
}
