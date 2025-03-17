package bubble.test.ex20;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

    // 의존성 컴포지션
    private BubbleFrame mContext;
    private Player player;
    // enemy는 이제 BubbleFrame의 enemyList에서 처리합니다.
    private BackgroundBubbleService backgroundBubbleService;

    // 위치 상태
    private int x;
    private int y;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;

    // 적군을 맞춘 상태
    private int state; // 0(물방울), 1(적을 가둔 물방울)

    private ImageIcon bubble;   // 물방울
    private ImageIcon bubbled;  // 적을 가둔 물방울
    private ImageIcon bomb;     // 물방울이 터진 상태

    public Bubble(BubbleFrame mContext) {
        this.mContext = mContext;
        this.player = mContext.getPlayer();
        initObject();
        initSetting();
    }

    private void initObject() {
        bubble = new ImageIcon("image/bubble.png");
        bubbled = new ImageIcon("image/bubbled.png");
        bomb = new ImageIcon("image/bomb.png");

        backgroundBubbleService = new BackgroundBubbleService(this);
    }

    private void initSetting() {
        left = false;
        right = false;
        up = false;

        x = player.getX();
        y = player.getY();

        setIcon(bubble);
        setSize(50, 50);

        state = 0;
    }

    @Override
    public void left() {
        left = true;
        for (int i = 0; i < 400; i++) {
            x--;
            setLocation(x, y);

            if (backgroundBubbleService.leftWall()) {
                left = false;
                break;
            }

            // enemyList에서 모든 enemy와 충돌 체크
            for (Enemy enemy : mContext.getEnemyList()) {
                if (enemy.getState() == 0 &&
                    (Math.abs(x - enemy.getX()) < 10) &&
                    Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50) {
                    System.out.println("물방울이 적군과 충돌하였습니다.");
                    attack(enemy);
                    left = false; // 반복 종료
                    break;
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!left) break;
        }
        up();
    }

    @Override
    public void right() {
        right = true;
        for (int i = 0; i < 400; i++) {
            x++;
            setLocation(x, y);

            if (backgroundBubbleService.rightWall()) {
                right = false;
                break;
            }

            for (Enemy enemy : mContext.getEnemyList()) {
                if (enemy.getState() == 0 &&
                    (Math.abs(x - enemy.getX()) < 10) &&
                    Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50) {
                    System.out.println("물방울이 적군과 충돌하였습니다.");
                    attack(enemy);
                    right = false;
                    break;
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!right) break;
        }
        up();
    }

    @Override
    public void up() {
        up = true;
        while (up) {
            y--;
            setLocation(x, y);

            if (backgroundBubbleService.topWall()) {
                up = false;
                break;
            }

            try {
                if (state == 0) { // 기본 물방울
                    Thread.sleep(1);
                } else { // 적을 가둔 물방울
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (state == 0) clearBubble(); // 천장에 도달 후 3초 후 메모리에서 제거
    }

    // enemy를 매개변수로 받아 충돌 처리
    public void attack(Enemy enemy) {
        state = 1;
        enemy.setState(1);
        setIcon(bubbled);
        mContext.remove(enemy);
        mContext.repaint();
    }

    private void clearBubble() {
        try {
            Thread.sleep(3000);
            setIcon(bomb);
            Thread.sleep(500);
            mContext.remove(this); // BubbleFrame에서 제거
            mContext.repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearBubbled() {
        new Thread(() -> {
            try {
                up = false;
                setIcon(bomb);
                Thread.sleep(1000);
                mContext.remove(this);
                mContext.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("clearBubbled");
    }
}
