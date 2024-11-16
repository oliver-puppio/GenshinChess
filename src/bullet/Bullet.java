package bullet;

import com.sun.javafx.geom.Point2D;
import monster.Monster;
import element.Element;
import res.Pic;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class Bullet {
    protected int[] attack;/*攻击力*/
    protected int[] attackRange;/*炮弹落点伤害范围*/
    protected int rank;/*炮弹等级*/
    protected Element element;/*元素属性*/
    protected Point2D initialPosition;/*子弹初始位置*/

    protected Monster[] monsters;/*保留对怪物群体的引用*/
    public Point2D position;/*位置信息*/
    protected int flyState;/*飞行状态，-1表示没有敌人，0表示在飞，1表示落地*/
    protected Image[] images;/*运动过程中需要的图像*/
    public static final int FLY_SPEED = 10;/*子弹飞行速度,每0.1秒前进的像素点数*/


    public Bullet(Element element, Monster[] monsters, Point2D position) {
        this.element = element;
        this.images = Pic.Bullet(element);
        this.monsters = monsters;
        initialPosition = new Point2D(position.x, position.y);
        this.position = new Point2D(initialPosition.x, initialPosition.y);
        attack = new int[]{10, 15, 20};
        attackRange = new int[]{30, 45, 55};
        rank = 0;
        flyState = -1;
    }


    /**
     * 绘制当前子弹轨迹
     *
     * @param graphics 沿用调用方法的参数
     * @param panel    监听位置
     */
    public void paint(Graphics graphics, JPanel panel) {
        if (flyState == -1) {
            return;
        }
        graphics.drawImage(Pic.Bullet(element)[flyState], (int) position.x, (int) position.y, 30, 30, panel);//运动中的图片
    }


    /**
     * 炮弹发动攻击,并将状态设置为初始状态
     */
    public void explode() {
        if (flyState != 1) {//如果子弹处于攻击状态，
            return;
        }
        flyState = 1;
        //到达目标，对范围内怪物发起元素攻击
        for (Monster i : monsters) {
            if (i != null && i.alive() && hitTarget(i)) {
                i.injury(attack[rank], element);
            }
        }
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 子弹是否能命中目标
     *
     * @param m 目标怪物
     * @return 目标是否在炮弹攻击范围内
     */
    private boolean hitTarget(Monster m) {
        return position.distance(m.position) <= attackRange[rank];
    }

    public void reset(){
        flyState = -1;
        position = new Point2D(initialPosition.x, initialPosition.y);
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getFlyState() {
        return flyState;
    }

    public void setFlyState(int flyState) {
        this.flyState = flyState;
    }
}
