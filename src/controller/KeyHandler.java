package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import view.GamePanel;

public class KeyHandler implements EventHandler<KeyEvent> {
    GamePanel gp;
    public boolean up, down, left, right,interract;
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void handle(KeyEvent event) {
        System.out.println(event);
        //PLAY STATE
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
                    case I :
                        interract = true;
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
                }
            }
        }
        //PAUSE STATE
        else if (gp.getGameState() == gp.getPauseState()){
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                switch (event.getCode()) {
                    case P:
                        gp.setGameState(gp.getPlayState());
                }
            }
        }
        //DIALOGUES STATE
        else if (gp.getGameState() == gp.getDialogueState()){
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                switch (event.getCode()) {
                    case Q:
                        gp.setGameState(gp.getPlayState());
                }
            }
        }
    }
}
