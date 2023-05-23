package abstraction.monsters;

public interface Monster {
    public void getImage();
    public void setAction();
    public int getActionLockCounter();
    public void setActionLockCounter(int actionLockCounter);
    public void update();
}
