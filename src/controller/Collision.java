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
}
