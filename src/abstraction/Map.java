package abstraction;

import view.GamePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Map {
    private  Tile map[][];
    private GamePanel gp;
    public Map(GamePanel gp){

        this.gp = gp;
        this.map = new Tile[gp.SIZE_MAP][gp.SIZE_MAP];
        loadMap();
    }
    public void loadMap(){

        String filePath = "file:res/map/map.txt"; // Chemin du fichier à lire

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("hello world");
        }

    }
}
