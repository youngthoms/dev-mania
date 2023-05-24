package abstraction;

import controller.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import view.GamePanel;

import java.io.File;

import static view.GamePanel.*;

/**
 * Classe représentant le joueur dans le jeu.
 */
public class Player extends Entity {
    private int hasKey = 0;
    public static final String RES_URL = "res" + File.separator + "player";
    KeyHandler keyH;
    private int hasLifePotion = 0;

    /**
     * Constructeur de la classe Player.
     *
     * @param g    Le GamePanel associé.
     * @param keyH L'objet KeyHandler associé.
     */
    public Player(GamePanel g, KeyHandler keyH) {
        super(g);
        this.keyH = keyH;
        this.getPlayerImage();
        this.getPlayerAttackImage();

        // Hitbox
        this.setHitbox(new Rectangle(8, 16, 32, 32));
        this.setSolidHitboxDefaultX((int) this.getHitbox().getX());
        this.setSolidHitboxDefaultY((int) this.getHitbox().getY());

        // Attack hitbox
        this.setAttackHitbox(new Rectangle(0, 0, 36, 36));

        // Player status
        this.setMaxLife(6); // 6 Life = 3 hearts
        this.setLife(this.getMaxLife());

        this.setDefaultValues(TILE_SIZE * 23, TILE_SIZE * 20, 5, "down");
    }

    /**
     * Méthode statique pour obtenir l'URL de l'image du joueur.
     *
     * @param imageName Le nom de l'image.
     * @return L'URL de l'image.
     */
    public static String getURL(String imageName) {
        return RES_URL + File.separator + imageName;
    }

    /**
     * Met à jour le joueur.
     */
    public void update() {
        String direction = this.getDirection();
        if (this.isAttacking()) {
            attackingAnimation();
        } else if (keyH.up || keyH.down || keyH.left || keyH.right || keyH.interract || keyH.attacking) {
            if (keyH.up) {
                this.setDirection("up");
            } else if (keyH.down) {
                this.setDirection("down");
            } else if (keyH.left) {
                this.setDirection("left");
            } else if (keyH.right) {
                this.setDirection("right");
            }

            //check attack state
            attack();

            // Check tile collision
            this.setCollisionOn(false);
            this.getGamePanel().getCollisionChecker().checkTile(this);

            // Check object collision
            int objectIndex = this.getGamePanel().getColisionObject().checkObject(this, true);
            pickUpObject(objectIndex);

            // Check event
            this.getGamePanel().getEventHandler().checkEvent();

            // Check NPC collision
            int npcIndex = getGamePanel().getCollisionChecker().checkEntity(this, getGamePanel().getNpc());
            interactNPC(npcIndex);

            // Check monster collision
            int monsterIndex = this.getGamePanel().getCollisionChecker().checkEntity(this, getGamePanel().getMonster());
            contactMonster(monsterIndex);

            // If collision is false, player can move
            if (this.getCollisionOn() == false) {
                switch (direction) {
                    case "up":
                        this.moveUp();
                        break;
                    case "down":
                        this.moveDown();
                        break;
                    case "left":
                        this.moveLeft();
                        break;
                    case "right":
                        this.moveRight();
                        break;
                }
            }

            this.setSpriteCounter(this.getSpriteCounter() + 1);
            if (this.getSpriteCounter() > SPRITE_COUNTER_NUMBER) {
                if (this.getSpriteNumber() == 1) {
                    this.setSpriteNumber(2);
                } else if (this.getSpriteNumber() == 2) {
                    this.setSpriteNumber(1);
                }
                this.setSpriteCounter(0);
            }
        }

        if (this.isInvincible()) {
            this.setInvincibleCounter(this.getInvincibleCounter() + 1);
            if (this.getInvincibleCounter() > FPS) {
                this.setInvincible(false);
                this.setInvincibleCounter(0);
            }
        }
    }

