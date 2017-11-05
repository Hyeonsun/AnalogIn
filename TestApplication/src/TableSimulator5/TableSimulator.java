package TableSimulator5;

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

	private Image background = new ImageIcon(Main.class.getResource("../image/testBackground.jpg")).getImage();
	private Image testButtonImage = new ImageIcon(Main.class.getResource("../image/2.png")).getImage();

	private JButton testButton = new JButton(new ImageIcon(testButtonImage.getScaledInstance(400, 100, Image.SCALE_SMOOTH)));
	// ũ�⿡ ���߾� ó���� ��
	
	public TableSimulator() {
		setUndecorated(true);
		
		setTitle("Table Simulator");
		setSize(Main.SCREEN_SIZE_X, Main.SCREEN_SIZE_Y);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

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
				testButton.setVisible(false);
				background = new ImageIcon(Main.class.getResource("../image/2.png")).getImage();
			}
		});
		add(testButton); // JFrame�� ��ư�� �߰�.

		Audio introMusic = new Audio("testMusic.mp3", true);
		introMusic.start();
	}

	// double buffer
	public void paint(Graphics g) { // ���� ù��°�� ȭ���� �׷��ִ� �Լ�. ��ӵ� �Լ�.
		screenImage = createImage(Main.SCREEN_SIZE_X, Main.SCREEN_SIZE_Y);
		screenGraphic = screenImage.getGraphics();

		screenDraw(screenGraphic);

		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}
}