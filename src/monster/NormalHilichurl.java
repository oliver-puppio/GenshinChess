package monster;

import com.sun.javafx.geom.Point2D;
import res.Pic;

public class NormalHilichurl extends Hilichurl {
    private final int fullHealth = 90;/*最大生命值*/

    public NormalHilichurl(Point2D[] path, Monsters monsters) {
        super(path, monsters);
        image = Pic.NormalHilichurl;
        health = fullHealth;
        attack = 1;
        reward = 35;
    }

    @Override
    public int fullHealth() {
        return fullHealth;
    }

    @Override
    public int fullShieldHealth() {
        return 0;
    }
}
