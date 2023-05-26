package abstraction;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import view.GamePanel;

import static view.GamePanel.*;
import static view.GamePanel.TILE_SIZE;

/**
 * Cette classe représente une entité dans le jeu.
 */
public class Entity {
    // Attributs de l'entité
    private int worldX, worldY, speed, life, maxLife, strength;
    private boolean oneShot = false;
    private String name = "any";
    private GamePanel gp;
    private int spriteCounter = 0;
    private int spriteNumber = 1;
    private int solidHitboxDefaultX, solidHitboxDefaultY;
    private Rectangle hitbox;
    private Rectangle attackHitbox;
    private String direction;
    private boolean collisionOn = false;
    private boolean invincible = false;
    private boolean attacking = false;
    private int invincibleCounter = 0;
    private int screenX, screenY;
    public static final int SPRITE_COUNTER_NUMBER = 9;
    private Image up1, up2, down1, down2, left1, left2, right1, right2;
    private Image attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String[] dialogues;
    private boolean noColideBonus = false;


    /**
     * Constructeur de l'entité.
     *
     * @param g le panneau de jeu auquel l'entité appartient
     */
    public Entity(GamePanel g) {
        this.gp = g;
        this.setScreenX(SCREEN_WIDTH / 2 - (TILE_SIZE / 2));
        this.setScreenY(SCREEN_HEIGHT / 2 - (TILE_SIZE / 2));

        dialogues = new String[15];
    }

    // Getters et setters pour les attributs

    /**
     * Définit les valeurs par défaut de l'entité.
     *
     * @param worldX    la position en X dans le monde
     * @param worldY    la position en Y dans le monde
     * @param speed     la vitesse de déplacement
     * @param direction la direction de l'entité
     */
    public void setDefaultValues(int worldX, int worldY, int speed, String direction) {
        this.setWorldX(worldX);
        this.setWorldY(worldY);
        this.setSpeed(speed);
        this.setDirection(direction);
    }

    /**
     * Définit la hitbox d'attaque de l'entité.
     *
     * @param attackHitbox la hitbox d'attaque
     */
    public void setAttackHitbox(Rectangle attackHitbox) {
        this.attackHitbox = attackHitbox;
    }

    /**
     * Retourne la hitbox d'attaque de l'entité.
     *
     * @return la hitbox d'attaque
     */
    public Rectangle getAttackHitbox() {
        return attackHitbox;
    }

    /**
     * Définit si l'entité est en train d'attaquer.
     *
     * @param attacking true si l'entité est en train d'attaquer, sinon false
     */
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    /**
     * Vérifie si l'entité est en train d'attaquer.
     *
     * @return true si l'entité est en train d'attaquer, sinon false
     */
    public boolean isAttacking() {
        return attacking;
    }

    /**
     * Retourne le compteur d'invincibilité de l'entité.
     *
     * @return le compteur d'invincibilité
     */
    public int getInvincibleCounter() {
        return invincibleCounter;
    }

    /**
     * Vérifie si l'entité est invincible.
     *
     * @return true si l'entité est invincible, sinon false
     */
    public boolean isInvincible() {
        return invincible;
    }

    /**
     * Définit le compteur d'invincibilité de l'entité.
     *
     * @param invincibleCounter le compteur d'invincibilité
     */
    public void setInvincibleCounter(int invincibleCounter) {
        this.invincibleCounter = invincibleCounter;
    }

    /**
     * Définit si l'entité est invincible.
     *
     * @param invincible true si l'entité est invincible, sinon false
     */
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    /**
     * Retourne la position Y par défaut de la hitbox solide de l'entité.
     *
     * @return la position Y par défaut de la hitbox solide
     */
    public int getSolidHitboxDefaultY() {
        return solidHitboxDefaultY;
    }

    /**
     * Retourne la position X par défaut de la hitbox solide de l'entité.
     *
     * @return la position X par défaut de la hitbox solide
     */
    public int getSolidHitboxDefaultX() {
        return solidHitboxDefaultX;
    }

