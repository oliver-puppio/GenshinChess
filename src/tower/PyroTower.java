package tower;

import bullet.Bullet;
import com.sun.javafx.geom.Point2D;
import game.Player;
import monster.Monster;
import element.Element;
import res.Pic;

public class PyroTower extends Tower {
    public PyroTower(Point2D position, Monster[] monsters, Player lock) {
        super(position, monsters, lock);
        this.attackGap = new int[]{8, 5, 2};
        element = Element.Pyro;
        images = Pic.PyroTower;
        bullet = new Bullet(element, monsters, centerPosition);
        attackImgIndex = 0;
    }
}
