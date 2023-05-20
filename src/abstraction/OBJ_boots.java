package abstraction;

import javafx.scene.image.Image;

import static view.GamePanel.TILE_SIZE;

public class OBJ_boots extends SuperObject{

    public OBJ_boots() {
        setName("Boots");
        setImage(new Image("file:res/bonus/boots.png", TILE_SIZE, TILE_SIZE, false, false));
    }

}