    /**
     * Vérifie si la collision est activée pour l'entité.
     *
     * @return true si la collision est activée, sinon false
     */
    public boolean isCollisionOn() {
        return collisionOn;
    }

    /**
     * Retourne les dialogues de l'entité.
     *
     * @return les dialogues de l'entité
     */
    public String[] getDialogues() {
        return dialogues;
    }

    /**
     * Définit les dialogues de l'entité.
     *
     * @param dialogues les dialogues de l'entité
     */
    public void setDialogues(String[] dialogues) {
        this.dialogues = dialogues;
    }

    /**
     * Permet à l'entité de parler.
     */
    public void speak() {
        // Code de la méthode speak
    }

    /**
     * Définit l'action de l'entité.
     */
    public void setAction() {
        // Code de la méthode setAction
    }

    /**
     * Met à jour l'entité.
     */
    public void update() {
        // Code de la méthode update
    }

    /**
     * Retourne la position Y par défaut de la hitbox solide de l'entité.
     *
     * @return la position Y par défaut de la hitbox solide
     */
    public int getsolidHitboxDefaultY() {
        return solidHitboxDefaultY;
    }

    /**
     * Retourne la position X par défaut de la hitbox solide de l'entité.
     *
     * @return la position X par défaut de la hitbox solide
     */
    public int getsolidHitboxDefaultX() {
        return solidHitboxDefaultX;
    }

    /**
     * Retourne le compteur de sprites de l'entité.
     *
     * @return le compteur de sprites
     */
    public int getSpriteCounter() {
        return spriteCounter;
    }

    /**
     * Retourne le numéro du sprite de l'entité.
     *
     * @return le numéro du sprite
     */
    public int getSpriteNumber() {
        return spriteNumber;
    }

    /**
     * Obtient l'image du joueur.
     */
    public void getPlayerImage() {
        // Code de la méthode getPlayerImage
    }

    /**
     * Retourne la hitbox de l'entité.
     *
     * @return la hitbox de l'entité
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Définit la position X dans le monde de l'entité.
     *
     * @param worldX la position X dans le monde
     */
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    /**
     * Définit la position Y dans le monde de l'entité.
     *
     * @param worldY la position Y dans le monde
     */
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    /**
     * Définit la hitbox de l'entité.
     *
     * @param hitbox la hitbox de l'entité
     */
    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * Définit la direction de l'entité.
     *
     * @param direction la direction de l'entité
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Retourne la position X dans le monde de l'entité.
     *
     * @return la position X dans le monde
     */
    public int getWorldX() {
        return worldX;
    }

    /**
     * Retourne la position Y dans le monde de l'entité.
     *
     * @return la position Y dans le monde
     */
    public int getWorldY() {
        return worldY;
    }

    /**
     * Retourne la direction de l'entité.
     *
     * @return la direction de l'entité
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Définit le compteur de sprites de l'entité.
     *
     * @param spriteCounter le compteur de sprites
     */
    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    /**
     * Définit le numéro du sprite de l'entité.
     *
     * @param spriteNumber le numéro du sprite
     */
    public void setSpriteNumber(int spriteNumber) {
        this.spriteNumber = spriteNumber;
    }

    /**
     * Retourne le panneau de jeu associé à l'entité.
     *
     * @return le panneau de jeu
     */
    public GamePanel getGamePanel() {
        return gp;
    }

    /**
     * Retourne la vitesse de l'entité.
     *
     * @return la vitesse de l'entité
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Vérifie si la collision est activée pour l'entité.
     *
     * @return true si la collision est activée, sinon false
     */
    public boolean getCollisionOn() {
        return collisionOn;
    }

