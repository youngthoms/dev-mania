package controller;

import abstraction.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.io.*;

public class TileManager {
    private static GamePanel gp;
    private Tile[] tile;
    private int[][] mapTileNum;
    public TileManager(GamePanel gp) throws IOException {
        this.gp = gp;
        this.mapTileNum=new int [gp.MAX_SCREEN_COLUMN][gp.MAX_SCREEN_ROW];
        this.tile = new Tile[gp.SIZE_MAP];
        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
        }
        loadMap();
    }
    public static String getTileURL(String ImageName) {

        return  gp.TILE_URL + File.separator + ImageName;
    }

    public void getTileImage(Tile[] tile) throws IOException {
        tile[0]=new Tile();
        tile[0].setImage(new Image (getClass().getResourceAsStream("grass.png")));

        tile[1]=new Tile();
        tile[1].setImage(new Image (getClass().getResourceAsStream("water.png")));

        tile[2]=new Tile();
        tile[2].setImage(new Image (getClass().getResourceAsStream("wall.png")));

    }

    public void draw (GraphicsContext g2) throws IOException {
        int col=0,row=0,x=0,y=0;
        getTileImage(this.tile);
        while(col<gp.MAX_SCREEN_COLUMN && row<gp.MAX_SCREEN_ROW){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].getImage(),x,y,gp.TILE_SIZE,gp.TILE_SIZE);
            ++col;
            x+=gp.TILE_SIZE;

            if(col == gp.MAX_SCREEN_COLUMN){
                col = 0;
                x=0;
                row++;
                y+=gp.TILE_SIZE;
            }

        }
    }

    public void loadMap() throws IOException {
        InputStream is = getClass().getResourceAsStream("map.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int col=0,row=0;
        while(col<gp.MAX_SCREEN_COLUMN && row<gp.MAX_SCREEN_ROW){
            String line = br.readLine();
            while(col<gp.MAX_SCREEN_COLUMN){
                String numbers[]=line.split(" ");
                int num = Integer.parseInt(numbers[col]);
                this.mapTileNum[col][row]=num;
                ++col;
            }
            if(col==gp.MAX_SCREEN_COLUMN){
                col=0;
                row++;
            }

        }
        br.close();
    }
}