    /**
     * Effectue une animation d'attaque du joueur.
     */
    public void attackingAnimation() {
        this.setSpriteCounter(this.getSpriteCounter() + 1);

        if (this.getSpriteCounter() <= 5) {
            this.setSpriteNumber(1);
        }
        if (this.getSpriteCounter() > 5 && this.getSpriteCounter() <= 25) {
            this.setSpriteNumber(2);

            // Sauvegarde des coordonnées actuelles (worldX, worldY) et de la Hitbox
            int currentWorldX = this.getWorldX();
            int currentWorldY = this.getWorldY();
            int hitboxWidth = (int) this.getHitbox().getWidth();
            int hitboxHeight = (int) this.getHitbox().getHeight();

            // Ajuste les coordonnées pour la zone d'attaque (attackArea)
            switch (this.getDirection()) {
                case "up":
                    this.setWorldY(this.getWorldY() - (int) this.getAttackHitbox().getHeight());
                    break;
                case "down":
                    this.setWorldY(this.getWorldY() + (int) this.getAttackHitbox().getHeight());
                    break;
                case "left":
                    this.setWorldX(this.getWorldX() - (int) this.getAttackHitbox().getWidth());
                    break;
                case "right":
                    this.setWorldX(this.getWorldX() + (int) this.getAttackHitbox().getWidth());
                    break;
            }

            this.getHitbox().setWidth(this.getAttackHitbox().getWidth());
            this.getHitbox().setHeight(this.getAttackHitbox().getHeight());

            // Vérifie les collisions avec les monstres et les endommage
            int monsterIndex = this.getGamePanel().getCollisionChecker().checkEntity(this, this.getGamePanel().getMonster());
            damageMonster(monsterIndex);

            // Rétablit les coordonnées et la Hitbox d'origine
            this.setWorldX(currentWorldX);
            this.setWorldY(currentWorldY);
            this.getHitbox().setWidth(hitboxWidth);
            this.getHitbox().setHeight(hitboxHeight);
        }
        if (this.getSpriteCounter() > 25) {
            this.setSpriteNumber(1);
            this.setSpriteCounter(0);
            this.setAttacking(false);
        }
    }

    /**
     * Gère la collision avec un monstre et réduit la vie du joueur si celui-ci n'est pas invincible.
     *
     * @param index L'indice du monstre avec lequel le joueur est en collision.
     */
    public void contactMonster(int index) {
        if (index != 999) {
            if (!this.isInvincible()) {
                this.setLife(this.getLife() - 1);
                this.setInvincible(true);
            }
        }
    }

    /**
     * Endommage un monstre en réduisant sa vie et le rend invincible.
     *
     * @param index L'indice du monstre à endommager.
     */
    public void damageMonster(int index) {
        if (index != 999) {
            Entity monster = this.getGamePanel().getMonster()[index];
            if (!monster.isInvincible()) {
                monster.setLife(monster.getLife() - 1);
                monster.setInvincible(true);

                if (monster.getLife() <= 0) {
                    this.getGamePanel().getMonster()[index] = null;
                }
            }
        }
    }

    /**
     * Interagit avec un NPC en affichant son dialogue si le joueur a appuyé sur la touche d'interaction.
     *
     * @param index L'indice du NPC avec lequel le joueur interagit.
     */
    public void interactNPC(int index) {
        if (index != 999) {
            if (getGamePanel().getKeyH().interract) {
                getGamePanel().setGameState(getGamePanel().getDialogueState());
                getGamePanel().getNpc()[index].speak();
            }
        }
        getGamePanel().getKeyH().interract = false;
    }

