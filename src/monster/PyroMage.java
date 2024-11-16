package monster;

import com.sun.javafx.geom.Point2D;
import element.Element;
import element.ElementState;
import res.Pic;

/**
 * @className: PyroMage
 * @description: 火系法师
 * @date: 2021/12/1 18:32
 */
public class PyroMage extends Mage {
    public PyroMage(Point2D[] path, Monsters monsters) {
        super(path, monsters);
        image = Pic.PyroMage;
        currentState = ElementState.PyroAttached;
    }

    protected void move() {
        super.move();
        if (shieldHealth > 0 && currentState == ElementState.Empty) {
            currentState = ElementState.PyroAttached;
        }
    }

    @Override
    public void injury(int damage, Element element) {
        if (shieldHealth > 0) {
            double multiplier;
            switch (element) {
                case Hydro:
                    multiplier = 3;
                    break;
                case Cryo:
                    multiplier = 1.5;
                    break;
                case Pyro:
                    multiplier = 0;
                    break;
                default:
                    multiplier = 0.4;
            }
            shieldHealth -= multiplier * damage;
//            System.out.println(this+"pyro shield damaged:" + multiplier * damage+" shieldHealth:"+shieldHealth);

        } else {
            super.injury(damage, element);
        }
    }
}
