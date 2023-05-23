package controller;

import abstraction.Entity;
import view.GamePanel;

/**
 * Vérifie les collisions avec les objets du jeu.
 */
public class ObjectColisionChecker {
    GamePanel gp;

    /**
     * Initialise un nouvel objet ObjectColisionChecker.
     *
     * @param gp Le panneau de jeu associé.
     */
    public ObjectColisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Vérifie les collisions avec les objets.
     *
     * @param entity L'entité dont on vérifie la collision.
     * @param player Indique s'il s'agit du joueur.
     * @return L'index de l'objet avec lequel une collision s'est produite, ou 999 si aucune collision n'a été détectée.
     */
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
                        break;
                    case "down":
                        entity.getHitbox().setY(entity.getHitbox().getY() + entity.getSpeed());
                        break;
                    case "right":
                        entity.getHitbox().setX(entity.getHitbox().getX() + entity.getSpeed());
                        break;
                    case "left":
                        entity.getHitbox().setX(entity.getHitbox().getX() - entity.getSpeed());
                        break;
                }

                if (entity.getHitbox().intersects(gp.object[i].getHitbox().getBoundsInLocal())) {
                    if (gp.object[i].isCollision()) {
                        entity.setCollisionOn(true);
                    }
                    if (player) {
                        index = i;
                    }
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
