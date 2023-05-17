package controller;

import abstraction.Entity;
import view.GamePanel;

public class ObjectColisionChecker {
    GamePanel gp;

    public ObjectColisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i < gp.object.length; i++) {

            if (gp.object[i] != null) {
                entity.getHitbox().setX(entity.getWorldX() + entity.getHitbox().getX());
                entity.getHitbox().setY(entity.getWorldY() + entity.getHitbox().getY());
                gp.object[i].getHitbox().setX(gp.object[i].getWorldX() + gp.object[i].getHitbox().getX());
                gp.object[i].getHitbox().setY(gp.object[i].getWorldY() + gp.object[i].getHitbox().getY());

                switch (entity.getDirection()) {
                    case "up":
                        entity.getHitbox().setY(entity.getHitbox().getY() - entity.getSpeed());
                        if (entity.getHitbox().intersects(gp.object[i].getHitbox().getBoundsInLocal())) {
                            System.out.println("Collide Up");
                            if (gp.object[i].getColision() == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.getHitbox().setY(entity.getHitbox().getY() + entity.getSpeed());
                        if (entity.getHitbox().intersects(gp.object[i].getHitbox().getBoundsInLocal())) {
                            System.out.println("Collide Down");
                            if (gp.object[i].getColision() == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getHitbox().setX(entity.getHitbox().getX() + entity.getSpeed());
                        if (entity.getHitbox().intersects(gp.object[i].getHitbox().getBoundsInLocal())) {
                            System.out.println("Collide Right");
                            if (gp.object[i].getColision() == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getHitbox().setX(entity.getHitbox().getX() - entity.getSpeed());
                        if (entity.getHitbox().intersects(gp.object[i].getHitbox().getBoundsInLocal())) {
                            System.out.println("Collide Left");
                            if (gp.object[i].getColision() == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }

                entity.getHitbox().setY(entity.getsolidHitboxDefaultY());
                entity.getHitbox().setX(entity.getsolidHitboxDefaultX());

                gp.object[i].getHitbox().setY(gp.object[i].getHitboxDefaultY());
                gp.object[i].getHitbox().setX(gp.object[i].getHitboxDefaultX());


            }
        }
        return index;
    }
}
