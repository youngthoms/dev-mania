package abstraction;

import javafx.scene.image.Image;
import view.GamePanel;

import static view.GamePanel.TILE_SIZE;

public class OBJ_heart extends SuperObject {
    public OBJ_heart() {
        setName("Heart");
        setImage(new Image("file:res/object/heart_full.png", TILE_SIZE, TILE_SIZE, false, false));
        setImage1(new Image("file:res/object/heart_half.png", TILE_SIZE, TILE_SIZE, false, false));
        setImage2(new Image("file:res/object/heart_blank.png", TILE_SIZE, TILE_SIZE, false, false));
    }

}
