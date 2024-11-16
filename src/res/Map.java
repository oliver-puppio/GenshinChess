package res;

import com.sun.javafx.geom.Point2D;

import java.awt.*;

public enum Map {
    MAP1(
            8,
            new Point2D[]{ //这里是塔的位置，第一个参数是塔的数量
                    new Point2D(830, 20),
                    new Point2D(600, 130),
                    new Point2D(360, 130),
                    new Point2D(110, 170),
                    new Point2D(240, 380),
                    new Point2D(480, 380),
                    new Point2D(720, 380),
                    new Point2D(1010, 390)
            },
            new Point2D[]{ //path1
                    new Point2D(760, -30),
                    new Point2D(760, 120-50),
                    new Point2D(260, 120-50),
                    new Point2D(260, 350-50),
                    new Point2D(1160, 350-50)
            },
            new Point2D[]{ //path2
                    new Point2D(-20, 350-40),
                    new Point2D(900, 350-40),
                    new Point2D(900, 560-40),
                    new Point2D(270, 560-40),
                    new Point2D(270, 660-40),
            },
            Pic.background[Pic.MAP1],
            new int[][]{
                    {2, 0, 1, 0, 0},
                    {2, 2, 1, 1, 0},
                    {2, 2, 1, 2, 2},
                    {2, 2, 1, 3, 3},
                    {2, 2, 1, 3, 3}
            },
            40
    ), MAP2(
            8,
            new Point2D[]{
                    new Point2D(100, 300 - 50),
                    new Point2D(300, 200 - 50),
                    new Point2D(300, 400 - 50),
                    new Point2D(500, 100 - 50),
                    new Point2D(500, 300 - 50),
                    new Point2D(600, 200 - 50),
                    new Point2D(600, 400 - 50),
                    new Point2D(790, 300 - 50),
            },
            new Point2D[]{
                    new Point2D(420, -30),
                    new Point2D(420, 300-10),
                    new Point2D(230, 300-10),
                    new Point2D(230, 500-10),
                    new Point2D(720, 500-10),
                    new Point2D(720, 200-10),
                    new Point2D(920, 200-10),
                    new Point2D(920, 400-10),
                    new Point2D(1120, 400-10),
            },
            new Point2D[]{
                    new Point2D(420, -30),
                    new Point2D(420, 300-10),
                    new Point2D(230, 300-10),
                    new Point2D(230, 500-10),
                    new Point2D(720, 500-10),
                    new Point2D(720, 200-10),
                    new Point2D(920, 200-10),
                    new Point2D(920, 400-10),
                    new Point2D(1120, 400-10),
            },
            Pic.background[Pic.MAP2],
             new int[][]{
                    {2, 0, 1, 0, 0},
                    {2, 2, 1, 1, 1},
                    {1, 1, 1, 2, 2},
                    {1, 2, 2, 3, 3},
                    {1, 2, 2, 4, 3}
            }, 40
    );

    public int towerN;              //塔的数量
    public Point2D[] plants;          //塔的位置
    public Point2D[] path1;           //第一条路
    public Point2D[] path2;           //第二条路
    public Image background;        //背景图片
    public int[][] rule;   //该地图对应的怪物种类和生成次序
    public int num;//怪物数量

    /**
     * @param tn     塔的数量
     * @param plants 塔的位置坐标数组
     * @param path1  第一条路
     * @param path2  第二条路
     * @param bg     背景图片
     * @param mn     二维数组，按顺序表示5种怪的数量和生成次序
     * @param num    怪物总数
     */
    Map(int tn, Point2D[] plants, Point2D[] path1, Point2D[] path2, Image bg, int[][] mn, int num) {
        this.towerN = tn;
        this.plants = plants;
        this.path1 = path1;
        this.path2 = path2;
        this.background = bg;
        this.rule = mn;
        this.num = num;
    }


}
