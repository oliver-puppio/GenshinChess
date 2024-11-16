package monster;

import com.sun.javafx.geom.Point2D;
import game.Player;
import res.Map;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * 怪物管理类，负责管理地图怪物静态数据、记录怪物生存状态
 * 基本思想：构造函数中，按照地图怪物数量，开辟一些地址空间,并全部赋空；
 * 线程负责刷怪。线程启动后，按照时间顺序和地图规则，创造怪物实例。
 * 具体操作是遍历monsters，然后动态给该引用创造对象。
 * <p>
 * 怪物生存管理，怪物具有属性killed，创建时为false，若遍历的时候发现该对象killed属性为true或者该对象为空，则该对象未产生或已死亡
 */
public class Monsters extends Thread {
    private int[][] rule;/*游戏规则，用二维数组来表示*/
    public Player player;
    private Point2D[] path1;/*路径1*/
    private Point2D[] path2;/*路径2*/
    private Monster[] monsters;/*存储所有怪物*/

    public static volatile boolean stopped; /*标记是否stop，线程会否结束*/

    public int deadN = 0;


    /**
     * 根据地图类型构造刷怪器对象
     *
     * @param map    使用的地图
     * @param player 添加玩家对象的引用
     */
    public Monsters(Map map, Player player) {
        stopped = false;
        monsters = new Monster[map.num];
        Arrays.fill(monsters, null);

        this.player = player;
        rule = map.rule;
        path1 = map.path1;
        path2 = map.path2;
    }


    /**
     * 该线程负责根据地图类型定时刷怪
     */
    @Override
    public void run() {
        int loc = 0;
        int rows = rule.length;//刷怪的波数
        int cols = 5;//怪物种类数
        if (!stopped) {
//            System.out.println(this + "Monsters are being created:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    for (int k = 0; k < rule[i][j]; k++) {//遍历第i波第j个怪的总数
                        synchronized (player) {
                            while (player.isPaused()) {
                                try {
                                    player.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (player.isStopped()) break;
                        }
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        monsters[loc] = Monster.getInstance(j, new Random().nextBoolean() ? path1 : path2, this);
                        new Thread(monsters[loc++]).start();
                    }
                    if (player.isStopped()) break;
                }
                try {
                    sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (player.isStopped()) break;
            }
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println( this + "刷怪结束");
    }

    /**
     * 画出每个存活怪物的图片、血条
     *
     * @param g      沿用调用语句中的g
     * @param jPanel 观察者
     */
    public void paint(Graphics g, JPanel jPanel) {
        //反向遍历，先出现的怪会被优先显示
        for (int i = monsters.length - 1; i >= 0; i--) {
            Monster temp = monsters[i];
            if (temp != null && temp.alive())
                temp.paint(g, jPanel);
        }
    }

    /**
     * 从生存怪物列表中移除该怪物，并让玩家收到金币或者扣血
     *
     * @param m 被击杀的怪物
     */
    public void kill(Monster m) {
        if (m.alive()) {//怪物存活到终点，需要手动kill
            m.kill();
            player.hurt(m.getAttack());
//            System.out.println(player.getHealth());
        } else {
            player.addMoney(m.getReward());
        }
        deadN++;
    }

    public Monster[] getMonsters() {
        return monsters;
    }
}
