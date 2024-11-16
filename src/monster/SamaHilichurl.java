package monster;

import com.sun.javafx.geom.Point2D;
import element.Element;
import res.Pic;

/**
 * 牧师丘丘人。会在自身附着的元素属性正常消散时给周围的怪物回复牧师当前生命值的30%
 */
public class SamaHilichurl extends Hilichurl {
    private final int fullHealth = 120;/*最大生命值*/
    private final int healArea = 80;/*治愈范围*/

    public SamaHilichurl(Point2D[] path, Monsters monsters) {
        super(path, monsters);
        image = Pic.SamaHilichurl;
        health = fullHealth;
        attack = 2;
        reward = 50;
    }

    @Override
    public int fullHealth() {
        return fullHealth;
    }

    @Override
    public int fullShieldHealth() {
        return 0;
    }

    @Override
    protected void removeElementInLoop() {
        if ((flagInElementShift++) == 25) {
            currentState = currentState.reaction(Element.Time);
            healMonsters();
            flagInElementShift = 0;
        }
    }

    private void healMonsters() {
        for (Monster i : monsters.getMonsters()) {
            if (i != null && i.alive() && i.position.distance(position) <= healArea) {
                i.heal((int) (0.3 * health));
            }
        }
    }
}
