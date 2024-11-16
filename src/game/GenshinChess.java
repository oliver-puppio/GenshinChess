package game;

import gui.GamePanel;
import gui.HelpPanel;
//import gui.MissionPanel;
import gui.StartPanel;

import javax.swing.*;
import java.awt.*;

public class GenshinChess extends JFrame {
    public static void main(String[] args) {
//        System.out.println("Hello Genshin");
        new GenshinChess();
    }

    private CardLayout cl;
    private StartPanel startPanel;/*首页*/
    public HelpPanel helpPanel;   /*帮助页面*/
    public GamePanel gamePanel;   /*在startPanel中由随机数创建、再设置并添加到GenshinChess窗体*/

    /**
     *
     */
    public GenshinChess() {
        //布局、标题、位置、大小
        cl = new CardLayout();
        setLayout(cl);
        setTitle("GenshinChess");

        helpPanel = new HelpPanel();
        startPanel = new StartPanel(this);

        //卡片布局，先添加startPanel
        add(startPanel);
        add(helpPanel);

        //需要在startPanel中创建实例再add
//        add(gamePanel);

        setVisible(true);
        setBounds(350, 120, 1200, 800);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
