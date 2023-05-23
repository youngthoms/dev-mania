package abstraction.monsters;

import abstraction.Player;
import javafx.scene.image.Image;
import view.GamePanel;

public class Monster_redSlime extends Monster_Slime {
    /**
     * Constructeur de l'entité.
     *
     * @param g le panneau de jeu auquel l'entité appartient
     */
    public Monster_redSlime(GamePanel g) {
        super(g);
    }

    public void handleCollision(boolean contactPlayer) {
        super.handleCollision(contactPlayer);
        if (contactPlayer) {
            getGamePanel().getPlayer().loseKey();
        }
    }

    public void getImage() {
        setUp1(new Image("res/monster/redslime_down_1.png"));
        setUp2(new Image("res/monster/redslime_down_2.png"));
        setDown1(new Image("res/monster/redslime_down_1.png"));
        setDown2(new Image("res/monster/redslime_down_2.png"));
        setLeft1(new Image("res/monster/redslime_down_1.png"));
        setLeft2(new Image("res/monster/redslime_down_2.png"));
        setRight1(new Image("res/monster/redslime_down_1.png"));
        setRight2(new Image("res/monster/redslime_down_2.png"));
    }
}
