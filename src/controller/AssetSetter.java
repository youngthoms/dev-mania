package controller;

import abstraction.*;
import view.GamePanel;

import static view.GamePanel.TILE_SIZE;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.object[0] = new OBJ_key();
        gp.object[0].setWorldX(23 * TILE_SIZE);
        gp.object[0].setWorldY(7 * TILE_SIZE);

        gp.object[1] = new OBJ_door();
        gp.object[1].setWorldX(23 * TILE_SIZE);
        gp.object[1].setWorldY(10 * TILE_SIZE);

        gp.object[2] = new OBJ_boots();
        gp.object[2].setWorldX(23 * gp.TILE_SIZE);
        gp.object[2].setWorldY(11 * gp.TILE_SIZE);

        gp.object[3] = new OBJ_lifePotion();
        gp.object[3].setWorldX(23 * gp.TILE_SIZE);
        gp.object[3].setWorldY(12 * gp.TILE_SIZE);

        gp.object[4] = new OBJ_Teleporteur();
        gp.object[4].setWorldX(23 * gp.TILE_SIZE);
        gp.object[4].setWorldY(18 * gp.TILE_SIZE);

        gp.object[5] = new OBJ_heart();
        gp.object[5].setWorldX(23 * gp.TILE_SIZE);
        gp.object[5].setWorldY(19 * gp.TILE_SIZE);
    }

    public void setNPC() {
        gp.getNpc()[0] = new NPC_OldMan(this.gp);
        gp.getNpc()[0].setWorldX(21 * TILE_SIZE);
        gp.getNpc()[0].setWorldY(21 * TILE_SIZE);
    }

    public void setMonster() {
        gp.getMonster()[0] = new MonsterGreenSlime(gp);
        gp.getMonster()[0].setWorldX(TILE_SIZE * 23);
        gp.getMonster()[0].setWorldY(TILE_SIZE * 36);

        gp.getMonster()[1] = new MonsterGreenSlime(gp);
        gp.getMonster()[1].setWorldX(TILE_SIZE * 23);
        gp.getMonster()[1].setWorldY(TILE_SIZE * 37);
    }

}
