package gui;

import com.sun.xml.internal.ws.api.server.LazyMOMProvider;
import game.GenshinChess;
import res.Map;
import res.Pic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class StartPanel extends JPanel implements MouseMotionListener, MouseListener {
    private static final long serialVersionUID = 1L;

    JButton helpButton, startButton;
    private int helpState = 0, startState = 0;
    private GenshinChess gc;

    /**
     * 初始化按钮及其监听事件（帮助：切换显示gc.helpPanel；开始：初始化gamePanel并添加到gc，切换为该游戏界面
     *
     * @param gc 传入的选关界面
     */
    public StartPanel(GenshinChess gc) {
        setLayout(null);
        setVisible(true);
        setSize(1200, 800);

        this.gc = gc;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * 绘制背景图、重绘内部控件
     *
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);
        setOpaque(true);
        //绘制背景图
        g.drawImage(Pic.background[Pic.MAIN], 0, 0, 1182, 753, this);

        //图片按钮
        g.drawImage(Pic.helpButtonPic[helpState], 1000, 60, 120, 103, this);/*帮助*/
        g.drawImage(Pic.startGamePic[startState], 420, 430, 360, 90, this);/*进入游戏*/
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getX() > 1000 && e.getX() < 1000 + 120 && e.getY() > 60 && e.getY() < 60 + 103) {/*帮助*/
            setVisible(false);
            gc.helpPanel.setVisible(true);
        } else if (e.getX() > 420 && e.getX() < 420 + 360 && e.getY() > 430 && e.getY() < 430 + 90) {/*开始游戏*/
            int i = new Random().nextInt(2); /*返回一个随机整数，区间为 [0,2) */
            System.out.println("Approaching map[" + i + "]...");

            gc.gamePanel = new GamePanel(Map.values()[i]);
            gc.add(gc.gamePanel);
            setVisible(false);
            gc.gamePanel.setVisible(true);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() > 1000 && e.getX() < 1000 + 120 && e.getY() > 60 && e.getY() < 60 + 103) {/*移入帮助*/
            helpState = 1;
            repaint();
        } else if (e.getX() > 420 && e.getX() < 420 + 360 && e.getY() > 430 && e.getY() < 430 + 90) {/*移入开始游戏*/
            startState = 1;
            repaint();
        } else {/*没有移入图标*/
            helpState = 0;
            startState = 0;
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
