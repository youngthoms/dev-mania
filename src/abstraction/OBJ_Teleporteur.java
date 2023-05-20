package abstraction;

import javafx.scene.image.Image;

import static view.GamePanel.TILE_SIZE;

public class OBJ_Teleporteur extends SuperObject {

    public OBJ_Teleporteur() {
        setName("Teleporteur");
        setImage(new Image("res/bonus/teleporteur.png", TILE_SIZE, TILE_SIZE, false, false));
    }
}

