package abstraction;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.GamePanel;

public class SuperObject {

    public Image image;
    public String name;
    public boolean colision = false;
    public int worldX,worldY;

    public void draw(GraphicsContext gc, GamePanel gp){
        int screenX = worldX - gp.getPlayer().getWorldX()+gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY()+gp.getPlayer().getScreenY();

        if (worldX + gp.TILE_SIZE > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.TILE_SIZE < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.TILE_SIZE > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.TILE_SIZE < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY() ){
            gc.drawImage(image,screenX,screenY);
        }

    }
}
