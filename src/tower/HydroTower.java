package tower;

import bullet.Bullet;
import com.sun.javafx.geom.Point2D;
import game.Player;
import monster.Monster;
import element.Element;
import res.Pic;

public class HydroTower extends Tower {
    public HydroTower(Point2D position, Monster[] monsters, Player lock) {
        super(position, monsters, lock);
        this.attackGap = new int[]{8, 5, 2};
        element = Element.Hydro;
        images = Pic.HydroTower;
        bullet = new Bullet(element, monsters, centerPosition);
        attackImgIndex = 0;
    }
}
