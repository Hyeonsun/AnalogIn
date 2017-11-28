package AnalogInProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Scene_CreateGame extends SceneManager {
	/************************************************
	 * Resource
	 *************************************************/
	// Music
	// private Audio introMusic;

	// Image
	private Image background = ImageManager.background;
	private Image image_mainFrame = ImageManager.mainFrame;
	private Image image_subFrame = ImageManager.subFrame;

	/************************************************
	 * Component/object
	 *************************************************/
	private Scene_CreateGame thisInstance = this; // thisInstance
	private JButton createBlockButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_1.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton imageLoadButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_2.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton deleteButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_3.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton ruleButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_4.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton exitButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_5.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton setupButton = new JButton(
			new ImageIcon(ImageManager.setupImage.getScaledInstance(350, 350, Image.SCALE_SMOOTH)));
	private JTextField blockNameTextField = new JTextField();
	private JTextField posXTextField = new JTextField();
	private JTextField posYTextField = new JTextField();
	private JTextField sizeXTextField = new JTextField();
	private JTextField sizeYTextField = new JTextField();
	private JButton staticButton = new JButton();
	private JButton visibleButton = new JButton();
	private JButton behindButton = new JButton();
	private JButton frontButton = new JButton();
	private JButton topButton = new JButton();
	private JButton bottomButton = new JButton();
	private JLabel createBlockField = new JLabel();

	/************************************************
	 * GameController
	 *************************************************/

	Font font1 = new Font("SansSerif", Font.BOLD, 11);

	/// GAME APPLICATION�� ����� �� �ݵ�� �ʱ�ȭ�ؾ��ϴ� GIM ����
	/// - KeyInputBuffer / GIM-currentScene / GIM-blockPriority // BlockObject
	/// -
	public Scene_CreateGame() {
		//
		GIM.currentScene = this;
		// Input ���
		GIM.keyInputBuffer = new KeyInputController();
		GIM.keyInputBuffer.start();
		GIM.blockObject = blockObject;

		// Block�� �켱����
		GIM.blockPriority = 1;

		// setup
		setupButton.setBounds(902, 210, 350, 350);
		setupButton.setBorderPainted(false); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		setupButton.setContentAreaFilled(false); // ä������
		setupButton.setFocusPainted(false);
		setupButton.setLayout(null);
		systemObject.add(setupButton);

		// setup -> name
		blockNameTextField.setBounds(150, 5, 190, 30);
		blockNameTextField.setOpaque(false);
		blockNameTextField.setFont(font1);
		blockNameTextField.setBorder(BorderFactory.createEmptyBorder());
		blockNameTextField.setHorizontalAlignment(JTextField.CENTER);
		setupButton.add(blockNameTextField);

		// setup -> pos X Y
		posXTextField.setBounds(170, 125, 60, 30);
		posXTextField.setOpaque(false);
		posXTextField.setFont(font1);
		posXTextField.setBorder(BorderFactory.createEmptyBorder());
		posXTextField.setHorizontalAlignment(JTextField.CENTER);
		setupButton.add(posXTextField);

		posYTextField.setBounds(260, 125, 60, 30);
		posYTextField.setOpaque(false);
		posYTextField.setFont(font1);
		posYTextField.setBorder(BorderFactory.createEmptyBorder());
		posYTextField.setHorizontalAlignment(JTextField.CENTER);
		setupButton.add(posYTextField);
		// setup -> size X Y
		sizeXTextField.setBounds(170, 175, 60, 30);
		sizeXTextField.setOpaque(false);
		sizeXTextField.setFont(font1);
		sizeXTextField.setBorder(BorderFactory.createEmptyBorder());
		sizeXTextField.setHorizontalAlignment(JTextField.CENTER);
		setupButton.add(sizeXTextField);

		sizeYTextField.setBounds(260, 175, 60, 30);
		sizeYTextField.setOpaque(false);
		sizeYTextField.setFont(font1);
		sizeYTextField.setBorder(BorderFactory.createEmptyBorder());
		sizeYTextField.setHorizontalAlignment(JTextField.CENTER);
		setupButton.add(sizeYTextField);
		// setup -> static
		staticButton.setBounds(114, 278, 27, 27);
		staticButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		staticButton.setContentAreaFilled(false); // ä������
		staticButton.setFocusPainted(false);
		staticButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				staticButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				staticButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				staticButton.setVisible(true);
			}
		});
		setupButton.add(staticButton);
		// setup -> visible
		visibleButton.setBounds(285, 277, 27, 27);
		visibleButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		visibleButton.setContentAreaFilled(false); // ä������
		visibleButton.setFocusPainted(false);
		visibleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				visibleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				visibleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				visibleButton.setVisible(true);
			}
		});
		setupButton.add(visibleButton);

		// setup -> back
		behindButton.setBounds(271, 315, 74, 27);
		behindButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		behindButton.setContentAreaFilled(false); // ä������
		behindButton.setFocusPainted(false);
		behindButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				visibleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				visibleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				visibleButton.setVisible(true);
			}
		});
		setupButton.add(behindButton);
		// setup -> bottom
		bottomButton.setBounds(187, 315, 74, 27);
		bottomButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		bottomButton.setContentAreaFilled(false); // ä������
		bottomButton.setFocusPainted(false);
		bottomButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				visibleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				visibleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				visibleButton.setVisible(true);
			}
		});
		setupButton.add(bottomButton);
		// setup -> bottom
		frontButton.setBounds(102, 315, 74, 27);
		frontButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		frontButton.setContentAreaFilled(false); // ä������
		frontButton.setFocusPainted(false);
		frontButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				visibleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				visibleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				visibleButton.setVisible(true);
			}
		});
		setupButton.add(frontButton);
		// setup -> TOP
		topButton.setBounds(12, 315, 74, 27);
		topButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		topButton.setContentAreaFilled(false); // ä������
		topButton.setFocusPainted(false);
		topButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				visibleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				visibleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				visibleButton.setVisible(true);
			}
		});
		setupButton.add(topButton);
		
		// button
		createBlockButton.setBounds(910, 90, 100, 100);
		createBlockButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		createBlockButton.setContentAreaFilled(false); // ä������
		createBlockButton.setFocusPainted(false);
		createBlockButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				createBlockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				createBlockButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				createBlockField.setVisible(true);
			}
		});
		systemObject.add(createBlockButton);

		// image Upload button
		imageLoadButton.setBounds(1027, 90, 100, 100);
		imageLoadButton.setBorderPainted(true); // ��ư ���� ���� ������ true�� ���� �� �׽�Ʈ��
		imageLoadButton.setContentAreaFilled(false); // ä������
		imageLoadButton.setFocusPainted(false);
		imageLoadButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				imageLoadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				imageLoadButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (GIM.checkedBlock != null) {
					String path = ImageManager.loadImage();
					if (path != null && GIM.checkedBlock != null) {
						GIM.checkedBlock.blockInfo.setImagePath(path);
						GIM.checkedBlock.synBlockInfo();
					}
				}
			}
		});
		systemObject.add(imageLoadButton);

		// delete button
		deleteButton.setBounds(1146, 90, 100, 100);
		deleteButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		deleteButton.setContentAreaFilled(false);
		deleteButton.setFocusPainted(false);
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // �̺�Ʈ
																		// ó����
																		// createBlock�̶�
																		// �Ȱ���
																		// �����Ǿ�����
																		// ������
																		// rule��
																		// �߰�
																		// �ؾ���
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				deleteButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				deleteButton.setVisible(true);
			}
		});
		systemObject.add(deleteButton);

		createBlockField.setBounds(0, 20, 1280, 700); // ũ�� ���� �������� �ƴϿ���? �׽�Ʈ
														// ����?
		createBlockField.setVisible(false);
		createBlockField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		createBlockField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX() + " " + e.getY());
				Block b = new Block(new BlockInformation(e.getX(), e.getY(), 300, 300, ""));
				blockObject.add(b);
				GIM.GameObject.add(b, GIM.blockPriority);
				createBlockField.setVisible(false);
			}
		});
		systemObject.add(0, createBlockField);

		// rule button
		ruleButton.setBounds(910, 580, 175, 115);
		ruleButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		ruleButton.setContentAreaFilled(false); // ä������
		ruleButton.setFocusPainted(false);
		ruleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				ruleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				ruleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				ruleButton.setVisible(true);
			}
		});
		systemObject.add(ruleButton);

		// exit button
		exitButton.setBounds(1220, 8, 29, 29);
		exitButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		exitButton.setContentAreaFilled(false); // ä������
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			// ������ ������ �̺�Ʈ ó������� ��
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				exitButton.setVisible(true);
			}
		});
		systemObject.add(exitButton);
		// blockObject.add(new Block(new
		// BlockInformation(400,300,300,300,ImageManager.testButtonImage)));
		// blockObject.add(new Block(new
		// BlockInformation(500,100,300,300,ImageManager.testButtonImage)));

		for (Component b : systemObject) {
			GIM.GameObject.add(b);
		}
		GIM.blockPriority = systemObject.size();
		for (Block b : blockObject) {
			GIM.GameObject.add(b);
		}

		GIM.GameObject.repaint();

		// introMusic = new Audio("testMusic.mp3", true); �ڲ� �������� �ּ�ó�� �س���
		// introMusic.start();
	}

	@Override
	public void removeScene() {
		GIM.removeGIM();
		for (Component c : systemObject) {
			GIM.GameObject.remove(c);
		}
		for (Block b : blockObject) {
			GIM.GameObject.remove(b);
		}
		// introMusic.close();
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, 1280, 720, null);
		g.drawImage(image_subFrame, 890, 35, 375, 670, null);
		g.drawImage(image_mainFrame, 15, 35, 860, 670, null);

		GIM.GameObject.paintComponents(g);
		GIM.GameObject.repaint();
	}
}
