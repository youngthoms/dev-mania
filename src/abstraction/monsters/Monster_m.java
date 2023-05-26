package abstraction.monsters;

import javafx.scene.image.Image;
import view.GamePanel;

public class Monster_m extends Monster_Slime {
    public Monster_m(GamePanel gp) {
        super(gp);
        this.setMaxLife(1);
        this.setLife(this.getMaxLife());
        this.setName("M");
    }

    @Override
    public void getImage() {
        setUp1(new Image("res/monster/m.png"));
        setUp2(new Image("res/monster/m.png"));
        setDown1(new Image("res/monster/m.png"));
        setDown2(new Image("res/monster/m.png"));
        setLeft1(new Image("res/monster/m.png"));
        setLeft2(new Image("res/monster/m.png"));
        setRight1(new Image("res/monster/m.png"));
        setRight2(new Image("res/monster/m.png"));
    }
}
