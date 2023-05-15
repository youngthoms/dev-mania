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
        this.mapTileNum = new int[MAX_SCREEN_COLUMN][MAX_SCREEN_ROW];
        this.getTileImage();
        this.loadMap("res/map/map.csv");
    }

    public void getTileImage() {
        tile[0] = new Tile();
        tile[0].setImage(new Image("file:res/map/grass.png", TILE_SIZE, TILE_SIZE, false, false));

        tile[1] = new Tile();
        tile[1].setImage(new Image("file:res/map/wall.png", TILE_SIZE, TILE_SIZE, false, false));

        tile[2] = new Tile();
        tile[2].setImage(new Image("file:res/map/water.png", TILE_SIZE, TILE_SIZE, false, false));
    }

    public void loadMap(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            int col = 0, row = 0;
            while (col < MAX_SCREEN_COLUMN && row < MAX_SCREEN_ROW) {
                String line = br.readLine();
                while (col < MAX_SCREEN_COLUMN) {
                    String numbers[] = line.split(",");
                    int num = Integer.parseInt(numbers[col]);
                    this.mapTileNum[col][row] = num;
                    col++;
                }
                if (col == MAX_SCREEN_COLUMN) {
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
        int col = 0, row = 0, x = 0, y = 0;

        while (col < MAX_SCREEN_COLUMN && row < MAX_SCREEN_ROW) {

            int tileNum = this.mapTileNum[col][row];

            gc.drawImage(this.tile[tileNum].getImage(), x, y, TILE_SIZE, TILE_SIZE);
            col++;
            x += TILE_SIZE;

            if (col == MAX_SCREEN_COLUMN) {
                col = 0;
                x = 0;
                row++;
                y += TILE_SIZE;
            }
        }
    }
}