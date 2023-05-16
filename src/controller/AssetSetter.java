package controller;

import abstraction.OBJ_door;
import abstraction.OBJ_key;
import view.GamePanel;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }

    public void setObject(){
        gp.object[0]=new OBJ_key();
        gp.object[0].worldX = 23*gp.TILE_SIZE;
        gp.object[0].worldY = 7*gp.TILE_SIZE;

        gp.object[1]=new OBJ_door();
        gp.object[1].worldX= 23 *gp.TILE_SIZE;
        gp.object[1].worldY=10 *gp.TILE_SIZE;
    }

}
