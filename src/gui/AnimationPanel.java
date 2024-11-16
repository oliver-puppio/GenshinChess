package gui;

import game.Player;
import monster.Monsters;
import res.Map;
import res.Pic;
import tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * @className: AnimationPanel
 * @description: 动画显示面板
 * @date: 2021/12/4 15:38
 */
public class AnimationPanel extends JPanel implements Runnable {
    private static final long serialVersionUID = 5119643599029184882L;
    private final Dimension groundSize;/*防御塔大小*/
    private final Map map;
    private final Tower[] towers;
    private final Monsters monsters;
    private final Player player;

    public AnimationPanel(Map map, Tower[] towers, Monsters monsters, Player player, BuildingPanel buildPanel) {
        this.map = map;
        this.towers = towers;
        this.monsters = monsters;
        this.player = player;
        groundSize = new Dimension(88, 88);

        setLayout(null);
        setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!player.isPaused()&&!player.isStopped()) {//如果被暂停，则不能触发点击事件
                    //防御塔响应的鼠标点击事件：遍历所有防御塔（靠鼠标坐标）
                    for (int i = 0; i < towers.length; i++) {
                        if (
                                e.getX() >= map.plants[i].x && e.getX() <= map.plants[i].x + groundSize.width
                                        && e.getY() >= map.plants[i].y && e.getY() <= map.plants[i].y + groundSize.height
                        ) {
                            buildPanel.show(i, map.plants[i]);  //传入这是点击的第i个塔（空地）
                            System.out.println("buildPanel.show(i, map.plants[i]);i=" + i);
                        }
                    }
                }
            }
        });
    }


    /**
     * 显示游戏主要内容：防御塔、怪物、地图
     *
     * @param g 画图对象
     */
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(map.background, 0, 0, 1182, 703, this);

        /*绘制玩家信息*/
        g.setColor(Color.black);
        g.fillRect(50, 5, 120, 90);
        g.setFont(new Font("宋", Font.BOLD, 15));
        g.setColor(new Color(251, 251, 21, 250));
        g.drawString("金币：" + player.getMoney(), 60, 23);
        g.setColor(Color.RED);
        g.drawString("生命：" + player.getHealth(), 60, 53);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("剩余怪物：" + (map.num - monsters.deadN), 60, 83);

        /*绘制防御塔和怪物*/
        for (int i = 0; i < towers.length; i++) {
            if (towers[i] != null) {
                towers[i].paint(g, this);
                towers[i].getBullet().paint(g, this);
            } else {  //这里表示towers[i]==null，即没有被建造。和isBuilt[i]==false相同（？
                g.drawImage(Pic.emptyAreaPic[Pic.OUT], (int) map.plants[i].x, (int) map.plants[i].y, 100, 100, this);
            }
        }
        monsters.paint(g, this);

    }

    @Override
    public void run() {
        while (true) {
            synchronized (player) {
                if (player.isPaused()) {
                    try {
                        player.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (player.isStopped()) break;
            }

            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("animation " + this.hashCode() + " run out==");

    }
}
