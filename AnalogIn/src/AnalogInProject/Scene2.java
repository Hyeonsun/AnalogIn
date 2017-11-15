package AnalogInProject;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Scene2 extends SceneManager {
	private SceneManager thisInstance = this;
	private JButton testButton = new JButton(
			new ImageIcon(ImageManager.background.getScaledInstance(400, 100, Image.SCALE_SMOOTH)));

	private Image background = ImageManager.testButtonImage_1;

	public Scene2() {
		// Icon normal
		
		testButton.setBounds(500, 500, 400, 100);
		testButton.setBorderPainted(false);
		testButton.setContentAreaFilled(false); // ä������
		testButton.setFocusPainted(false);
		testButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				testButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// Music Class�� �ҷ��ͼ� ���� ���డ��. Start()�� ��������ߵȴٴ��� ( ������ϱ� )
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				testButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// Music Class�� �ҷ��ͼ� ���� ���డ��. Start()�� ��������ߵȴٴ��� ( ������ϱ� )
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// �������� ó�� ȭ���� �ٲ㺸��.
				GIM.GameObject.changeScene(thisInstance, "1");
			}
		});
		GIM.GameObject.add(testButton); // JFrame�� ��ư�� �߰�.


		GIM.GameObject.repaint();
	}

	@Override
	public void removeScene()
	{
		GIM.GameObject.remove(testButton);		
	}
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, 1250, 1080, null);
		GIM.GameObject.paintComponents(g);
		GIM.GameObject.repaint();
	}
}
