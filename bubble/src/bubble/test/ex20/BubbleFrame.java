package bubble.test.ex20;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BubbleFrame extends JFrame {

    private BubbleFrame mContext = this;
    private JLabel backgroudMap;
    private Player player;
    // enemy를 단일 객체에서 List로 변경 (3개 생성)
    private List<Enemy> enemyList;

    public BubbleFrame() {
        initObject();
        initSetting();
        initListener();
        setVisible(true);
    }

    private void initObject() {
        backgroudMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        setContentPane(backgroudMap);
        player = new Player(mContext);
        add(player);

        // enemy 3개 생성 후 enemyList에 담고 화면에 추가
        enemyList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Enemy enemy = new Enemy(mContext);
            enemyList.add(enemy);
            add(enemy);
        }

        new BGM();
    }

    private void initSetting() {
        setSize(1000, 640);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initListener() {
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                // System.out.println(e.getKeyCode());

                switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (!player.isLeft() && !player.isLeftWallCrash()) {
                        player.left();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (!player.isRight() && !player.isRightWallCrash()) {
                        player.right();
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (!player.isUp() && !player.isDown()) {
                        player.up();
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    player.attack();
                    break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                     player.setLeft(false);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.setRight(false);
                    break;  
                }  
            }

        });
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
