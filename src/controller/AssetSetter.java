package controller;

import abstraction.Entity;
import abstraction.monsters.Monster_b;
import abstraction.monsters.Monster_greenSlime;
import abstraction.monsters.Monster_m;
import abstraction.monsters.Monster_redSlime;
import abstraction.npc.NPC_GivePotion;
import abstraction.npc.NPC_OldMan;
import abstraction.objects.*;
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

//        gp.object[4] = new OBJ_Teleporteur();
//        gp.object[4].setWorldX(23 * gp.TILE_SIZE);
//        gp.object[4].setWorldY(18 * gp.TILE_SIZE);

        gp.object[5] = new OBJ_heart();
        gp.object[5].setWorldX(24 * gp.TILE_SIZE);
        gp.object[5].setWorldY(10 * gp.TILE_SIZE);

        gp.object[6] = new OBJ_key();
        gp.object[6].setWorldX(35 * TILE_SIZE);
        gp.object[6].setWorldY(10 * TILE_SIZE);

        gp.object[7] = new OBJ_noColide();
        gp.object[7].setWorldX(23 * TILE_SIZE);
        gp.object[7].setWorldY(16 * TILE_SIZE);

        gp.object[8] = new OBJ_invincible();
        gp.object[8].setWorldX(39 * TILE_SIZE);
        gp.object[8].setWorldY(12 * TILE_SIZE);

        gp.object[9] = new OBJ_newMap();
        gp.object[9].setWorldX(39 * TILE_SIZE);
        gp.object[9].setWorldY(11 * TILE_SIZE);

        gp.object[10] = new OBJ_X();
        gp.object[10].setWorldX(23 * TILE_SIZE);
        gp.object[10].setWorldY(34 * TILE_SIZE);
    }

    public void setNPC() {
        gp.getNpc()[0] = new NPC_OldMan(this.gp);
        gp.getNpc()[0].setWorldX(21 * TILE_SIZE);
        gp.getNpc()[0].setWorldY(21 * TILE_SIZE);

        gp.getNpc()[1] = new NPC_GivePotion(this.gp);
        gp.getNpc()[1].setWorldX(23 * TILE_SIZE);
        gp.getNpc()[1].setWorldY(23 * TILE_SIZE);
    }

    public void setMonster() {
        gp.getMonster()[0] = new Monster_greenSlime(gp);
        gp.getMonster()[0].setWorldX(TILE_SIZE * 23);
        gp.getMonster()[0].setWorldY(TILE_SIZE * 36);

        gp.getMonster()[1] = new Monster_greenSlime(gp);
        gp.getMonster()[1].setWorldX(TILE_SIZE * 23);
        gp.getMonster()[1].setWorldY(TILE_SIZE * 37);

        gp.getMonster()[2] = new Monster_redSlime(gp);
        gp.getMonster()[2].setWorldX(TILE_SIZE * 23);
        gp.getMonster()[2].setWorldY(TILE_SIZE * 38);

        gp.getMonster()[4] = new Monster_b(gp);
        gp.getMonster()[4].setWorldX(TILE_SIZE * 26);
        gp.getMonster()[4].setWorldY(TILE_SIZE * 22);

        gp.getMonster()[5] = new Monster_m(gp);
        gp.getMonster()[5].setWorldX(TILE_SIZE * 20);
        gp.getMonster()[5].setWorldY(TILE_SIZE * 22);

        gp.getMonster()[6] = new Monster_m(gp);
        gp.getMonster()[6].setWorldX(TILE_SIZE * 19);
        gp.getMonster()[6].setWorldY(TILE_SIZE * 22);


        gp.getMonster()[7] = new Monster_m(gp);
        gp.getMonster()[7].setWorldX(TILE_SIZE * 18);
        gp.getMonster()[7].setWorldY(TILE_SIZE * 22);
    }

    public void setDrop(int worldX, int worldY) {
        for (int i = 0; i < gp.object.length; i++) {
            if (gp.object[i] == null) {
                gp.object[i] = new OBJ_Z();
                gp.object[i].setWorldX(worldX);
                gp.object[i].setWorldY(worldY);
                break;
            }
        }
    }
}
