package bubble.test.ex18;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private Enemy enemy; // enemy를 여러마리 만들고 싶으면 private List<Enemy> enemy;

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
		enemy = new Enemy(mContext);
		add(enemy);
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

	});}

	public static void main(String[] args) {
		new BubbleFrame();

	}

}
