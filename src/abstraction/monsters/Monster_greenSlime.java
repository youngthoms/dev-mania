package abstraction.monsters;

import javafx.scene.image.Image;
import view.GamePanel;

public class Monster_greenSlime extends Monster_Slime {

    /**
     * Constructeur de l'entité.
     *
     * @param g le panneau de jeu auquel l'entité appartient
     */
    public Monster_greenSlime(GamePanel g) {
        super(g);
    }

    public void getImage() {
        setUp1(new Image("res/monster/greenslime_down_1.png"));
        setUp2(new Image("res/monster/greenslime_down_2.png"));
        setDown1(new Image("res/monster/greenslime_down_1.png"));
        setDown2(new Image("res/monster/greenslime_down_2.png"));
        setLeft1(new Image("res/monster/greenslime_down_1.png"));
        setLeft2(new Image("res/monster/greenslime_down_2.png"));
        setRight1(new Image("res/monster/greenslime_down_1.png"));
        setRight2(new Image("res/monster/greenslime_down_2.png"));
    }
}
