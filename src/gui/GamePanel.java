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

/**
 * 游戏界面，包括顶部导航栏、动画面板、底部建造栏
 */
public class GamePanel extends JPanel implements Runnable {
    private static final long serialVersionUID = -4855036208991755326L;
    /*界面相关的实例及基本属性*/
    protected Map map;
    protected Player player;       /*使用player调用*/
    protected Tower[] towers;      /*每块地的塔*/
    protected Monsters monsters;   /*对怪物类的引用*/
    protected boolean launched;    /*游戏进行中的标志*/

    /*游戏界面相关的控件*/
    public JPanel topPanel;
    public BuildingPanel buildPanel;
    private AnimationPanel animationPanel;
    private JLabel start, restart, back;


    /**
     * 根据 map 创建游戏界面 GamePanel 实例
     *
     * @param map 根据map确定GamePanel的背景、塔的位置、怪物群
     */
    public GamePanel(Map map) {
        setLayout(new BorderLayout());
        setVisible(false);   //创建实例时不可见
        setSize(1200, 750);
        setBackground(new Color(243, 214, 214));

        /*创建实体对象*/
        player = new Player();
        launched = false;/*标记本局游戏暂未开始*/
        this.map = map;
        towers = new Tower[map.towerN];
        monsters = new Monsters(map, player);

        /*创建游戏界面主要面板*/
        topPanel = new JPanel();
        buildPanel = new BuildingPanel(towers, monsters.getMonsters(), player);
        initializeTopPanel();

        add(topPanel, BorderLayout.NORTH);
        add(buildPanel, BorderLayout.SOUTH);

        animationPanel = new AnimationPanel(map, towers, monsters, player, buildPanel);
        add(animationPanel, BorderLayout.CENTER);
    }

    private void initializeTopPanel() {
        /*设置顶部导航栏格式*/
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(0, 60));
        topPanel.setBackground(new Color(225, 222, 194));
        topPanel.setVisible(true);

        /*创建按钮实例并添加*/
        start = new JLabel(new ImageIcon(Pic.startPic[Pic.OUT]));
        restart = new JLabel(new ImageIcon(Pic.restartPic[Pic.OUT]));
        back = new JLabel(new ImageIcon(Pic.backPic[Pic.OUT]));
        topPanel.add(start);
        topPanel.add(restart);
        topPanel.add(back);

        /*start按钮兼职开始游戏、暂停、继续*/
        start.setBounds(300, 5, 50, 50);
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!launched) { /*游戏还没开始*/
                    if (player.isStopped()) player.setStopped(false);
                    new Thread(animationPanel).start();
                    new Thread(GamePanel.this).start();
//                    System.out.println("...The animation " + animationPanel.hashCode() + "is started...");
                    monsters.start();  /*点击按钮，开始刷怪*/
                    launched = true;
                    start.setIcon(new ImageIcon(Pic.pausePic[Pic.OUT]));
//                    System.out.println("...Game Launched...");
                } else { /*已经开始则为暂停、继续功能*/
                    player.shiftPaused();
                    synchronized (player) {
                        if (!player.isPaused()) {
                            player.notifyAll();
//                            System.out.println("...notified all...");
                        }
                    }
//                    System.out.println("Game paused or revived");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (launched) {
                    start.setIcon(new ImageIcon(Pic.pausePic[Pic.IN]));
                } else {
                    start.setIcon(new ImageIcon(Pic.startPic[Pic.IN]));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (launched) {
//                    start.setText("3pause*");
                    start.setIcon(new ImageIcon(Pic.pausePic[Pic.OUT]));
                } else {
                    start.setIcon(new ImageIcon(Pic.startPic[Pic.OUT]));
//                    start.setText("4start");
                }

            }
        });

        restart.setBounds(600, 5, 50, 50);
        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                reset();
//                System.out.println("...reset is done...");
                launched = false;
                start.setIcon(new ImageIcon(Pic.startPic[Pic.OUT]));
//                System.out.println("animation" + animationPanel.hashCode());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                restart.setIcon(new ImageIcon(Pic.restartPic[Pic.IN]));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                restart.setIcon(new ImageIcon(Pic.restartPic[Pic.OUT]));
            }
        });

        back.setBounds(900, 5, 50, 50);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                reset();
//                System.out.println("...back...reset is done...");
                setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                back.setIcon(new ImageIcon(Pic.backPic[Pic.IN]));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                back.setIcon(new ImageIcon(Pic.backPic[Pic.OUT]));
            }
        });
    }

    /**
     * 重置本局游戏：重新生成player、monsters、towers、isBuilt。
     * 关于monsters和towers中的各个怪、塔，重新生成之前先：停止原实例的线程（stopped=true使线程结束）
     */
    public void reset() {
        /*停止所有进行中的线程，并更新实体对象*/
        synchronized (player) {
            if (player.isPaused()) player.notifyAll();
            player.setStopped(true); //其他线程应该因此break循环并结束
        }
        player = new Player(); //重新生成玩家
        monsters = new Monsters(map, player);//重新生成monsters
        towers = new Tower[map.towerN]; //重新生成towers

        /*更新建造栏*/
        remove(buildPanel);
        buildPanel = new BuildingPanel(towers, monsters.getMonsters(), player);
        buildPanel.setPreferredSize(new Dimension(0, 70));
        add(buildPanel, BorderLayout.SOUTH);

        /*更新动画面板*/
//        System.out.println("原animation " + animationPanel.hashCode());
        remove(animationPanel);
        animationPanel = new AnimationPanel(map, towers, monsters, player, buildPanel);
        add(animationPanel, BorderLayout.CENTER);
//        System.out.println("新animation " + animationPanel.hashCode());
    }

    /**
     * 该线程用于检测玩家生存状态
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!player.isAlive() || monsters.deadN == map.num) {
                player.setStopped(true);
                JOptionPane.showMessageDialog(null, monsters.deadN >= map.num ? "游戏胜利" : "游戏失败");
                reset();
                break;
            }
        }
    }
}
