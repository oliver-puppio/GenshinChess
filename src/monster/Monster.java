package monster;

import com.sun.javafx.geom.Point2D;
import game.Player;
import element.Element;
import element.ElementState;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;


public abstract class Monster implements Runnable {
    protected int health;/*生命值*/
    protected int shieldHealth;/*护盾值*/
    protected int attack;/*攻击力*/
    protected int reward;/*击杀奖励*/
    public Image[] image;/*怪物图片，在最终类构造函数中赋值*/

    protected int step; /*步长,同类怪相同*/

    protected Point2D[] path;/*怪物行进路线*/

    protected ElementState currentState = ElementState.Empty;/*当前附着元素*/
    protected boolean killed; /*怪物是否被杀死*/
    public int direction;/*四种方向*/
    protected int loc;/*当前目的位置下标*/
    public Point2D position;/*当前怪物位置*/

    protected int flagInElementShift;/*元素附着时间标记，当等于25时解除怪物附着元素*/
    protected boolean flagInAnimation;/*动画效果切换标记，用于丰富怪物动画*/

    protected Monsters monsters;/*对所属群体的引用*/
    protected Player lock;/*对玩家对象的引用*/

    protected static final int TIMER = 100;/*频率*/


    public Monster(Point2D[] path, Monsters monsters) {
        this.path = path;
        this.monsters = monsters;
        position = new Point2D(path[0].x, path[0].y);
        direction = 0;
        loc = 1;
        flagInElementShift = 0;
        flagInAnimation = false;
        killed = false;
        lock = monsters.player;
    }

    /**
     * 怪物移动规则,方向0、1、2、3分别表示下、左、上、右
     */
    protected void move() {
        /*若为冻结状态，则直接返回*/
        if (currentState == ElementState.Freeze || killed) {
            return;
        }
        /*若当前位置距离目标位置小于步长，则将目标位置切换至路径的下一段*/
        if (position.distance(path[loc]) < step) {
            if (loc == path.length - 1) {//若到达终点，则计入玩家伤害
                monsters.kill(this);
                return;
            }
            loc++;
        }

        /*更新怪物位置*/
        float dx = path[loc].x - position.x;
        float dy = path[loc].y - position.y;
        if (dx != 0) {
            position.x += (dx > 0) ? step : -step;
        }
        if (dy != 0) {
            position.y += (dy > 0) ? step : -step;
        }

        /*更新方向*/
        if (dx > 0.5 * step) {
            direction = 3;
        } else if (dx < -0.5 * step) {
            direction = 1;
        } else {
            direction = dy < 0 ? 2 : 0;
        }
        direction += flagInAnimation ? 4 : 0;//若flagInAnimation为真，则切换同一方向的另一张图片
        flagInAnimation = !flagInAnimation;
    }

    /**
     * 画怪物的图片、血条,若有护盾则画护盾
     *
     * @param g      沿用调用语句中的g
     * @param jPanel 绘制位置
     */
    public void paint(Graphics g, JPanel jPanel) {
        g.drawImage(image[direction], (int) position.x - 10, (int) position.y - 10, jPanel);//播放第1张图片
        g.setColor(Color.cyan);
        g.drawRect((int) position.x - 11, (int) position.y - 15, 35, 7);//显示血条边框
        g.setColor(Color.red);
        g.fillRect((int) position.x - 10, (int) position.y - 14, (35 * health / fullHealth()), 5);//显示血条

    }

    /**
     * 怪物线程，当怪物活着时进行位置移动,并且更新元素状态
     */
    public void run() {
        while (!killed) {
            synchronized (lock) {
                while (lock.isPaused()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (lock.isStopped()) break;
            }
            move();
            removeElementInLoop();
            try {
                sleep(TIMER);
            } catch (Exception e) {
            }
        }
        System.out.println(this + " is dead");
    }

    /**
     * 治疗
     */
    public void heal(int remedy) {
        health += remedy;
        if (health > fullHealth())
            health = fullHealth();
    }

    /**
     * 怪物受到元素伤害，同时怪物的元素状态会变成三种元素附着、冻结、空其中一种。
     * 若元素攻击造成怪物的元素状态发生变化，则会刷新元素状态的附着时间。
     * 若怪物死亡，则会被移出生存者列表
     *
     * @param damage  受到的伤害值
     * @param element 攻击的元素类型
     */
    public void injury(int damage, Element element) {
        ElementState nextState = currentState.reaction(element);//记录下一状态的元素状态
        if (nextState != currentState) {
            flagInElementShift = 0;//重置附着时间
        }
        currentState = nextState;
        health -= damage * currentState.multiplier();//造成元素伤害
        /*当元素状态为蒸发和融化时，状态会瞬间发生转移*/
        if (currentState == ElementState.Evaporation || currentState == ElementState.Melt) {
            currentState = currentState.reaction(Element.Time);
        }
        /*若怪物生命值小于等于0，则会被判定死亡*/
        if (health <= 0) {
            killed = true;
            monsters.kill(this);
        }
    }

    /**
     * 自杀
     */
    public void kill() {
        killed = true;
    }

    /**
     * 用于刷新怪物附着元素（水、火、冰、冻结）状态。每次被调用时，会使flag值变化一次。每调用25次会解除一次怪物身上附着的元素
     */
    protected void removeElementInLoop() {
        if ((flagInElementShift++) == 25) {
            currentState = currentState.reaction(Element.Time);
            flagInElementShift = 0;
        }
    }

    /**
     * 返回该怪物的总血量
     *
     * @return 总血量
     */
    public abstract int fullHealth();

    /**
     * 返回怪物总护盾值
     *
     * @return 总护盾值
     */
    public abstract int fullShieldHealth();


    public int getReward() {
        return reward;
    }

    public int getAttack() {
        return attack;
    }

    public boolean alive() {
        return !killed && health > 0;
    }

    public static Monster getInstance(int i, Point2D[] path, Monsters monsters) {
        switch (i) {
            case 0:
                return new NormalHilichurl(path, monsters);
            case 1:
                return new ShieldHilichurl(path, monsters);
            case 2:
                return new SamaHilichurl(path, monsters);
            case 3:
                return new PyroMage(path, monsters);
            default:
                return new HydroMage(path, monsters);
        }
    }


}
