package bubble.test.ex20;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable {

    private BufferedImage image;
    private Player player;
    private List<Bubble> bubbleList;
    private BubbleFrame mContext;

    // 플레이어, 버블
    public BackgroundPlayerService(Player player) {
        this.player = player;
        this.mContext = player.getMContext();
        this.bubbleList = player.getBubbleList();
        try {
            image = ImageIO.read(new File("image/backgroundMapService.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            // 1. 버블과 적 충돌 체크 (기존 코드)
            for (int i = 0; i < bubbleList.size(); i++) {
                Bubble bubble = bubbleList.get(i);
                if (bubble.getState() == 1) {
                    if ((Math.abs(player.getX() - bubble.getX()) < 10)
                            && Math.abs(player.getY() - bubble.getY()) > 0
                            && Math.abs(player.getY() - bubble.getY()) < 50) {
                        System.out.println("적군 사살 완료");
                        bubble.clearBubbled();
                        break;
                    }

                }
            }

            // 2. 벽 충돌 체크
            Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY() + 25));
            int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5)
                    + image.getRGB(player.getX() + 50 - 10, player.getY() + 50 + 5);

            // 바닥 충돌 확인
            if (bottomColor != -2) {
                player.setDown(false);
            } else { // 바닥이 하얀색일 때
                if (!player.isUp() && !player.isDown()) {
                    player.down();
                }
            }

            // 외벽 충돌 체크
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
                player.setLeftWallCrash(true);
                player.setLeft(false);
            } else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
                player.setRightWallCrash(true);
                player.setRight(false);
            } else {
                player.setLeftWallCrash(false);
                player.setRightWallCrash(false);
            }

            // 3. 플레이어와 enemy 충돌 체크 -> 게임 오버 처리
            for (Enemy enemy : mContext.getEnemyList()) {
                if (enemy.getState() == 0) {
                    int diffX = Math.abs(player.getX() - enemy.getX());
                    int diffY = Math.abs(player.getY() - enemy.getY());
                    if (diffX < 50 && diffY < 50) { // 충돌 임계값 (픽셀 단위, 필요시 조정)
                        System.out.println("플레이어가 적과 충돌하였습니다. 게임 오버!");
                        System.exit(0);
                    }
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
