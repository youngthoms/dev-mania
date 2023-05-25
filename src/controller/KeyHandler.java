package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import view.GamePanel;

/**
 * Gère les événements clavier pour le jeu.
 */
public class KeyHandler implements EventHandler<KeyEvent> {
    GamePanel gp;
    public boolean up, down, left, right, interract, attacking;

    /**
     * Initialise un nouvel objet KeyHandler.
     *
     * @param gp Le panneau de jeu associé.
     */
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void handle(KeyEvent event) {
        // PLAY STATE
        if (gp.getGameState() == gp.getPlayState()) {
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                switch (event.getCode()) {
                    case UP:
                        up = true;
                        break;
                    case DOWN:
                        down = true;
                        break;
                    case LEFT:
                        left = true;
                        break;
                    case RIGHT:
                        right = true;
                        break;
                    case P:
                        gp.setGameState(gp.getPauseState());
                        up = false;
                        down = false;
                        right = false;
                        left = false;
                        break;
                    case I:
                        interract = true;
                        break;
                    case ENTER:
                        attacking = true;
                        break;
                    case SPACE:
                        if (gp.getPlayer().getHasLifePotion() > 0) {
                            gp.getPlayer().setLife(gp.getPlayer().getMaxLife());
                            gp.getPlayer().setHasLifePotion(gp.getPlayer().getHasLifePotion() - 1);
                        }
                        break;
                }
            } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                switch (event.getCode()) {
                    case UP:
                        up = false;
                        break;
                    case DOWN:
                        down = false;
                        break;
                    case LEFT:
                        left = false;
                        break;
                    case RIGHT:
                        right = false;
                        break;
                    case ENTER:
                        attacking = false;
                        break;
                }
            }
        }
        // PAUSE STATE
        else if (gp.getGameState() == gp.getPauseState()) {
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                switch (event.getCode()) {
                    case P:
                        gp.setGameState(gp.getPlayState());
                        up = false;
                        down = false;
                        right = false;
                        left = false;
                        break;
                }
            }
        }
        // DIALOGUES STATE
        else if (gp.getGameState() == gp.getDialogueState()) {
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                switch (event.getCode()) {
                    case Q:
                        gp.setGameState(gp.getPlayState());
                        up = false;
                        down = false;
                        right = false;
                        left = false;
                        break;
                }
            }
        }
    }
}
