package view;

import abstraction.Entity;
import abstraction.Player;
import abstraction.SuperObject;
import abstraction.TileManager;
import controller.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


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

    //GAME STATE

    private int gameState;
    private int playState = 1;
    private int pauseState = 2;
    private int dialogueState = 3;

    TileManager tileManager = new TileManager(this);
    private Collision collisionChecker;
    private Thread gameThread;
    private KeyHandler keyH;
    private Player player;
    private UI ui;

    private ObjectColisionChecker colisionObject;
    public SuperObject object[] = new SuperObject[10];
    private Entity npc[] = new Entity[10];
    public AssetSetter assetSetter = new AssetSetter(this);
    private EventHandler eventHandler;

    public GamePanel() {
        super(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.keyH = new KeyHandler(this);
        this.player = new Player(this, keyH);
        this.collisionChecker = new Collision(this);
        this.ui = new UI(this);
        this.colisionObject = new ObjectColisionChecker(this);
        this.eventHandler = new EventHandler(this);
    }

    public Player getPlayer() {
        return player;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
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

    public int getGameState() {
        return gameState;
    }

    public void update() {
        if (getGameState() == getPlayState()) {
            player.update();

            for (Entity ent : npc) {
                if (ent != null) {
                    ent.update();
                }
            }
        }
        if(getGameState()== getPauseState()){

        }
    }

    public Collision getCollisionChecker() {
        return collisionChecker;
    }

    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        tileManager.draw(gc);

        for (SuperObject so : object) {
            if (so != null) {
                so.draw(gc, this);
            }
        }
        for (Entity ent : getNpc()) {
            if (ent != null) {
                ent.draw(gc);
            }
        }

        player.draw(gc);
        ui.draw(gc);
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public void setUpGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        setGameState(getPlayState());
    }

    public ObjectColisionChecker getColisionObject() {
        return colisionObject;
    }

    public void setColisionObject(ObjectColisionChecker colisionObject) {
        this.colisionObject = colisionObject;
    }

    public Entity[] getNpc() {
        return npc;
    }

    public void setNpc(Entity[] npc) {
        this.npc = npc;
    }

    public int getPauseState() {
        return pauseState;
    }

    public void setPauseState(int pauseState) {
        this.pauseState = pauseState;
    }

    public int getPlayState() {
        return playState;
    }

    public void setPlayState(int playState) {
        this.playState = playState;
    }

    public int getDialogueState() {
        return dialogueState;
    }

    public void setDialogueState(int dialogueState) {
        this.dialogueState = dialogueState;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }
}
