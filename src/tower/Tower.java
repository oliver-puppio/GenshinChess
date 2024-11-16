package tower;

import bullet.Bullet;
import com.sun.javafx.geom.Point2D;
import game.Player;
import monster.Monster;
import element.Element;
import res.Pic;

import javax.swing.*;
import java.awt.*;

import static bullet.Bullet.FLY_SPEED;

public abstract class Tower implements Runnable {
    protected int[] attackGap;/*攻击间隔*/
    protected int[] explosionArea;/*攻击范围*/
    protected int rank = 0;/*当前等级*/
    protected Image[] images;/*图片*/
    protected int attackImgIndex = 0;/*表示攻击动画处于第i张*/
    protected Bullet bullet;/*绑定的子弹对象*/
    protected Element element;/*元素类型*/

    protected Monster[] monsters;/*对生存怪物群的引用*/

    protected Dimension imgSize;/*图片大小*/
    protected Point2D centerPosition;/*中间位置*/
    protected Point2D imgPosition;/*左上角位置*/

    public static final int[] price = new int[]{60, 110, 150};/*购买价格*/
    public static final int[] cost = new int[]{50, 100, 130};/*出售折价*/

    protected boolean killed;/*塔线程被回收的标志*/
    protected Player lock;

    /**
     * 创建塔，引用游戏内的monsters，初始化塔的左上角position及中心位置、塔的等级初始为0
     *
     * @param position 塔的左上角位置
     * @param monsters 对游戏内aliveMonsters的引用
     */
    public Tower(Point2D position, Monster[] monsters, Player lock) {
        this.lock = lock;
        this.monsters = monsters;
        this.imgPosition = position;

        this.imgSize = new Dimension(100, 100);
        this.centerPosition = new Point2D(position.x + imgSize.width / 2, position.y + imgSize.height / 2);

        this.explosionArea = new int[]{250, 265, 285};

        killed = false;
    }

    /**
     * 判断能否升级，若是则升级，并且返回真，否则返回假
     */
    public void levelUp() {
        if (rank < 2) {
            bullet.setRank(++rank);
        }
    }


    public Bullet getBullet() {
        return bullet;
    }


    /**
     * 绘制塔：根据等级rank绘制塔的样式、根据attackImgIndex绘制对应的攻击动作图片
     *
     * @param g     沿用调用方法的g
     * @param panel 观察者
     */
    public void paint(Graphics g, JPanel panel) {
        if (setTarget() != null) {
            g.drawImage(Pic.Tower(element)[attackImgIndex + rank], (int) imgPosition.x, (int) this.imgPosition.y, imgSize.width, imgSize.height, panel);
        }
        g.drawImage(Pic.Tower(element)[rank], (int) this.imgPosition.x, (int) this.imgPosition.y, imgSize.width, imgSize.height, panel);
    }

    /**
     * 寻找攻击目标，若找到则返回目标的位置，否则返回空
     *
     * @return 目标位置，null表示目标不存在
     */
    public Point2D setTarget() {
        if (bullet.getFlyState() != -1) {
            return null;
        }
        //遍历aliveMonsters中每一个对象
        for (Monster mon : monsters)
            if (mon != null && mon.alive() && centerPosition.distance(mon.position) <= explosionArea[rank])
                return new Point2D(mon.position.x, mon.position.y);
        return null;
    }

    /**
     * 负责检测有没有靠近的怪物，若有则启动炮弹，发动进攻
     */
    public void run() {
        while (!killed) {
            Point2D target = setTarget();
            if (target != null) {
                //发射动作动画
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

                for (int i = 0; i < images.length - 3; i++) { //每次循环是一帧
                    attackImgIndex = i;
                    try {
                        Thread.sleep(100);//进攻动作图片的切换间隔0.1s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                attackImgIndex = 0;//回到第一帧

                bullet.setFlyState(0);
                double cos = (double) (target.x - centerPosition.x) / centerPosition.distance(target);
                double sin = (double) (target.y - centerPosition.y) / centerPosition.distance(target);
                while (bullet.position.distance(target) > FLY_SPEED) {
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
                    bullet.position.x += FLY_SPEED * cos;
                    bullet.position.y += FLY_SPEED * sin;
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                bullet.setFlyState(1);
                bullet.explode();
                bullet.reset();

                try {
                    //每完整攻击一次后，间隔一段时间再次攻击
                    Thread.sleep((long) attackGap[rank] * 80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //休息间隔时间检测一次
            try {
                Thread.sleep(attackGap[rank]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("Tower " + this + " run out==");
    }

    public void kill() {
        killed = true;
    }

    /**
     * 获取当前防御塔成本
     *
     * @return 回收价格
     */
    public int getCurrentCost() {
        return cost[rank];
    }

    /**
     * 获取当前防御塔价格
     *
     * @return 当前等级的价格
     */
    public int getCurrentPrice() {
        return price[rank];
    }

    public Element getElement() {
        return element;
    }

    public int getRank() {
        return rank;
    }

    public int getNowExplosionArea() {
        return explosionArea[this.getRank()];
    }

    public Point2D getCenterPosition() {
        return centerPosition;
    }
}
