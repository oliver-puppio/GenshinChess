package gui;

import com.sun.javafx.geom.Point2D;
import game.Player;
import monster.Monster;
import res.Pic;
import tower.CryoTower;
import tower.HydroTower;
import tower.PyroTower;
import tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuildingPanel extends JPanel {
    Player player;
    Tower[] towers;
    Monster[] monsters; /*将monsters传递给要创建的tower实例*/
    Point2D position;
    int pos; /*触发此panel的 空地 或塔，也是本panel要操作的地（塔）*/

    JLabel CryoBtn, HydroBtn, PyroBtn, levelUpBtn, saleBtn;

    public BuildingPanel(Tower[] towers, Monster[] mons, Player player) {
        setLayout(null);
        setVisible(false);
        setPreferredSize(new Dimension(0, 65));
        setBackground(new Color(225, 222, 194));
        this.towers = towers;
        this.monsters = mons;
        this.player = player;

        CryoBtn = new JLabel("Cryo");
        CryoBtn.setFont(new Font("宋", Font.BOLD, 16));
        CryoBtn.setIcon(new ImageIcon(Pic.CryoBullet[0]));
        CryoBtn.setBounds(300 - 80, 5, 160, 60);
        CryoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (Tower.price[0] <= player.getMoney()) {
                    player.purchase(Tower.price[0]);
                    towers[pos] = new CryoTower(position, monsters, player);
                    new Thread(towers[pos]).start();
                }
                setVisible(false);

            }
        });

        HydroBtn = new JLabel("Hydro");
        HydroBtn.setFont(new Font("宋", Font.BOLD, 16));
        HydroBtn.setIcon(new ImageIcon(Pic.HydroBullet[0]));
        HydroBtn.setBounds(600 - 80, 5, 160, 60);
        HydroBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (Tower.price[0] <= player.getMoney()) {
                    player.purchase(Tower.price[0]);
                    towers[pos] = new HydroTower(position, monsters, player);
                    new Thread(towers[pos]).start();
                }
                setVisible(false);
            }
        });

        PyroBtn = new JLabel("Pyro");
        PyroBtn.setFont(new Font("宋", Font.BOLD, 16));
        PyroBtn.setIcon(new ImageIcon(Pic.PyroBullet[0]));
        PyroBtn.setBounds(900 - 80, 5, 160, 60);
        PyroBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (Tower.price[0] <= player.getMoney()) {
                    player.purchase(Tower.price[0]);
                    towers[pos] = new PyroTower(position, monsters, player);
                    new Thread(towers[pos]).start();
                }
                setVisible(false);
            }
        });

        levelUpBtn = new JLabel("Level up");
        levelUpBtn.setFont(new Font("宋", Font.BOLD, 18));
        levelUpBtn.setBounds(400 - 80, 5, 160, 60);
        levelUpBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (towers[pos].getCurrentPrice() <= player.getMoney() && towers[pos].getRank() < 2) {
                    towers[pos].levelUp();
                    player.purchase(towers[pos].getCurrentPrice());
                }
                setVisible(false);
            }
        });

        saleBtn = new JLabel("Sell");
        saleBtn.setFont(new Font("宋", Font.BOLD, 18));
        saleBtn.setBounds(800 - 80, 5, 160, 60);
        saleBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                player.addMoney(towers[pos].getCurrentCost());
                towers[pos].kill();
                towers[pos] = null;
                setVisible(false);
            }

        });

        add(CryoBtn);
        add(HydroBtn);
        add(PyroBtn);
        add(levelUpBtn);
        add(saleBtn);

    }

    /**
     * 点击第i个塔触发了该面板的显示，面板内显示升级或建造
     *
     * @param i        第i个塔
     * @param position 放置位置
     */
    public void show(int i, Point2D position) {
        this.pos = i;
        this.position = position;
        setVisible(true);
        /*如果第i个已经有塔，显示升级及所需金额、售卖及价格*/
        if (towers[i] != null) {
            this.CryoBtn.setVisible(false);
            this.HydroBtn.setVisible(false);
            this.PyroBtn.setVisible(false);

            if (towers[pos].getRank() < 2) {
                levelUpBtn.setText("Level up($" + Tower.price[towers[pos].getRank() + 1] + ")");
                this.levelUpBtn.setVisible(true);
            } else this.levelUpBtn.setVisible(false);
            saleBtn.setText("Sell($" + Tower.cost[towers[pos].getRank()] + ")");
            this.saleBtn.setVisible(true);
        }
        /*如果第i个还没有塔，即空地，则显示各种塔的按钮以及初始价格*/
        else {
            CryoBtn.setText("Cryo($" + Tower.price[0] + ")");
            HydroBtn.setText("Hydro($" + Tower.price[0] + ")");
            PyroBtn.setText("Pyro($" + Tower.price[0] + ")");
            this.CryoBtn.setVisible(true);
            this.HydroBtn.setVisible(true);
            this.PyroBtn.setVisible(true);

            this.levelUpBtn.setVisible(false);
            this.saleBtn.setVisible(false);
        }
    }
}
