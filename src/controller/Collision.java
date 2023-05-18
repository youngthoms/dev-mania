package controller;

import abstraction.Entity;
import abstraction.TileManager;
import view.GamePanel;

import static view.GamePanel.TILE_SIZE;

public class Collision {
    private GamePanel gp;

    public Collision(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = (int) (entity.getWorldX() + entity.getHitbox().getX());
        int entityRightWorldX = (int) (entity.getWorldX() + entity.getHitbox().getX() + entity.getHitbox().getWidth());
        int entityTopWorldY = (int) (entity.getWorldY() + entity.getHitbox().getY());
        int entityBottomWorldY = (int) (entity.getWorldY() + entity.getHitbox().getY() + entity.getHitbox().getHeight());

        int entityLeftCol = entityLeftWorldX / TILE_SIZE;
        int entityRightCol = entityRightWorldX / TILE_SIZE;
        int entityTopRow = entityTopWorldY / TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / TILE_SIZE;

        int tileNum1, tileNum2;

        TileManager tm = this.gp.getTileManager();

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / TILE_SIZE;
                tileNum1 = tm.getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = tm.getMapTileNum()[entityRightCol][entityTopRow];

                if (tm.getTile()[tileNum1].getCollision() || tm.getTile()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / TILE_SIZE;
                tileNum1 = tm.getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = tm.getMapTileNum()[entityRightCol][entityBottomRow];

                if (tm.getTile()[tileNum1].getCollision() || tm.getTile()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / TILE_SIZE;
                tileNum1 = tm.getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = tm.getMapTileNum()[entityLeftCol][entityBottomRow];

                if (tm.getTile()[tileNum1].getCollision() || tm.getTile()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / TILE_SIZE;
                tileNum1 = tm.getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = tm.getMapTileNum()[entityRightCol][entityBottomRow];

                if (tm.getTile()[tileNum1].getCollision() || tm.getTile()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }

    }
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;
        for (int i = 0; i < target.length; i++) {

            if (target[i] != null) {
                entity.getHitbox().setX(entity.getWorldX() + entity.getHitbox().getX());
                entity.getHitbox().setY(entity.getWorldY() + entity.getHitbox().getY());
                target[i].getHitbox().setX(target[i].getWorldX() + target[i].getHitbox().getX());
                target[i].getHitbox().setY(target[i].getWorldY() + target[i].getHitbox().getY());

                switch (entity.getDirection()) {
                    case "up":
                        entity.getHitbox().setY(entity.getHitbox().getY() - entity.getSpeed());
                        if (entity.getHitbox().intersects(target[i].getHitbox().getBoundsInLocal())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "down":
                        entity.getHitbox().setY(entity.getHitbox().getY() + entity.getSpeed());

                        System.out.println("Collide Down");
                        if (entity.getHitbox().intersects(target[i].getHitbox().getBoundsInLocal())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getHitbox().setX(entity.getHitbox().getX() + entity.getSpeed());
                            System.out.println("Collide Right");
                            if (entity.getHitbox().intersects(target[i].getHitbox().getBoundsInLocal())) {
                                entity.setCollisionOn(true);
                                index = i;
                        }
                        break;
                    case "left":
                        entity.getHitbox().setX(entity.getHitbox().getX() - entity.getSpeed());
                        System.out.println("Collide Left");
                        if (entity.getHitbox().intersects(target[i].getHitbox().getBoundsInLocal())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                }

                entity.getHitbox().setY(entity.getsolidHitboxDefaultY());
                entity.getHitbox().setX(entity.getsolidHitboxDefaultX());

                target[i].getHitbox().setY(target[i].getSolidHitboxDefaultY());
                target[i].getHitbox().setX(target[i].getSolidHitboxDefaultX());


            }
        }
        return index;
    }

    public void checkPlayer(Entity entity){
        entity.getHitbox().setX(entity.getWorldX() + entity.getHitbox().getX());
        entity.getHitbox().setY(entity.getWorldY() + entity.getHitbox().getY());
        gp.getPlayer().getHitbox().setX(gp.getPlayer().getWorldX() + gp.getPlayer().getHitbox().getX());
        gp.getPlayer().getHitbox().setY(gp.getPlayer().getWorldY() + gp.getPlayer().getHitbox().getY());

        switch (entity.getDirection()) {
            case "up":
                entity.getHitbox().setY(entity.getHitbox().getY() - entity.getSpeed());
                if (entity.getHitbox().intersects(gp.getPlayer().getHitbox().getBoundsInLocal())) {
                    entity.setCollisionOn(true);

                }
                break;
            case "down":
                entity.getHitbox().setY(entity.getHitbox().getY() + entity.getSpeed());

                System.out.println("Collide Down");
                if (entity.getHitbox().intersects(gp.getPlayer().getHitbox().getBoundsInLocal())) {
                    entity.setCollisionOn(true);

                }
                break;
            case "right":
                entity.getHitbox().setX(entity.getHitbox().getX() + entity.getSpeed());
                System.out.println("Collide Right");
                if (entity.getHitbox().intersects(gp.getPlayer().getHitbox().getBoundsInLocal())) {
                    entity.setCollisionOn(true);

                }
                break;
            case "left":
                entity.getHitbox().setX(entity.getHitbox().getX() - entity.getSpeed());
                System.out.println("Collide Left");
                if (entity.getHitbox().intersects(gp.getPlayer().getHitbox().getBoundsInLocal())) {
                    entity.setCollisionOn(true);

                }
                break;
        }

        entity.getHitbox().setY(entity.getsolidHitboxDefaultY());
        entity.getHitbox().setX(entity.getsolidHitboxDefaultX());

        gp.getPlayer().getHitbox().setY(gp.getPlayer().getSolidHitboxDefaultY());
        gp.getPlayer().getHitbox().setX(gp.getPlayer().getSolidHitboxDefaultX());
    }
}
