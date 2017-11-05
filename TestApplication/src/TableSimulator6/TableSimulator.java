package TableSimulator6;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TableSimulator extends JFrame {
	private Image screenImage;
	private Graphics screenGraphic;

	private SceneManager currentScene = null;
 // ũ�⿡ ���߾� ó���� ��
	
	public TableSimulator() {
		GameInformationMaster.GameObject = this;
		setUndecorated(true);
		setTitle("Table Simulator");
		setSize(Main.SCREEN_SIZE_X, Main.SCREEN_SIZE_Y);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		currentScene = SceneManager.createScene(1);
	}
	
	public void changeScene(SceneManager preScene, int i){
		preScene.removeScene();
		currentScene = SceneManager.createScene(i);
	}
	
	// double buffer
	public void paint(Graphics g) { // ���� ù��°�� ȭ���� �׷��ִ� �Լ�. ��ӵ� �Լ�.
		screenImage = createImage(Main.SCREEN_SIZE_X, Main.SCREEN_SIZE_Y);
		screenGraphic = screenImage.getGraphics();
		
		if(currentScene != null)
			currentScene.screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

}