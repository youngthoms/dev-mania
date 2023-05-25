package controller;

import abstraction.Player;
import abstraction.tiles.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.GamePanel;

import java.io.*;

import static view.GamePanel.*;

/**
 * TileManager gère les tuiles de la carte.
 */
public class TileManager {
    private String filePath;
    private GamePanel gp; // Instance du GamePanel
    private Tile[] tile; // Tableau des tuiles
    private int mapTileNum[][]; // Tableau des numéros de tuiles de la carte

    /**
     * Constructeur de TileManager.
     *
     * @param gp Instance du GamePanel.
     */
    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new Tile[10];
        this.mapTileNum = new int[MAX_WORLD_COLUMN][MAX_WORLD_ROW];
        this.getTileImage();
        this.setFilePath("res/map/world.csv");
        this.loadMap();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Initialise les images des tuiles.
     */
    public void getTileImage() {
        // Initialisation des images des tuiles
        tile[0] = new Tile();
        tile[0].setImage(new Image("res/map/grass.png", TILE_SIZE, TILE_SIZE, false, false));

        tile[1] = new Tile();
        tile[1].setImage(new Image("res/map/wall.png", TILE_SIZE, TILE_SIZE, false, false));
        tile[1].setAsObstacle();

        tile[2] = new Tile();
        tile[2].setImage(new Image("res/map/water.png", TILE_SIZE, TILE_SIZE, false, false));
        tile[2].setAsObstacle();

        tile[3] = new Tile();
        tile[3].setImage(new Image("res/map/earth.png", TILE_SIZE, TILE_SIZE, false, false));

        tile[4] = new Tile();
        tile[4].setImage(new Image("res/map/tree.png", TILE_SIZE, TILE_SIZE, false, false));
        tile[4].setAsObstacle();

        tile[5] = new Tile();
        tile[5].setImage(new Image("res/map/sand.png", TILE_SIZE, TILE_SIZE, false, false));

        tile[6] = new Tile();
        tile[6].setImage(new Image("res/map/tree.png", TILE_SIZE, TILE_SIZE, false, false));
        tile[6].setAsObstacle();
    }

    /**
     * Obtient le tableau des numéros de tuiles de la carte.
     *
     * @return Le tableau des numéros de tuiles de la carte.
     */
    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    /**
     * Obtient le tableau des tuiles.
     *
     * @return Le tableau des tuiles.
     */
    public Tile[] getTile() {
        return tile;
    }

    /**
     * Charge la carte à partir d'un fichier CSV.
     */
    public void loadMap() {
        String filePath = this.getFilePath();
        try {
            InputStream is = TileManager.class.getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
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

    /**
     * Dessine les tuiles sur le contexte graphique.
     *
     * @param gc Le contexte graphique.
     */
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
