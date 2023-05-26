package abstraction.monsters;

import javafx.scene.image.Image;
import view.GamePanel;

public class Monster_b extends Monster_Slime {

    public Monster_b(GamePanel g) {
        super(g);
        this.setName("B");
    }
    public void getImage() {
        setUp1(new Image("res/monster/b.png"));
        setUp2(new Image("res/monster/b.png"));
        setDown1(new Image("res/monster/b.png"));
        setDown2(new Image("res/monster/b.png"));
        setLeft1(new Image("res/monster/b.png"));
        setLeft2(new Image("res/monster/b.png"));
        setRight1(new Image("res/monster/b.png"));
        setRight2(new Image("res/monster/b.png"));
    }
}
