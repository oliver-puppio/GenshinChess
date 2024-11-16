package game;


public class Player {
    private int health = 10;
    private int money = 300;

    private boolean paused;//暂停所有线程的标志

    public boolean isStopped() {
        return stopped;
    }

    private boolean stopped;//切断所有线程的标志

    public Player() {
        paused = false;
        stopped = true;
    }

    public boolean isPaused() {
        return paused;
    }

    public void shiftPaused() {
        this.paused = !paused;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public int getHealth() {
        return health;
    }

    public int getMoney() {
        return money;
    }

    /**
     * 让玩家对象受到伤害
     *
     * @param damage 伤害值
     */
    public void hurt(int damage) {
        health -= damage;
    }

    /**
     * 增加玩家金币总数
     *
     * @param money 增加金币数
     */
    public void addMoney(int money) {
        this.money += money;
    }

    /**
     * 购买防御塔
     *
     * @param price 防御塔价格
     */
    public void purchase(int price) {
        if (money >= price) {
            money -= price;
        }
    }

    /**
     * 用户对象是否还活着
     *
     * @return 返回是否还活着
     */
    public boolean isAlive() {
        return health > 0;
    }

}
