package monster;

import com.sun.javafx.geom.Point2D;
import element.Element;
import element.ElementState;
import res.Pic;

/**
 * @className: HydroMage
 * @description: 水系法师
 * @date: 2021/12/1 18:33
 */
public class HydroMage extends Mage {
    public HydroMage(Point2D[] path, Monsters monsters) {
        super(path, monsters);
        image = Pic.HydroMage;
        currentState = ElementState.HydroAttached;
    }

    protected void move() {
        super.move();
        if (shieldHealth > 0 && currentState == ElementState.Empty) {
            currentState = ElementState.HydroAttached;
        }
    }

    @Override
    public void injury(int damage, Element element) {
        if (shieldHealth > 0) {
            double multiplier;
            switch (element) {
                case Cryo:
                    multiplier = 3;
                    currentState = ElementState.Freeze;
                    break;
                case Pyro:
                    multiplier = 1.5;
                    break;
                case Hydro:
                    multiplier = 0;
                    break;
                default:
                    multiplier = 0.4;
            }
            shieldHealth -= multiplier * damage;
//            System.out.println(this+"hydro shield damaged:" + multiplier * damage+" shieldHealth:"+shieldHealth);
        } else {
            super.injury(damage, element);
        }
    }
}
