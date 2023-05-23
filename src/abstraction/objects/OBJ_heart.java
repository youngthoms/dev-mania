package abstraction.objects;

import javafx.scene.image.Image;

import static view.GamePanel.TILE_SIZE;

public class OBJ_heart extends SuperObject {
    public OBJ_heart() {
        setName("Heart");
        setImage(new Image("res/object/heart_full.png", TILE_SIZE, TILE_SIZE, false, false));
        setImage1(new Image("res/object/heart_half.png", TILE_SIZE, TILE_SIZE, false, false));
        setImage2(new Image("res/object/heart_blank.png", TILE_SIZE, TILE_SIZE, false, false));
    }

}
