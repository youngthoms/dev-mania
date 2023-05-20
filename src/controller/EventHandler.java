package controller;

import abstraction.Entity;
import javafx.scene.shape.Rectangle;
import view.GamePanel;

import static view.GamePanel.TILE_SIZE;

public class EventHandler {

    private GamePanel gp;
    Rectangle eventRectangle;
    int eventRectangleDefaultX, eventRectangleDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        this.eventRectangle = new Rectangle(23, 23, 2, 2);
        this.eventRectangleDefaultX = (int) eventRectangle.getX();
        this.eventRectangleDefaultY = (int) eventRectangle.getY();
    }

    public void damagePit() {
        if (this.gp.getPlayer().getLife()>=1){
            this.gp.getPlayer().setLife(this.gp.getPlayer().getLife() - 1);
        }
    }

    public void checkEvent() {
        // If player hit the 29, 16 case from the right we handle the hit
        if (hit(29, 16, "right")) {
            damagePit();
        }

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;
        Entity player = this.gp.getPlayer();

        player.getHitbox().setX(player.getWorldX() + player.getHitbox().getX());
        player.getHitbox().setY(player.getWorldY() + player.getHitbox().getY());

        this.eventRectangle.setX(eventCol * TILE_SIZE + eventRectangle.getX());
        this.eventRectangle.setY(eventRow * TILE_SIZE + eventRectangle.getY());

        if (player.getHitbox().intersects(this.eventRectangle.getBoundsInParent())) {
            if (player.getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        player.getHitbox().setX(player.getsolidHitboxDefaultX());
        player.getHitbox().setY(player.getsolidHitboxDefaultY());
        this.eventRectangle.setX(this.eventRectangleDefaultX);
        this.eventRectangle.setY(this.eventRectangleDefaultY);

        return hit;
    }
}
