package bubble.test.ex20;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy extends JLabel implements Moveable {

    private BubbleFrame mContext;

    // 위치 상태
    private int x;
    private int y;

    // 적군의 방향
    private EnemyWay enemyWay;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private int state; // 0(살아있는 상태), 1(물방울에 갇힌 상태)

    // 적군 속도 상태
    private final int SPEED = 3;
    private final int JUMPSPEED = 1;

    private ImageIcon enemyR, enemyL;

    public Enemy(BubbleFrame mContext) {
        this.mContext = mContext;
        initObject();
        initSetting();
        initBackgroundEnemyService();

        // 독립적인 좌우 움직임을 위한 제어 스레드
        new Thread(() -> {
            java.util.Random r = new java.util.Random();
            while (state == 0) {
                // 현재 움직임 정지
                left = false;
                right = false;
                try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
                // 좌우 방향 랜덤 선택
                if (r.nextBoolean()) {
                    left();
                } else {
                    right();
                }
                // 선택한 방향으로 1~3초간 이동
                try {
                    Thread.sleep(1000 + r.nextInt(2000));
                } catch (InterruptedException e) { e.printStackTrace(); }
                // 이동 정지 후 잠시 대기
                left = false;
                right = false;
                try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }).start();

        // 점프 기능: 2~5초 간격으로 점프
        new Thread(() -> {
            java.util.Random r = new java.util.Random();
            while (state == 0) {
                try {
                    Thread.sleep(2000 + r.nextInt(3000)); // 2~5초 간격
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!up && !down) {
                    up();
                }
            }
        }).start();
    }

    private void initObject() {
        enemyR = new ImageIcon("image/enemyR.png");
        enemyL = new ImageIcon("image/enemyL.png");
    }

    private void initSetting() {
        // 랜덤한 x 좌표를 지정 (예: 300 ~ 700 범위)
        x = 300 + new java.util.Random().nextInt(400);
        y = 178; // 고정된 y 좌표 (필요시 조정 가능)

        left = false;
        right = false;
        up = false;
        down = false;

        state = 0;

        // 초기 방향도 랜덤하게 선택
        enemyWay = new java.util.Random().nextBoolean() ? EnemyWay.RIGHT : EnemyWay.LEFT;

        setIcon(enemyR);
        setSize(50, 50);
        setLocation(x, y);
    }

    private void initBackgroundEnemyService() {
        new Thread(new BackgroundEnemyService(this)).start();
    }

    @Override
    public void left() {
        enemyWay = EnemyWay.LEFT;
        left = true;
        new Thread(() -> {
            while (left) {
                setIcon(enemyL);
                x = x - SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10); // 0.01초
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void right() {
        enemyWay = EnemyWay.RIGHT;
        right = true;
        new Thread(() -> {
            while (right) {
                setIcon(enemyR);
                x = x + SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10); // 0.01초
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void up() {
        up = true;
        new Thread(() -> {
            for (int i = 0; i < 130 / JUMPSPEED; i++) {
                y = y - JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            up = false;
            down();
        }).start();
    }

    @Override
    public void down() {
        down = true;
        new Thread(() -> {
            while (down) {
                y = y + JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            down = false;
        }).start();
    }
}