    /**
     * Ramasse un objet du jeu et effectue l'action correspondante.
     *
     * @param index L'indice de l'objet à ramasser.
     */
    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = getGamePanel().object[index].getName();
            switch (objectName) {
                case "Key":
                    hasKey++;
                    getGamePanel().object[index] = null;
                    break;
                case "Door":
                    if (getHasKey() > 0) {
                        hasKey--;
                        getGamePanel().object[index] = null;
                    }
                    break;
                case "Boots":
                    this.setSpeed(8);
                    getGamePanel().object[index] = null;
                    break;
                case "Heart":
                    if (this.getLife() < getMaxLife() - 2) {
                        setLife(getLife() + 2);
                        getGamePanel().object[index] = null;
                    } else if (this.getLife() < getMaxLife()) {
                        setLife(getMaxLife());
                        getGamePanel().object[index] = null;
                    }
                    break;
                case "Teleporteur":
                    this.setWorldX(33 * getGamePanel().TILE_SIZE);
                    this.setWorldY(7 * getGamePanel().TILE_SIZE);
                    break;
                case "LifePotion":
                    setHasLifePotion(getHasLifePotion() + 1);
                    getGamePanel().object[index] = null;
                    break;
                case "NoColide":
                    setNoColideBonus(true);
                    getGamePanel().object[index] = null;
            }
        }
    }

    /**
     * Charge les images du joueur à partir des fichiers correspondants.
     */
    public void getPlayerImage() {
        setUp1(new Image(getURL("boy_up_1_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setUp2(new Image(getURL("boy_up_2_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setDown2(new Image(getURL("boy_down_2_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setDown1(new Image(getURL("boy_down_1_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setLeft1(new Image(getURL("boy_left_1_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setLeft2(new Image(getURL("boy_left_2_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setRight1(new Image(getURL("boy_right_1_2.png"), TILE_SIZE, TILE_SIZE, false, false));
        setRight2(new Image(getURL("boy_right_2_2.png"), TILE_SIZE, TILE_SIZE, false, false));
    }

    /**
     * Charge les images d'attaque du joueur à partir des fichiers correspondants.
     */
    public void getPlayerAttackImage() {
        setAttackUp1(new Image(getURL("boy_attack_up_1.png"), TILE_SIZE, TILE_SIZE * 2, false, false));
        setAttackUp2(new Image(getURL("boy_attack_up_2.png"), TILE_SIZE, TILE_SIZE * 2, false, false));
        setAttackDown1(new Image(getURL("boy_attack_down_1.png"), TILE_SIZE, TILE_SIZE * 2, false, false));
        setAttackDown2(new Image(getURL("boy_attack_down_2.png"), TILE_SIZE, TILE_SIZE * 2, false, false));
        setAttackLeft1(new Image(getURL("boy_attack_left_1.png"), TILE_SIZE * 2, TILE_SIZE, false, false));
        setAttackLeft2(new Image(getURL("boy_attack_left_2.png"), TILE_SIZE * 2, TILE_SIZE, false, false));
        setAttackRight1(new Image(getURL("boy_attack_right_1.png"), TILE_SIZE * 2., TILE_SIZE, false, false));
        setAttackRight2(new Image(getURL("boy_attack_right_2.png"), TILE_SIZE * 2, TILE_SIZE, false, false));
    }


    /**
     * Retourne le nombre de clés que le joueur possède.
     *
     * @return Le nombre de clés que le joueur possède.
     */
    public int getHasKey() {
        return hasKey;
    }

    /**
     * Définit le nombre de clés que le joueur possède.
     *
     * @param hasKey Le nombre de clés à définir.
     */
    public void setHasKey(int hasKey) {
        this.hasKey = hasKey;
    }

    /**
     * Retourne le nombre de potions de vie que le joueur possède.
     *
     * @return Le nombre de potions de vie que le joueur possède.
     */
    public int getHasLifePotion() {
        return hasLifePotion;
    }

    /**
     * Définit le nombre de potions de vie que le joueur possède.
     *
     * @param hasLifePotion Le nombre de potions de vie à définir.
     */
    public void setHasLifePotion(int hasLifePotion) {
        this.hasLifePotion = hasLifePotion;
    }

    /**
     * Lance une attaque du joueur si la touche d'attaque est enfoncée.
     */
    public void attack() {
        if (this.getGamePanel().getKeyH().attacking) {
            this.setAttacking(true);
        }
    }

    /**
     * Dessine le joueur sur le contexte graphique donné.
     *
     * @param gc Le contexte graphique sur lequel dessiner le joueur.
     */
    public void draw(GraphicsContext gc) {
        Image image = null;
        int tempScreenX = this.getScreenX();
        int tempScreenY= this.getScreenY();

        switch (this.getDirection()) {
            case "up":
                if (this.isAttacking()) {
                    tempScreenY = this.getScreenY() - TILE_SIZE;
                    if (this.getSpriteNumber() == 1) {
                        image = getAttackUp1();
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = getAttackUp2();
                    }
                } else {
                    if (this.getSpriteNumber() == 1) {
                        image = getUp1();
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = getUp2();
                    }
                }
                break;
            case "down":
                if (this.isAttacking()) {
                    if (this.getSpriteNumber() == 1) {
                        image = getAttackDown1();
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = getAttackDown2();
                    }
                } else {
                    if (this.getSpriteNumber() == 1) {
                        image = getDown1();
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = getDown2();
                    }
                }
                break;
            case "left":
                if (this.isAttacking()) {
                    tempScreenX = this.getScreenX() - TILE_SIZE;
                    if (this.getSpriteNumber() == 1) {
                        image = getAttackLeft1();
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = getAttackLeft2();
                    }
                } else {
                    if (this.getSpriteNumber() == 1) {
                        image = getLeft1();
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = getLeft2();
                    }
                }
                break;
            case "right":
                if (this.isAttacking()) {
                    if (this.getSpriteNumber() == 1) {
                        image = getAttackRight1();
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = getAttackRight2();
                    }
                } else {
                    if (this.getSpriteNumber() == 1) {
                        image = getRight1();
                    }
                    if (this.getSpriteNumber() == 2) {
                        image = getRight2();
                    }
                }
                break;
        }

        if (this.isInvincible()) {
            gc.strokeText("-1", this.getScreenX(), this.getScreenY());
        }
        gc.drawImage(image, tempScreenX, tempScreenY, image.getRequestedWidth(), image.getRequestedHeight());
    }

    public void loseKey() {
        int numberOfKeys = this.getHasKey();
        if (numberOfKeys > 0) {
            this.setHasKey(numberOfKeys - 1);
        }
    }

}
