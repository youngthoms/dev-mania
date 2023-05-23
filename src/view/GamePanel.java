package view;

import abstraction.*;
import abstraction.objects.OBJ_door;
import abstraction.objects.SuperObject;
import abstraction.tiles.TileManager;
import controller.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Cette classe représente le panneau de jeu.
 */
public class GamePanel extends Canvas implements Runnable {
    // Paramètres de l'écran
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // Tuile de 48*48 pixels
    public static final int MAX_SCREEN_COLUMN = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN; // 768 px
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 px

    // Paramètres du monde
    public static final int MAX_WORLD_COLUMN = 50;
    public static final int MAX_WORLD_ROW = 50;
    public static final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COLUMN;
    public static final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;

    // FPS
    public static final int FPS = 45;

    // ÉTAT DU JEU

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
    private Entity monster[] = new Entity[20];
    public AssetSetter assetSetter = new AssetSetter(this);
    private EventHandler eventHandler;

    /**
     * Initialise un nouveau panneau de jeu.
     */
    public GamePanel() {
        super(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.keyH = new KeyHandler(this);
        this.player = new Player(this, keyH);
        this.collisionChecker = new Collision(this);
        this.ui = new UI(this);
        this.colisionObject = new ObjectColisionChecker(this);
        this.eventHandler = new EventHandler(this);
    }

    public Entity[] getMonster() {
        return monster;
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

    /**
     * Lance le thread du jeu.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Vérifie si tous les coffres ont été gagnés.
     *
     * @return true si tous les coffres ont été gagnés, sinon false.
     */
    public boolean winChest() {
        for (SuperObject so : object) {
            if (so instanceof OBJ_door) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si tous les monstres ont été vaincus.
     *
     * @return true si tous les monstres ont été vaincus, sinon false.
     */
    public boolean winMonster() {
        for (Entity ent : monster) {
            if (ent != null) {
                return false;
            }
        }
        return true;
    }



    /**
     * Cette méthode représente la boucle de jeu exécutée par le thread.
     */
    @Override
    public void run() {
        while (gameThread != null) {
            // Vérifie si le joueur est en vie et s'il n'a pas encore gagné les coffres ou les monstres
            if (getPlayer().getLife() != 0 && !winChest() && !winMonster()) {
                double drawInterval = 1000000000 / FPS; // 0.01666 secondes
                double nextDrawTime = System.nanoTime() + drawInterval;

                // Met à jour les informations telles que la position du personnage
                update();

                // Dessine l'écran avec les informations mises à jour
                draw(this.getGraphicsContext2D());

                try {
                    double remainingTime = nextDrawTime - System.nanoTime();
                    remainingTime = remainingTime / 1000000;

                    if (remainingTime < 0) {
                        remainingTime = 0;
                    }

                    // Attend le temps restant avant le prochain dessin
                    Thread.sleep((long) remainingTime);

                    nextDrawTime += drawInterval;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Vérifie les conditions de fin du jeu
            if (getPlayer().getLife() == 0) {
                this.getUi().setCurrentDialogue("You loose");
                getUi().drawDialogueScreen();
                break;
            } else if (winChest() || winMonster()) {
                this.getUi().setCurrentDialogue("Win");
                getUi().drawDialogueScreen();
                break;
            }
        }
    }

    /**
     * Retourne l'état actuel du jeu.
     *
     * @return L'état du jeu.
     */
    public int getGameState() {
        return gameState;
    }

    /**
     * Met à jour les éléments du jeu.
     */
    public void update() {
        if (getGameState() == getPlayState()) {
            player.update();

            for (Entity ent : npc) {
                if (ent != null) {
                    ent.update();
                }
            }

            for (Entity ent : monster) {
                if (ent != null) {
                    ent.update();
                }
            }
        }
        if (getGameState() == getPauseState()) {

        }
    }

    /**
     * Retourne l'objet responsable de la gestion des collisions.
     *
     * @return L'objet CollisionChecker.
     */
    public Collision getCollisionChecker() {
        return collisionChecker;
    }

    /**
     * Dessine les éléments du jeu sur le contexte graphique spécifié.
     *
     * @param gc Le contexte graphique.
     */
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
        for (Entity ent : this.getMonster()) {
            if (ent != null) {
                ent.draw(gc);
            }
        }
        player.draw(gc);
        ui.draw(gc);
    }

    /**
     * Définit l'état du jeu.
     *
     * @param gameState L'état du jeu.
     */
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    /**
     * Initialise le jeu.
     */
    public void setUpGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setMonster();
        setGameState(getPlayState());
    }

    /**
     * Retourne l'objet responsable de la gestion des collisions avec les objets.
     *
     * @return L'objet ObjectColisionChecker.
     */
    public ObjectColisionChecker getColisionObject() {
        return colisionObject;
    }

    /**
     * Définit l'objet responsable de la gestion des collisions avec les objets.
     *
     * @param colisionObject L'objet ObjectColisionChecker.
     */
    public void setColisionObject(ObjectColisionChecker colisionObject) {
        this.colisionObject = colisionObject;
    }

    /**
     * Retourne les PNJ du jeu.
     *
     * @return Un tableau d'entités représentant les PNJ.
     */
    public Entity[] getNpc() {
        return npc;
    }

    /**
     * Définit les PNJ du jeu.
     *
     * @param npc Un tableau d'entités représentant les PNJ.
     */
    public void setNpc(Entity[] npc) {
        this.npc = npc;
    }

    /**
     * Retourne l'état de pause du jeu.
     *
     * @return L'état de pause du jeu.
     */
    public int getPauseState() {
        return pauseState;
    }

    /**
     * Définit l'état de pause du jeu.
     *
     * @param pauseState L'état de pause du jeu.
     */
    public void setPauseState(int pauseState) {
        this.pauseState = pauseState;
    }

    /**
     * Retourne l'état de jeu en cours.
     *
     * @return L'état de jeu en cours.
     */
    public int getPlayState() {
        return playState;
    }

    /**
     * Définit l'état de jeu en cours.
     *
     * @param playState L'état de jeu en cours.
     */
    public void setPlayState(int playState) {
        this.playState = playState;
    }

    /**
     * Retourne l'état de dialogue du jeu.
     *
     * @return L'état de dialogue du jeu.
     */
    public int getDialogueState() {
        return dialogueState;
    }

    /**
     * Définit l'état de dialogue du jeu.
     *
     * @param dialogueState L'état de dialogue du jeu.
     */
    public void setDialogueState(int dialogueState) {
        this.dialogueState = dialogueState;
    }

    /**
     * Retourne l'interface utilisateur du jeu.
     *
     * @return L'interface utilisateur du jeu.
     */
    public UI getUi() {
        return ui;
    }

    /**
     * Définit l'interface utilisateur du jeu.
     *
     * @param ui L'interface utilisateur du jeu.
     */
    public void setUi(UI ui) {
        this.ui = ui;
    }

}
