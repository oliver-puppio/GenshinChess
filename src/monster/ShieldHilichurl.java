package monster;

import com.sun.javafx.geom.Point2D;
import element.Element;
import element.ElementState;
import res.Pic;

import javax.swing.*;
import java.awt.*;

public class ShieldHilichurl extends Hilichurl {
    private final int fullHealth = 130;/*最大生命值*/
    private final int fullShieldHealth = 100;/*最大护盾值*/

    public ShieldHilichurl(Point2D[] path, Monsters monsters) {
        super(path, monsters);
        image = Pic.ShieldHilichurl;
        health = fullHealth;
        shieldHealth = fullShieldHealth;
        attack = 3;
        reward = 80;
    }

    @Override
    public void paint(Graphics g, JPanel jPanel) {
        super.paint(g, jPanel);
        if (shieldHealth > 0) {
            g.setColor(Color.blue);
            g.fillRect((int) position.x - 10, (int) position.y - 14, (35 * shieldHealth / fullShieldHealth()), 5);//显示护盾
        }
    }

    @Override
    public void injury(int damage, Element element) {
        if (shieldHealth > 0) {
            currentState = currentState.reaction(element);
            shieldHealth -= 0.3 * damage;
        } else
            super.injury(damage, element);
    }

    @Override
    protected void move() {
        super.move();
        if (currentState == ElementState.PyroAttached) {
            shieldHealth -= 2;
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

}
