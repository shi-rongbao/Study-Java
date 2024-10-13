package com.study.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    int count = 0;//定义变量,用来统计步数
    String[] girlPath = {
            "Study-016\\image\\girl\\girl1\\",
            "Study-016\\image\\girl\\girl2\\",
            "Study-016\\image\\girl\\girl3\\",
            "Study-016\\image\\girl\\girl4\\",
            "Study-016\\image\\girl\\girl5\\",
            "Study-016\\image\\girl\\girl6\\",
            "Study-016\\image\\girl\\girl7\\",
            "Study-016\\image\\girl\\girl8\\",
            "Study-016\\image\\girl\\girl9\\",
            "Study-016\\image\\girl\\girl10\\",

    };

    String[] animalPath = {
            "Study-016\\image\\animal\\animal1\\",
            "Study-016\\image\\animal\\animal2\\",
            "Study-016\\image\\animal\\animal3\\",
            "Study-016\\image\\animal\\animal4\\",
            "Study-016\\image\\animal\\animal5\\",
            "Study-016\\image\\animal\\animal6\\",
            "Study-016\\image\\animal\\animal7\\",
            "Study-016\\image\\animal\\animal8\\",

    };

    String[] sportPath = {
            "Study-016\\image\\sport\\sport1\\",
            "Study-016\\image\\sport\\sport2\\",
            "Study-016\\image\\sport\\sport3\\",
            "Study-016\\image\\sport\\sport4\\",
            "Study-016\\image\\sport\\sport5\\",
            "Study-016\\image\\sport\\sport6\\",
            "Study-016\\image\\sport\\sport7\\",
            "Study-016\\image\\sport\\sport8\\",
            "Study-016\\image\\sport\\sport9\\",
            "Study-016\\image\\sport\\sport10\\",
    };
    String defaultPath = "Study-016\\image\\animal\\animal3\\"; // 图片路径

    //创建一个二维数组,图片会根据二维数组中的数据进行加载
    int[][] arr = new int[4][4];
    //创建winArr,这个数组是按顺序排列好的,后续判断游戏是否胜利
    int[][] winArr = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    JMenuItem replayItem = new JMenuItem("重新游戏");//创建"重新游戏按钮"
    JMenuItem reLoginItem = new JMenuItem("重新登录");//创建"重新登录按钮"
    JMenuItem closeItem = new JMenuItem("关闭游戏");//创建"关闭游戏按钮"
    JMenuItem accountItem = new JMenuItem("二维码");//创建"二维码按钮"

    JMenuItem girl = new JMenuItem("美女");//创建"美女"按钮
    JMenuItem animal = new JMenuItem("动物");//创建"动物"按钮
    JMenuItem sport = new JMenuItem("运动");//创建"运动"按钮


    //记录空白方块在数组中的位置
    int x = 0;
    int y = 0;


    public GameJFrame() {
        initJFrame();//初始化界面
        initJMenuBar();//初始化菜单
        initDate();//初始化数据(打乱图片)
        initImage();//初始化图片


        setVisible(true);//设置页面可见
    }

    //初始化数据(打乱图片所对应的二维数组)
    private void initDate() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};//创建一维数组,存储0-15的元素
        Random r = new Random();
        //打乱一维数组中的元素
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //将一维数组填充到二维数组中
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {//找到 0 元素,计算出空白图片坐标
                x = i / 4;
                y = i % 4;
            }
            arr[i / 4][i % 4] = tempArr[i];//将一维数组填充到二维数组中
        }
        //遍历二维数组中的元素
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    //初始化图片
    private void initImage() {
        //清空JFrame对象中所有组件
        this.getContentPane().removeAll();//this->JFrame对象
        //如果victory返回为true,则显示 "胜利" 图片
        if (victory()) {
            JLabel winImage = new JLabel(new ImageIcon("Study-016\\image\\win.png"));
            winImage.setBounds(203, 283, 197, 73);
            getContentPane().add(winImage);
        }
        //显示步数
        JLabel stepCount = new JLabel("步数:" + count);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);
        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("Study-016\\image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        //循环嵌套,将要还原的图片放入窗体中
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel jLabel = new JLabel(new ImageIcon(defaultPath + arr[i][j] + ".jpg"));
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                getContentPane().add(jLabel);
            }
        }
        getContentPane().add(background);

        this.getContentPane().repaint();//刷新页面
    }

    //初始化菜单
    private void initJMenuBar() {
        //创建菜单
        JMenuBar jMenuBar = new JMenuBar();
        JMenu changeImage = new JMenu("更换图片");
        JMenu functionJMenu = new JMenu("功能");
        JMenu rechargeJMenu = new JMenu("充值");

        jMenuBar.add(functionJMenu);
        jMenuBar.add(rechargeJMenu);
        //给菜单添加动作监听功能
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        //将二级菜单添加到一级菜单中
        functionJMenu.add(changeImage);
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        rechargeJMenu.add(accountItem);

        setJMenuBar(jMenuBar);
    }

    //初始化界面
    private void initJFrame() {
        setSize(603, 680);//设置JFrame窗体大小
        setTitle("拼图单机版 v1.0");//设置窗体标题
        setAlwaysOnTop(true);//设置窗体总是在最上层
        setLocationRelativeTo(null);//设置窗体启动后居中位置
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置点击关闭按钮后JVM虚拟机停止
        setLayout(null);//取消组件的默认位置
        setResizable(false);//设置窗体不可以被改变大小
        this.addKeyListener(this);//给组件添加键盘监听
    }

    //判断数组与winArr是否相等,相等返回true,意为游戏胜利
    private boolean victory() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != winArr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //该方法执行第一步首先判断游戏是否已经胜利,如果已经胜利,则直接return结束方法
        if (victory()) {
            return;
        }
        //查看完整图片功能
        //如果游戏还未胜利,则按下 A 键则查看完整图片
        if (e.getKeyCode() == 65) {
            this.getContentPane().removeAll();
            JLabel background = new JLabel(new ImageIcon("Study-016\\image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            JLabel jLabel = new JLabel(new ImageIcon(defaultPath + "all.jpg"));
            jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
            jLabel.setBounds(83, 134, 420, 420);
            JLabel stepCount = new JLabel("步数:" + count);
            stepCount.setBounds(50, 30, 100, 20);
            this.getContentPane().add(stepCount);
            this.getContentPane().add(jLabel);
            this.getContentPane().add(background);
            this.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //该方法执行第一步首先判断游戏是否已经胜利,如果已经胜利,则直接return结束方法
        if (victory()) {
            return;
        }
        //上下左右移动功能
        //如果游戏还未胜利,按 上下左右 键可以控制空白图片移动
        int code = e.getKeyCode();
        if (code == 37) {
            if (y == 0) {
                return;
            }
            arr[x][y] = arr[x][y - 1];
            arr[x][y - 1] = 0;
            y--;
            count++;
            initImage();
        } else if (code == 38) {
            if (x == 0) {
                return;
            }
            arr[x][y] = arr[x - 1][y];
            arr[x - 1][y] = 0;
            x--;
            count++;
            initImage();
        } else if (code == 39) {
            if (y == 3) {
                return;
            }
            arr[x][y] = arr[x][y + 1];
            arr[x][y + 1] = 0;
            y++;
            count++;
            initImage();
        } else if (code == 40) {
            if (x == 3) {
                return;
            }
            arr[x][y] = arr[x + 1][y];
            arr[x + 1][y] = 0;
            x++;
            count++;
            initImage();
        } else if (code == 65) {//松开A键后再次显示游戏页面
            initImage();
        } else if (code == 87) {//如果按下W 键,将二维数组更换为已正确排列的数组
            arr = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random r = new Random();
        //鼠标动作监听
        //重新游戏功能
        if (e.getSource() == replayItem) {
            count = 0;
            initDate();
            initImage();
        } else if (e.getSource() == reLoginItem) {//重新登录功能
            setVisible(false);
            new LoginJFrame();
        } else if (e.getSource() == closeItem) {//关闭游戏功能
            System.exit(0);//点击后直接结束虚拟机运行
        } else if (e.getSource() == accountItem) {//二维码功能
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("Study-016\\image\\WeChat.png"));
            jLabel.setBounds(0, 0, 258, 258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(522, 618);
            jDialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setResizable(false);
            jDialog.setVisible(true);
        } else if (e.getSource() == girl) {
            if (victory()){
                return;
            }
            int index = r.nextInt(10);
            defaultPath = girlPath[index];
            count = 0;
            initDate();
            initImage();
        } else if (e.getSource() == animal) {
            if (victory()){
                return;
            }
            int index = r.nextInt(8);
            defaultPath = animalPath[index];
            count = 0;
            initDate();
            initImage();
        } else if (e.getSource() == sport) {
            if (victory()){
                return;
            }
            int index = r.nextInt(10);
            defaultPath = sportPath[index];
            count = 0;
            initDate();
            initImage();
        }
    }
}