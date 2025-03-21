package bubble.test.ex09;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroudMap;
	private Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}

	private void initObject() {
		backgroudMap = new JLabel(new ImageIcon("image/backgroundMapService.png"));
		setContentPane(backgroudMap);
		player = new Player();
		add(player);
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
					player.up();
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