    /**
     * Définit la vitesse de l'entité.
     *
     * @param speed la vitesse de l'entité
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Définit si la collision est activée pour l'entité.
     *
     * @param collisionOn true si la collision est activée, sinon false
     */
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    /**
     * Définit la position X par défaut de la hitbox solide de l'entité.
     *
     * @param solidHitboxDefaultX la position X par défaut de la hitbox solide
     */
    public void setSolidHitboxDefaultX(int solidHitboxDefaultX) {
        this.solidHitboxDefaultX = solidHitboxDefaultX;
    }

    /**
     * Définit la position Y par défaut de la hitbox solide de l'entité.
     *
     * @param solidHitboxDefaultY la position Y par défaut de la hitbox solide
     */
    public void setSolidHitboxDefaultY(int solidHitboxDefaultY) {
        this.solidHitboxDefaultY = solidHitboxDefaultY;
    }

    /**
     * Retourne le nombre de points de vie maximum de l'entité.
     *
     * @return le nombre de points de vie maximum
     */
    public int getMaxLife() {
        return maxLife;
    }

    /**
     * Définit le nombre de points de vie maximum de l'entité.
     *
     * @param maxLife le nombre de points de vie maximum
     */
    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    /**
     * Retourne le nombre de points de vie actuel de l'entité.
     *
     * @return le nombre de points de vie actuel
     */
    public int getLife() {
        return life;
    }

    /**
     * Définit le nombre de points de vie actuel de l'entité.
     *
     * @param life le nombre de points de vie actuel
     */
    public void setLife(int life) {
        this.life = life;
    }


    /**
     * Déplace l'objet vers le haut.
     */
    public void moveUp() {
        this.setWorldY(this.getWorldY() - this.getSpeed());
    }

    /**
     * Déplace l'objet vers le bas.
     */
    public void moveDown() {
        this.setWorldY(this.getWorldY() + this.getSpeed());
    }

    /**
     * Déplace l'objet vers la gauche.
     */
    public void moveLeft() {
        this.setWorldX(this.getWorldX() - this.getSpeed());
    }

    /**
     * Déplace l'objet vers la droite.
     */
    public void moveRight() {
        this.setWorldX(this.getWorldX() + this.getSpeed());
    }

    /**
     * Retourne la coordonnée X de l'écran.
     *
     * @return La coordonnée X de l'écran.
     */
    public int getScreenX() {
        return screenX;
    }

    /**
     * Retourne la coordonnée Y de l'écran.
     *
     * @return La coordonnée Y de l'écran.
     */
    public int getScreenY() {
        return screenY;
    }

