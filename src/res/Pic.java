package res;

import element.Element;

import java.awt.*;

public class Pic {
    public static final int OUT = 0;
    public static final int IN = 1;

    public static final int MAIN = 0;
    public static final int MAP1 = 1;
    public static final int MAP2 = 2;

    /**
     * 按顺序：main、map1、map2
     */
    public static Image[] background = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/background_main_startPanel.png"),
            Toolkit.getDefaultToolkit().getImage("pic/background_map1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/background_map2.png")
    };

    public static Image[] helpButtonPic = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\helpOff.png"),
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\helpOn.png")
    };

    public static Image[] startGamePic=new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\startGameOff.png"),
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\startGameOn.png"),

    };

    public static Image[] missionButtonPic = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\missionOff.png"),
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\missionOn.png")
    };

    /**
     * 顺序：第1、2、3页
     */
    public static Image[] helpBgPic = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\help1.png"),
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\help2.png"),
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\help3.png")
    };

    public static Image[] backPic = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\backOff.png"),
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\backOn.png")
    };

    public static Image[] lastPagePic = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/panel/lastPageOff.png"),
            Toolkit.getDefaultToolkit().getImage("pic/panel/lastPageOn.png")
    };

    public static Image[] nextPagePic = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/panel/nextPageOff.png"),
            Toolkit.getDefaultToolkit().getImage("pic/panel/nextPageOn.png")
    };

    public static Image[] startPic = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\startOff.png"),
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\startOn.png")
    };

    public static Image[] pausePic = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\pauseOff.png"),
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\pauseOn.png")
    };

    public static Image[] restartPic = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\reStartOff.png"),
            Toolkit.getDefaultToolkit().getImage("pic\\panel\\reStartOn.png")
    };

    /**
     * 空地图片
     */
    public static Image[] emptyAreaPic = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/nullTower.png"),
            Toolkit.getDefaultToolkit().getImage("pic/selectedNullTower.png")
    };

    /**
     * 火防御塔图片，最后三张是攻击动画图
     */
    public static Image[] PyroTower = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/towers/pyro/1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/towers/pyro/2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/towers/pyro/3.png"),
    };

    /**
     * 水防御塔图片，最后三张是攻击动画图
     */
    public static Image[] HydroTower = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/towers/hydro/1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/towers/hydro/2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/towers/hydro/3.png")
    };

    /**
     * 冰防御塔图片,最后三张是攻击动画图
     */
    public static Image[] CryoTower = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/towers/cryo/1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/towers/cryo/2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/towers/cryo/3.png"),
    };

    /**
     * 获取特定类型的防御塔图片集
     *
     * @param element 元素类型
     * @return 返回塔图片集
     */
    public static Image[] Tower(Element element) {
        switch (element) {
            case Pyro:
                return PyroTower;
            case Hydro:
                return HydroTower;
            default:
                return CryoTower;
        }
    }

    /**
     * 火子弹图片
     */
    public static Image[] PyroBullet = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/bullets/pyroFly.png"),
            Toolkit.getDefaultToolkit().getImage("pic/bullets/pyroBloom.png"),
    };

    /**
     * 水子弹图片
     */
    public static Image[] HydroBullet = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/bullets/hydroFly.png"),
            Toolkit.getDefaultToolkit().getImage("pic/bullets/hydroBloom.png"),
    };

    /**
     * 冰子弹图片
     */
    public static Image[] CryoBullet = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/bullets/cryoFly.png"),
            Toolkit.getDefaultToolkit().getImage("pic/bullets/cryoBloom.png"),
    };

    /**
     * 获取特定类型的子弹图片集
     *
     * @param element 元素类型
     * @return 返回子弹图片集
     */
    public static Image[] Bullet(Element element) {
        switch (element) {
            case Pyro:
                return PyroBullet;
            case Hydro:
                return HydroBullet;
            default:
                return CryoBullet;
        }
    }

    public static Image[] NormalHilichurl = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/enemy/normalhilichurl/f1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/normalhilichurl/l1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/normalhilichurl/b1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/normalhilichurl/r1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/normalhilichurl/f2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/normalhilichurl/l2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/normalhilichurl/b2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/normalhilichurl/r2.png"),
    };

    public static Image[] ShieldHilichurl = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/enemy/shieldhilichurl/f1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/shieldhilichurl/l1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/shieldhilichurl/b1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/shieldhilichurl/r1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/shieldhilichurl/f2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/shieldhilichurl/l2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/shieldhilichurl/b2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/shieldhilichurl/r2.png"),
    };

    public static Image[] SamaHilichurl = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/enemy/samahilichurl/f1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/samahilichurl/l1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/samahilichurl/b1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/samahilichurl/r1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/samahilichurl/f2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/samahilichurl/l2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/samahilichurl/b2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/samahilichurl/r2.png"),
    };

    public static Image[] PyroMage = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/f1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/l1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/b1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/r1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/f2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/l2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/b2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/r2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/sf1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/sl1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/sb1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/sr1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/sf2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/sl2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/sb2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/pyromage/sr2.png"),
    };

    public static Image[] HydroMage = new Image[]{
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/f1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/l1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/b1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/r1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/f2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/l2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/b2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/r2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/sf1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/sl1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/sb1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/sr1.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/sf2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/sl2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/sb2.png"),
            Toolkit.getDefaultToolkit().getImage("pic/enemy/hydromage/sr2.png"),
    };
}
