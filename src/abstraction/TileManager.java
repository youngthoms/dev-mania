package abstraction;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.GamePanel;

import java.io.*;

import static view.GamePanel.*;

public class TileManager {
    private GamePanel gp;
    private Tile[] tile;
    private int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new Tile[10];
        this.mapTileNum = new int[MAX_WORLD_COLUMN][MAX_WORLD_ROW];
        this.getTileImage();
        this.loadMap("res/map/world.csv");
    }

    public void getTileImage() {
        tile[0] = new Tile();
        tile[0].setImage(new Image("file:res/map/grass.png", TILE_SIZE, TILE_SIZE, false, false));

        tile[1] = new Tile();
        tile[1].setImage(new Image("file:res/map/wall.png", TILE_SIZE, TILE_SIZE, false, false));
        tile[1].setAsObstacle();

        tile[2] = new Tile();
        tile[2].setImage(new Image("file:res/map/water.png", TILE_SIZE, TILE_SIZE, false, false));
        tile[2].setAsObstacle();

        tile[3] = new Tile();
        tile[3].setImage(new Image("file:res/map/earth.png", TILE_SIZE, TILE_SIZE, false, false));

        tile[4] = new Tile();
        tile[4].setImage(new Image("file:res/map/tree.png", TILE_SIZE, TILE_SIZE, false, false));
        tile[4].setAsObstacle();

        tile[5] = new Tile();
        tile[5].setImage(new Image("file:res/map/sand.png", TILE_SIZE, TILE_SIZE, false, false));
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public Tile[] getTile() {
        return tile;
    }

    public void loadMap(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            int col = 0, row = 0;
            String line;
            while (col < MAX_WORLD_COLUMN && row < MAX_WORLD_ROW && (line = br.readLine()) != null) {
                while (col < MAX_WORLD_COLUMN) {
                    String numbers[] = line.split(",");
                    int num = Integer.parseInt(numbers[col]);
                    this.mapTileNum[col][row] = num;
                    col++;
                }
                if (col == MAX_WORLD_COLUMN) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(GraphicsContext gc) {
        int worldCol = 0, worldRow = 0;
        Player player = this.gp.getPlayer();
        while (worldCol < MAX_WORLD_COLUMN && worldRow < MAX_WORLD_ROW) {

            int tileNum = this.mapTileNum[worldCol][worldRow];

            int worldX = worldCol * TILE_SIZE;
            int worldY = worldRow * TILE_SIZE;
            int screenX = worldX - player.getWorldX() + player.getScreenX();
            int screenY = worldY - player.getWorldY() + player.getScreenY();

            if (
                    worldX + TILE_SIZE > player.getWorldX() - player.getScreenX() &&
                    worldX - TILE_SIZE < player.getWorldX() + player.getScreenX() &&
                    worldY + TILE_SIZE > player.getWorldY() - player.getScreenY() &&
                    worldY - TILE_SIZE < player.getWorldY() + player.getScreenY()
            ) {
                gc.drawImage(this.tile[tileNum].getImage(), screenX, screenY, TILE_SIZE, TILE_SIZE);
            }
            worldCol++;

            if (worldCol == MAX_WORLD_COLUMN) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}