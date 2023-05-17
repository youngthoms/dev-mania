package view;

import abstraction.Player;
import abstraction.SuperObject;
import abstraction.TileManager;
import controller.Collision;
import controller.AssetSetter;
import controller.KeyHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class GamePanel extends Canvas implements Runnable {
    // Screen settings
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48*48 tile
    public static final int MAX_SCREEN_COLUMN = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN; // 48*16 = 768 px
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 48*12 = 576 px

    // WORLD SETTINGS
    public static final int MAX_WORLD_COLUMN = 50;
    public static final int MAX_WORLD_ROW = 50;
    public static final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COLUMN;
    public static final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;

    // FPS
    public static final int FPS = 45;

    TileManager tileManager = new TileManager(this);
    private Collision collisionChecker;
    Thread gameThread;
    KeyHandler keyH;
    private Player player;

    public SuperObject object[] = new SuperObject[10];
    public AssetSetter assetSetter = new AssetSetter(this);

    public GamePanel(KeyHandler keyH) {
        super(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.player = new Player(this, keyH);
        this.collisionChecker = new Collision(this);
    }

    public Player getPlayer() {
        return player;
    }

    public TileManager getTileManager() {
        return tileManager;
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
            draw(this.getGraphicsContext2D());

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

    public Collision getCollisionChecker() {
        return collisionChecker;
    }

    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        tileManager.draw(gc);

        for (int i = 0; i < object.length; i++) {
            if (object[i] != null) {
                object[i].draw(gc, this);
            }
        }
        player.draw(gc);
        gc.stroke();
    }

    public void setUpGame() {
        assetSetter.setObject();
    }
}
