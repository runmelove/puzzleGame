package ui;

import domain.GameInfo;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Properties;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    int[][] dataArr = new int[4][4];
    int x = 0;
    int y = 0;
    String imageType = "animal";
    Random r = new Random();
    int imageIndex = 3;
    String path = "puzzlegame\\image\\" + imageType + "\\" + imageType + imageIndex + "\\";
    int[][] win ={
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 0},
    };
    int step = 0;
    // 创建选项下的条目对象
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenu saveJMenu = new JMenu("存档");
    JMenu loadJMenu = new JMenu("读档");

    JMenuItem saveItem0 = new JMenuItem("存档0(空)");
    JMenuItem saveItem1 = new JMenuItem("存档1(空)");
    JMenuItem saveItem2 = new JMenuItem("存档2(空)");
    JMenuItem saveItem3 = new JMenuItem("存档3(空)");
    JMenuItem saveItem4 = new JMenuItem("存档4(空)");

    JMenuItem loadItem0 = new JMenuItem("读档0(空)");
    JMenuItem loadItem1 = new JMenuItem("读档1(空)");
    JMenuItem loadItem2 = new JMenuItem("读档2(空)");
    JMenuItem loadItem3 = new JMenuItem("读档3(空)");
    JMenuItem loadItem4 = new JMenuItem("读档4(空)");

    JMenuItem acountItem = new JMenuItem("公众号");

    public GameJFrame() {
        // 初始化界面
        initJFrame();

        // 初始化菜单
        initJMenuBar();

        // 初始化数据
        initData();

        // 初始化图片
        initImage();

        // 显示界面
        this.setVisible(true);
    }

    private void initData() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[index];
            tempArr[index] = tempArr[i];
            tempArr[i] = temp;
        }

        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                x = i/4;
                y = i%4;
            }
            dataArr[i/4][i%4] = tempArr[i];
        }
    }

    private void initImage() {
        // 清空原本的图片
        this.getContentPane().removeAll();
        /*
        // 创建一个图片ImageIcon的对象
        ImageIcon icon1 = new ImageIcon("D:\\java\\code\\idea\\puzzlegame\\image\\animal\\animal3\\3.jpg");
        // 创建一个JLabel的对象（管理容器）
        JLabel jLabel1 = new JLabel(icon1);
        // 指定图片位置
        jLabel1.setBounds(0, 0, 105, 105);
        // 将管理容器添加到界面中
        // this.add(jLabel1);
        this.getContentPane().add(jLabel1);
        */
        if(checkWin()){
            JLabel win = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            win.setBounds(203, 283, 197, 73);
            this.getContentPane().add(win);
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = dataArr[i][j];
                // 创建管理容器
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                // 指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                // 为图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                // 将管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30 ,100, 20);
        this.getContentPane().add(stepCount);

        // 刷新界面
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        // 创建整个菜单栏的对象
        JMenuBar jMenuBar = new JMenuBar();

        // 创建菜单选项的对象（功能 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu changeImage = new JMenu("更换图片");

        JMenu aboutJMenu = new JMenu("关于我们");

        //把美女，动物，运动添加到更换图片当中
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);

        //把5个存档，添加到saveJMenu中
        saveJMenu.add(saveItem0);
        saveJMenu.add(saveItem1);
        saveJMenu.add(saveItem2);
        saveJMenu.add(saveItem3);
        saveJMenu.add(saveItem4);

        //把5个读档，添加到loadJMenu中
        loadJMenu.add(loadItem0);
        loadJMenu.add(loadItem1);
        loadJMenu.add(loadItem2);
        loadJMenu.add(loadItem3);
        loadJMenu.add(loadItem4);

        // 将每一个条目对象添加到对应的选项中
        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        functionJMenu.add(saveJMenu);
        functionJMenu.add(loadJMenu);

        //将公众号添加到关于我们中
        aboutJMenu.add(acountItem);

        // 将菜单选项添加到菜单栏中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        // 为条目对象绑定事件
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        acountItem.addActionListener(this);
        saveItem0.addActionListener(this);
        saveItem1.addActionListener(this);
        saveItem2.addActionListener(this);
        saveItem3.addActionListener(this);
        saveItem4.addActionListener(this);
        loadItem0.addActionListener(this);
        loadItem1.addActionListener(this);
        loadItem2.addActionListener(this);
        loadItem3.addActionListener(this);
        loadItem4.addActionListener(this);

        //读取存档信息，修改菜单上表示的内容
        getGameInfo();

        // 为整个界面设置JMenuBar对象
        this.setJMenuBar(jMenuBar);
    }

    private void getGameInfo() {
        //获取每一个文件，存入集合
        File file = new File("puzzlegame\\save");
        File[] files = file.listFiles();
        //遍历文件集合，获取步数以及存档名
        for (File f : files) {
            GameInfo gi = null;
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                gi = (GameInfo)ois.readObject();
                ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            //获取到步数
            int step = gi.getStep();
            //得到文件名，获取索引
            String name = f.getName();
            int index = name.charAt(4) - '0';
            //修改菜单上所表示的文字信息
            saveJMenu.getItem(index).setText("存档" + index + "(" + step + ")步");
            loadJMenu.getItem(index).setText("存档" + index + "(" + step + ")步");
        }
    }

    private void initJFrame() {
        // 设置界面的宽高
        this.setSize(603, 680);
        // 设置界面标题
        this.setTitle("拼图游戏单机版 v1.0");
        // 置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 取消默认的居中放置，取消后才可使用XY轴的放置方式
        setLayout(null);
        // 为界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(checkWin()){
            return;
        }
        int code = keyEvent.getKeyCode();
        if(code == 65){
            // 清空原本的图片
            this.getContentPane().removeAll();
            // 添加完整图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            // 加载背景图片
            JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);

            // 刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(checkWin()){
            return;
        }
        int code = keyEvent.getKeyCode();
        if(code == 37){
            if(y == 3){
                System.out.println("超出边界");
                return;
            }else{
                System.out.println("向左移动");
                dataArr[x][y] = dataArr[x][y+1];
                dataArr[x][y+1] = 0;
                y++;
                step++;

                initImage();
            }

        }else if(code == 38){
            if(x == 3){
                System.out.println("超出边界");
                return;
            }else{
                System.out.println("向上移动");
                dataArr[x][y] = dataArr[x+1][y];
                dataArr[x+1][y] = 0;
                x++;
                step++;

                initImage();
            }

        }else if(code == 39){
            if(y == 0){
                System.out.println("超出边界");
                return;
            }else{
                System.out.println("向右移动");
                dataArr[x][y] = dataArr[x][y-1];
                dataArr[x][y-1] = 0;
                y--;
                step++;

                initImage();
            }
        }else if(code == 40){
            if(x == 0){
                System.out.println("超出边界");
                return;
            }else{
                System.out.println("向下移动");
                dataArr[x][y] = dataArr[x-1][y];
                dataArr[x-1][y] = 0;
                x--;
                step++;

                initImage();
            }
        }else if(code == 65){
            initImage();
        }else if(code == 87){
            dataArr= new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0},
            };
            x = 3;
            y = 3;
            initImage();
        }
    }

    public boolean checkWin(){
        for (int i = 0; i < dataArr.length; i++) {
            for (int j = 0; j < dataArr[i].length; j++) {
                if(dataArr[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source == replayItem){
            System.out.println("重新游戏");
            step = 0;
            initData();
            initImage();

        }else if(source == reLoginItem){
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();
        }else if(source == closeItem){
            System.out.println("关闭游戏");
            System.exit(0);
        }else if(source == acountItem){
            System.out.println("公众号");
            //1.创建集合
            Properties prop = new Properties();
            //2.读取数据
            try {
                FileInputStream fis = new FileInputStream("puzzlegame\\game.properties");
                prop.load(fis);
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //3.获取图片的路径
            String path = (String) prop.get("account");
            //4.调用showJDialog方法，展示弹窗
            //方法的参数就是图片的路径
            showJDialog(path);

        }else if(source == girl){
            reImage(source);
        }else if(source == animal){
            reImage(source);
        }else if(source == sport){
            reImage(source);
        }else if (source == saveItem0 || source == saveItem1 || source == saveItem2 || source == saveItem3 || source == saveItem4){
            //获取存档索引
            JMenuItem item = (JMenuItem) source;
            String str = item.getText();
            int index = str.charAt(2) - '0';
            //将游戏数据写到本地
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("puzzlegame\\save\\save" + index + ".data"));
                GameInfo gi = new GameInfo(dataArr, x, y, path, step);
                oos.writeObject(gi);
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //修改一下存档item上的展示信息
            //存档0(XX步)
            item.setText("存档" + index + "(" + step + "步)");
            //修改一下读档item上的展示信息
            loadJMenu.getItem(index).setText("存档" + index + "(" + step + "步)");
        }else if (source == loadItem0 || source == loadItem1 || source == loadItem2 || source == loadItem3 || source == loadItem4){
            //获取当前是哪个读档被点击了，获取其中的序号
            JMenuItem item = (JMenuItem) source;
            String str = item.getText();
            int index = str.charAt(2) - '0';

            GameInfo gi = null;
            try {
                //可以到本地文件中读取数据
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("puzzlegame\\save\\save" + index + ".data"));
                gi = (GameInfo)ois.readObject();
                ois.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

            dataArr = gi.getData();
            path = gi.getPath();
            step = gi.getStep();
            x = gi.getX();
            y = gi.getY();

            //重新刷新界面加载游戏
            initImage();
        }
    }

    //创建一个弹框对象
    JDialog jDialog = new JDialog();
    //展示弹框
    public void showJDialog(String filepath) {
        if(!jDialog.isVisible()){
            JLabel jLabel = null;
            //判断传递的字符串是否是一个路径，且必须以jpg或者png结尾
            //如果满足，则当做图片处理
            //如果不满足，则当做普通字符串处理
            if(new File(filepath).exists() && (filepath.endsWith(".png")||filepath.endsWith(".jpg") )){
                jLabel = new JLabel(new ImageIcon(filepath));
            }else{
                jLabel = new JLabel(filepath);
            }
            //设置位置和宽高
            jLabel.setBounds(0, 0, 258, 258);
            //把图片添加到弹框当中
            jDialog.getContentPane().add(jLabel);
            //给弹框设置大小
            jDialog.setSize(344, 344);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);
            //让弹框显示出来
            jDialog.setVisible(true);
        }
    }
    public void reImage(Object source){
        if(source == girl){
            imageType = "girl";
            imageIndex = r.nextInt(13 + 1);
        }else if(source == animal){
            imageType = "animal";
            imageIndex = r.nextInt(8 + 1);
        }else if(source == sport){
            imageType = "sport";
            imageIndex = r.nextInt(10 + 1);
        }

        path = "puzzlegame\\image\\" + imageType + "\\" + imageType + imageIndex + "\\";

        step = 0;
        initData();
        initImage();
    }
}

