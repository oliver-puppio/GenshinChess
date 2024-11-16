package monster;

import com.sun.javafx.geom.Point2D;
import element.Element;

import javax.swing.*;
import java.awt.*;


public abstract class Mage extends Monster {
    protected final int fullHealth = 150;/*最大生命值*/
    protected final int fullShieldHealth = 80;/*最大生命值*/
    protected int shieldHealth;/*护盾生命值*/

    public Mage(Point2D[] path, Monsters monsters) {
        super(path, monsters);
        step = 3;
        health = fullHealth;
        attack = 4;
        reward = 90;
        shieldHealth = fullShieldHealth;
    }

    @Override
    public void paint(Graphics g, JPanel jPanel) {
        if (shieldHealth > 0) {
            g.drawImage(image[direction+8], (int) position.x - 10, (int) position.y - 10, jPanel);//播放第1张图片
            g.setColor(Color.cyan);
            g.drawRect((int) position.x - 11, (int) position.y - 15, 35, 7);//显示血条边框
            g.setColor(Color.red);
            g.fillRect((int) position.x - 10, (int) position.y - 14, (35 * health / fullHealth()), 5);//显示血条
            g.setColor(Color.blue);
            g.fillRect((int) position.x - 10, (int) position.y - 14, (35 * shieldHealth / fullShieldHealth()), 5);//显示护盾
        }else {
            super.paint(g, jPanel);
        }
    }

    @Override
    public int fullHealth() {
        return fullHealth;
    }

    @Override
    public int fullShieldHealth() {
        return fullShieldHealth;
    }

    @Override
    public void injury(int damage, Element element) {
        super.injury(damage, element);
    }

}