    /**
     * Définit la coordonnée X de l'écran.
     *
     * @param screenX La coordonnée X de l'écran.
     */
    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    /**
     * Définit la coordonnée Y de l'écran.
     *
     * @param screenY La coordonnée Y de l'écran.
     */
    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    /**
     * Dessine l'objet sur le contexte graphique spécifié.
     *
     * @param gc Le contexte graphique sur lequel dessiner.
     */
    public void draw(GraphicsContext gc) {
        Image image = null;
        int screenX = getWorldX() - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = getWorldY() - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (worldX + TILE_SIZE > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - TILE_SIZE < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + TILE_SIZE > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - TILE_SIZE < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {

            switch (this.getDirection()) {
                case "up":
                    if (this.isAttacking()) {
                        if (this.getSpriteNumber() == 1) {
                            image = attackUp1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = attackUp2;
                        }
                    } else {
                        if (this.getSpriteNumber() == 1) {
                            image = up1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = up2;
                        }
                    }
                    break;
                case "down":
                    if (this.isAttacking()) {
                        if (this.getSpriteNumber() == 1) {
                            image = attackDown1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = attackDown2;
                        }
                    } else {
                        if (this.getSpriteNumber() == 1) {
                            image = down1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = down2;
                        }
                    }
                    break;
                case "left":
                    if (this.isAttacking()) {
                        if (this.getSpriteNumber() == 1) {
                            image = attackLeft1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = attackLeft2;
                        }
                    } else {
                        if (this.getSpriteNumber() == 1) {
                            image = left1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = left2;
                        }
                    }
                    break;
                case "right":
                    if (this.isAttacking()) {
                        if (this.getSpriteNumber() == 1) {
                            image = attackRight1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = attackRight2;
                        }
                    } else {
                        if (this.getSpriteNumber() == 1) {
                            image = right1;
                        }
                        if (this.getSpriteNumber() == 2) {
                            image = right2;
                        }
                    }
                    break;
            }
        }
        if (this.isInvincible()) {
            gc.strokeText("Hit", screenX, screenY);
        }
        gc.drawImage(image, screenX, screenY, getGamePanel().TILE_SIZE, getGamePanel().TILE_SIZE);
    }


    /**
     * Définit l'image correspondant à la deuxième étape de mouvement vers la droite.
     *
     * @param right2 L'image correspondant à la deuxième étape de mouvement vers la droite.
     */
    public void setRight2(Image right2) {
        this.right2 = right2;
    }

    /**
     * Définit l'image correspondant à la première étape de mouvement vers la droite.
     *
     * @param right1 L'image correspondant à la première étape de mouvement vers la droite.
     */
    public void setRight1(Image right1) {
        this.right1 = right1;
    }

    /**
     * Définit l'image correspondant à la deuxième étape de mouvement vers la gauche.
     *
     * @param left2 L'image correspondant à la deuxième étape de mouvement vers la gauche.
     */
    public void setLeft2(Image left2) {
        this.left2 = left2;
    }

    /**
     * Définit l'image correspondant à la première étape de mouvement vers la gauche.
     *
     * @param left1 L'image correspondant à la première étape de mouvement vers la gauche.
     */
    public void setLeft1(Image left1) {
        this.left1 = left1;
    }

    /**
     * Définit l'image correspondant à la deuxième étape de mouvement vers le bas.
     *
     * @param down2 L'image correspondant à la deuxième étape de mouvement vers le bas.
     */
    public void setDown2(Image down2) {
        this.down2 = down2;
    }

    /**
     * Définit l'image correspondant à la première étape de mouvement vers le bas.
     *
     * @param down1 L'image correspondant à la première étape de mouvement vers le bas.
     */
    public void setDown1(Image down1) {
        this.down1 = down1;
    }

    /**
     * Définit l'image correspondant à la deuxième étape de mouvement vers le haut.
     *
     * @param up2 L'image correspondant à la deuxième étape de mouvement vers le haut.
     */
    public void setUp2(Image up2) {
        this.up2 = up2;
    }

    /**
     * Définit l'image correspondant à la première étape de mouvement vers le haut.
     *
     * @param up1 L'image correspondant à la première étape de mouvement vers le haut.
     */
    public void setUp1(Image up1) {
        this.up1 = up1;
    }

    /**
     * Retourne l'image correspondant à la première étape de mouvement vers le haut.
     *
     * @return L'image correspondant à la première étape de mouvement vers le haut.
     */
    public Image getUp1() {
        return up1;
    }

    /**
     * Retourne l'image correspondant à la deuxième étape de mouvement vers le haut.
     *
     * @return L'image correspondant à la deuxième étape de mouvement vers le haut.
     */
    public Image getUp2() {
        return up2;
    }

    /**
     * Retourne l'image correspondant à la première étape de mouvement vers le bas.
     *
     * @return L'image correspondant à la première étape de mouvement vers le bas.
     */
    public Image getDown1() {
        return down1;
    }

    /**
     * Retourne l'image correspondant à la deuxième étape de mouvement vers le bas.
     *
     * @return L'image correspondant à la deuxième étape de mouvement vers le bas.
     */
    public Image getDown2() {
        return down2;
    }

    /**
     * Renvoie l'image "right1".
     *
     * @return L'image "right1".
     */
    public Image getRight1() {
        return right1;
    }

    /**
     * Renvoie l'image "right2".
     *
     * @return L'image "right2".
     */
    public Image getRight2() {
        return right2;
    }

    /**
     * Renvoie l'image "left1".
     *
     * @return L'image "left1".
     */
    public Image getLeft1() {
        return left1;
    }

    /**
     * Renvoie l'image "left2".
     *
     * @return L'image "left2".
     */
    public Image getLeft2() {
        return left2;
    }

    /**
     * Définit l'image "attackRight1".
     *
     * @param attackRight1 L'image "attackRight1".
     */
    public void setAttackRight1(Image attackRight1) {
        this.attackRight1 = attackRight1;
    }

    /**
     * Définit l'image "attackLeft2".
     *
     * @param attackLeft2 L'image "attackLeft2".
     */
    public void setAttackLeft2(Image attackLeft2) {
        this.attackLeft2 = attackLeft2;
    }

    /**
     * Définit l'image "attackLeft1".
     *
     * @param attackLeft1 L'image "attackLeft1".
     */
    public void setAttackLeft1(Image attackLeft1) {
        this.attackLeft1 = attackLeft1;
    }

    /**
     * Définit l'image "attackDown2".
     *
     * @param attackDown2 L'image "attackDown2".
     */
    public void setAttackDown2(Image attackDown2) {
        this.attackDown2 = attackDown2;
    }

    /**
     * Définit l'image "attackDown1".
     *
     * @param attackDown1 L'image "attackDown1".
     */
    public void setAttackDown1(Image attackDown1) {
        this.attackDown1 = attackDown1;
    }

    /**
     * Définit l'image "attackUp2".
     *
     * @param attackUp2 L'image "attackUp2".
     */
    public void setAttackUp2(Image attackUp2) {
        this.attackUp2 = attackUp2;
    }

    /**
     * Définit l'image "attackUp1".
     *
     * @param attackUp1 L'image "attackUp1".
     */
    public void setAttackUp1(Image attackUp1) {
        this.attackUp1 = attackUp1;
    }

    /**
     * Définit l'image "attackRight2".
     *
     * @param attackRight2 L'image "attackRight2".
     */
    public void setAttackRight2(Image attackRight2) {
        this.attackRight2 = attackRight2;
    }

    /**
     * Renvoie l'image "attackUp1".
     *
     * @return L'image "attackUp1".
     */
    public Image getAttackUp1() {
        return attackUp1;
    }

    /**
     * Renvoie l'image "attackUp2".
     *
     * @return L'image "attackUp2".
     */
    public Image getAttackUp2() {
        return attackUp2;
    }

    /**
     * Renvoie l'image "attackDown1".
     *
     * @return L'image "attackDown1".
     */
    public Image getAttackDown1() {
        return attackDown1;
    }

    /**
     * Renvoie l'image "attackDown2".
     *
     * @return L'image "attackDown2".
     */
    public Image getAttackDown2() {
        return attackDown2;
    }

    /**
     * Renvoie l'image "attackLeft1".
     *
     * @return L'image "attackLeft1".
     */
    public Image getAttackLeft1() {
        return attackLeft1;
    }

    /**
     * Renvoie l'image "attackLeft2".
     *
     * @return L'image "attackLeft2".
     */
    public Image getAttackLeft2() {
        return attackLeft2;
    }

    /**
     * Renvoie l'image "attackRight1".
     *
     * @return L'image "attackRight1".
     */
    public Image getAttackRight1() {
        return attackRight1;
    }

    /**
     * Renvoie l'image "attackRight2".
     *
     * @return L'image "attackRight2".
     */
    public Image getAttackRight2() {
        return attackRight2;
    }

    public boolean isNoColideBonus() {
        return noColideBonus;
    }

    public void setNoColideBonus(boolean noColideBonus) {
        this.noColideBonus = noColideBonus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOneShot(boolean oneShot) {
        this.oneShot = oneShot;
    }

    public boolean isOneShot() {
        return oneShot;
    }
}
