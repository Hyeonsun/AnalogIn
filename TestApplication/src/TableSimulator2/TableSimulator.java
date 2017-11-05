package TableSimulator2;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TableSimulator extends JFrame {
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image introBackground;
	
	public TableSimulator(){
		setTitle("Table Simulator");
		setSize(Main.SCREEN_SIZE_X,Main.SCREEN_SIZE_Y);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		introBackground = new ImageIcon(Main.class.getResource("../image/testBackground.jpg")).getImage(); // �ʱ�ȭ
	}
	//double buffer
	public void paint(Graphics g){ // ���� ù��°�� ȭ���� �׷��ִ� �Լ�. ��ӵ� �Լ�.
		screenImage = createImage(Main.SCREEN_SIZE_X,Main.SCREEN_SIZE_Y);
		screenGraphic = screenImage.getGraphics();

		screenDraw(screenGraphic);

		g.drawImage(screenImage, 0, 0, null);		
	}
	public void screenDraw(Graphics g){
		g.drawImage(introBackground, 0, 0, null);
		g.drawImage(introBackground, 100, 100, null);
		this.repaint(); 
	}
}