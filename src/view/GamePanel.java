package view;

import abstraction.Entity;
import abstraction.Map;
import abstraction.Player;
import controller.KeyHandler;
import controller.TileManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;


public class GamePanel extends Canvas implements Runnable {
    // Screen settings
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48*48 tile
    public static final int MAX_SCREEN_COLUMN = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN; // 48*16 = 768 px
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 48*12 = 576 px
    public static final int FPS = 60;
    public static final int SIZE_MAP=20;
    public static final String TILE_URL = "file:res" + File.separator + "map";

    Thread gameThread;
    KeyHandler keyH;
    private Entity player;
    //private Map map;
    TileManager tileM = new TileManager(this);

    public GamePanel(KeyHandler keyH) throws IOException {
        super(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.player = new Player(this, keyH);
        //this.map=new Map(this);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            double drawInterval = 1000000000 / FPS; // 0.01666 seconds
            double nextDrawTime = System.nanoTime() + drawInterval;
            // Update information such as character position
            update();
            // Draw the screen with the updated information
            try {
                draw(this.getGraphicsContext2D());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update() {
        player.update();
    }

    public void draw(GraphicsContext gc) throws IOException {
        gc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        tileM.draw(gc);
        player.draw(gc);
        gc.stroke();
    }
}
