package tower;

import bullet.Bullet;
import com.sun.javafx.geom.Point2D;
import game.Player;
import monster.Monster;
import element.Element;
import res.Pic;

public class CryoTower extends Tower {

    public CryoTower(Point2D position, Monster[] monsters, Player lock) {
        super(position, monsters, lock);
        this.attackGap = new int[]{8, 5, 2};
        element = Element.Cryo;
        images = Pic.CryoTower;
        bullet = new Bullet(element, monsters, centerPosition);
        attackImgIndex = 0;
    }
}
