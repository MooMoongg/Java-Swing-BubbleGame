package bubble.test.ex02;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame{

	private JLabel backgroudMap;
	private Player player;
	
	public BubbleFrame() {
		initObject();
		initSetting();
		setVisible(true);
	}
	
	private void initObject() {
		backgroudMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroudMap);
		
		player = new Player();
		add(player);
		//backgroudMap.setSize(100, 100);
		//backgroudMap.setLocation(300, 300);
		//backgroudMap.setSize(1000, 600);
		//add(backgroudMap);
	}
	
	private void initSetting() {
		setSize(1000, 640);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new BubbleFrame();

	}

}
