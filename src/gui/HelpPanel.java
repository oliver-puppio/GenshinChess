package gui;

import res.Pic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class HelpPanel extends JPanel implements MouseListener, MouseMotionListener {
    private int pageNow = 0;
    private int backState = 0, lastState = 0, nextState = 0;/*鼠标是否移入图片范围*/

    /**
     * 创建实例时，该panel不可见
     */
    public HelpPanel() {
        setVisible(false);
        setBackground(Color.black);
        setSize(1200, 750);

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    /**
     * 根据当前页数绘制帮助页图片
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setOpaque(true);
        //根据当前所处页数绘制pic
        g.drawImage(Pic.helpBgPic[pageNow], 0, 0, 1182, 755, this);

        //绘制可点击的图片（返回、上一页、下一页）
        g.drawImage(Pic.backPic[backState], 75, 660, 50, 50, this);
        g.drawImage(Pic.lastPagePic[lastState], 200, 660, 50, 50, this);
        g.drawImage(Pic.nextPagePic[nextState], 300, 660, 50, 50, this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //点击返回
        if (e.getX() > 75 && e.getX() < 75 + 50 && e.getY() > 660 && e.getY() < 660 + 50) {
            setVisible(false);
            pageNow = 0;
        }
        //点击lastPage
        if (e.getX() > 200 && e.getX() < 200 + 50 && e.getY() > 660 && e.getY() < 660 + 50) {
            if (pageNow > 0) pageNow--;
            repaint();
        }
        //点击nextPage
        if (e.getX() > 300 && e.getX() < 300 + 50 && e.getY() > 660 && e.getY() < 660 + 50) {
            if (pageNow < 2) pageNow++;
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //返回
        if (e.getX() > 75 && e.getX() < 125 && e.getY() > 660 && e.getY() < 710) {
            backState = 1;
            repaint();
        }
        //lastPage
        else if (e.getX() > 200 && e.getX() < 250 && e.getY() > 660 && e.getY() < 710) {
            lastState = 1;
            repaint();
        }
        //nextPage
        else if (e.getX() > 300 && e.getX() < 350 && e.getY() > 660 && e.getY() < 710) {
            nextState = 1;
            repaint();
        } else {
            backState = 0;
            lastState = 0;
            nextState = 0;
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
