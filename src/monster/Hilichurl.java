package monster;

import com.sun.javafx.geom.Point2D;

public abstract class Hilichurl extends Monster{
    public Hilichurl(Point2D[] path, Monsters monsters) {
        super(path, monsters);
        step=2;
        shieldHealth = 0;
    }

    @Override
    protected void move() {
        super.move();
    }
}
